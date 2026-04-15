package com.gateway.apigateway.filter;

import com.gateway.apigateway.config.GatewayJwtUtil;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Applied only to secured routes (e.g. /api/v1/quantities/**).
 *
 * Flow:
 *  1. Check Authorization: Bearer <token> header
 *  2. Validate JWT — invalid → 401, never reaches downstream service
 *  3. Valid → add X-Authenticated-User: <email> header and forward
 *
 * Downstream services trust X-Authenticated-User because only the
 * Gateway sets it after a successful validation.
 */
@Component
public class JwtAuthFilter extends AbstractGatewayFilterFactory<JwtAuthFilter.Config> {

    @Autowired
    private GatewayJwtUtil jwtUtil;

    public JwtAuthFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String authHeader = exchange.getRequest()
                    .getHeaders()
                    .getFirst(HttpHeaders.AUTHORIZATION);

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return unauthorizedResponse(exchange, "Missing or invalid Authorization header");
            }

            String token = authHeader.substring(7);

            if (!jwtUtil.isTokenValid(token)) {
                return unauthorizedResponse(exchange, "Invalid or expired JWT token");
            }

            String username = jwtUtil.extractUsername(token);

            ServerWebExchange mutated = exchange.mutate()
                    .request(r -> r.header("X-Authenticated-User", username))
                    .build();

            return chain.filter(mutated);
        };
    }

    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange, String message) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        exchange.getResponse().getHeaders().add("Content-Type", "application/json");
        var buffer = exchange.getResponse().bufferFactory()
                .wrap(("{\"error\": \"" + message + "\"}").getBytes());
        return exchange.getResponse().writeWith(Mono.just(buffer));
    }

    public static class Config { }
}