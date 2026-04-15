package com.quantity.quantityservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Example Feign client — Quantity Service calling User Service.
 *
 * Feign automatically:
 *   1. Looks up "user-service" in Eureka
 *   2. Load-balances across instances
 *   3. Generates the HTTP call from the method signature
 *
 * Not used by default — the Gateway header is sufficient.
 * Activate it when you need to fetch user details from User Service.
 */
@FeignClient(name = "user-service")
public interface UserServiceClient {

    @GetMapping("/internal/users/exists")
    boolean userExists(@RequestParam("email") String email);
}