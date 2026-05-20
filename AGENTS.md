# Project Architecture
This is a Java 21, Spring Boot 3.x multi-module microservices project managed by Maven.

## Structural Rules
- The root pom.xml must use `<packaging>pom</packaging>`.
- Individual microservices must be nested inside this root as sub-modules.
- Use Lombok (`@Data`, `@RequiredArgsConstructor`) for clean Java entities.
- Each service must have its own independent PostgreSQL database connection.
- Put shared events and DTOs inside a standalone module named `common-dto`.