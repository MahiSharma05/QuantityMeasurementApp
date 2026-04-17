# Quantity Measurement API

A Spring Boot REST API for unit conversion and quantity measurement, featuring JWT authentication and Google OAuth2 login.

## Tech Stack

- Java 21 + Spring Boot 3.3
- Spring Security (JWT + OAuth2)
- H2 In-Memory Database
- Swagger / OpenAPI
- Docker (multi-stage build)

---

## Getting Started

### Prerequisites

- Java 21+
- Maven 3.8+ (or use `./mvnw`)
- Docker & Docker Compose

---

## Running Locally

### 1. Configure environment

```bash
cp .env.example .env
# Edit .env with your values (Google OAuth2 credentials, JWT secret, etc.)
```

### 2. Run with Maven

```bash
./mvnw spring-boot:run
```

### 3. Run with Docker Compose

```bash
docker-compose up --build
```

The app will be available at: `http://localhost:8080`

---

## API Documentation

Swagger UI: `http://localhost:8080/swagger-ui/index.html`

---

## Endpoints

| Method | Path | Auth | Description |
|--------|------|------|-------------|
| POST | `/auth/register` | Public | Register new user |
| POST | `/auth/login` | Public | Login and receive JWT |
| GET/POST | `/api/**` | JWT required | Quantity conversion APIs |

---

## Environment Variables

| Variable | Default | Description |
|----------|---------|-------------|
| `PORT` | `8080` | Server port |
| `DB_URL` | `jdbc:h2:mem:quantitydb` | JDBC datasource URL |
| `DB_USERNAME` | `sa` | Database username |
| `DB_PASSWORD` | _(empty)_ | Database password |
| `H2_CONSOLE_ENABLED` | `false` | Enable H2 web console |
| `JPA_SHOW_SQL` | `false` | Print SQL to logs |
| `JWT_SECRET` | _(must set)_ | Min 32-char secret key for JWT signing |
| `JWT_EXPIRATION_MS` | `86400000` | JWT token lifetime in ms (default: 24h) |
| `GOOGLE_CLIENT_ID` | _(must set)_ | Google OAuth2 client ID |
| `GOOGLE_CLIENT_SECRET` | _(must set)_ | Google OAuth2 client secret |
| `OAUTH2_REDIRECT_URI` | `http://localhost:8080/login/oauth2/code/google` | Registered OAuth2 redirect URI |
| `FRONTEND_REDIRECT_URL` | `http://localhost:4200/oauth2/callback` | Frontend URL after OAuth2 success |
| `ACTUATOR_ENDPOINTS` | `health,info` | Exposed actuator endpoints |

---

## Railway Deployment

1. Push to a GitHub repository.
2. Create a new Railway project and link the repo.
3. Set all required environment variables in Railway's **Variables** panel (see table above).
4. Railway auto-detects the `Dockerfile` and deploys automatically.
5. The `PORT` variable is injected by Railway automatically.

> **Important:** Make sure to add your Railway app's URL as an authorized redirect URI in your [Google Cloud Console](https://console.cloud.google.com/).

---

## Security Notes

- Never commit `.env` or any file containing real credentials.
- Set a strong `JWT_SECRET` (32+ random characters) in production.
- Disable `H2_CONSOLE_ENABLED` in production (default is `false`).
- Restrict `ACTUATOR_ENDPOINTS` to `health,info` in production.
