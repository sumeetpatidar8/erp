# ERP Application

[![Java](https://img.shields.io/badge/Java-21+-blue.svg)](https://openjdk.java.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.1-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Gradle](https://img.shields.io/badge/Gradle-9.2.1-02303A.svg)](https://gradle.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

A modular Spring Boot-based ERP system for managing companies, inventory, orders, and real-time notifications.

## Features

- **Authentication & Security**: OAuth2 JWT-based security with configurable paths.
- **Real-time Notifications**: WebSocket support for alerts scoped by company.
- **Modular Architecture**: Organized into packages (common, auth, org, inventory, orders, notifications, frontend) for scalability.
- **Database Migrations**: Flyway for version-controlled schema changes.
- **Monitoring**: Spring Boot Actuator for health checks and metrics.
- **Frontend**: Thymeleaf with HTMX for dynamic, server-driven UI.
- **Profiles**: Dev and prod configurations for different environments.

## Prerequisites

- **Java**: 21 or higher
- **Database**: PostgreSQL
- **Build Tool**: Gradle (included via wrapper)

## Quick Start

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd erp
   ```

2. **Configure Database**:
   - Update `src/main/resources/application.yml` or use profiles (`application-dev.yml`, `application-prod.yml`).
   - Ensure PostgreSQL is running.

3. **Build and Run**:
   ```bash
   ./gradlew build
   ./gradlew bootRun  # For dev
   # Or for prod: ./gradlew bootRun --args='--spring.profiles.active=prod'
   ```

4. **Access the Application**:
   - Frontend: http://localhost:8080
   - Actuator Health: http://localhost:8080/actuator/health

## Project Structure

```
erp/
├── src/main/java/com/sumeet/erp/
│   ├── common/          # Shared utilities, configs, validation
│   ├── auth/            # User and role management
│   ├── org/             # Companies, branches, departments
│   ├── inventory/       # Products, warehouses, stock
│   ├── orders/          # Orders and order lines
│   ├── notifications/   # WebSocket alerts
│   └── frontend/        # Controllers and views
├── src/main/resources/
│   ├── db/migration/    # Flyway scripts
│   └── templates/       # Thymeleaf views
└── src/test/            # Unit and integration tests
```

## Profiles

- **`dev`**: Debug logging, local DB, relaxed security.
- **`prod`**: Production settings with env vars for DB credentials.

## API Documentation

- Health Checks: `/actuator/health`
- Metrics: `/actuator/metrics`
- (Future: Swagger UI at `/swagger-ui.html`)

## Testing

```bash
./gradlew test          # Run all tests
./gradlew test --info   # With detailed output
```

## Contributing

1. Fork the repository.
2. Create a feature branch: `git checkout -b feature/your-feature`.
3. Commit changes: `git commit -am 'Add your feature'`.
4. Push to branch: `git push origin feature/your-feature`.
5. Submit a pull request.

### Guidelines
- Follow Java coding standards.
- Add tests for new features.
- Update documentation as needed.
- Use conventional commits.

## Future Enhancements

- REST APIs for full CRUD operations.
- Frontend views with HTMX components.
- Advanced authentication (JWT implementation).
- Multi-tenancy support.
- External integrations (email, payments).
- CI/CD pipeline.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Support

For issues or questions, open a GitHub issue or contact the maintainers.