<div align="center">

# Royal Bank - Enterprise Banking System

[![Java Version](https://img.shields.io/badge/Java-11-orange.svg)](https://www.oracle.com/java/)
[![Jakarta EE](https://img.shields.io/badge/Jakarta%20EE-10.0-blue.svg)](https://jakarta.ee/)
[![Maven](https://img.shields.io/badge/Maven-3.x-red.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

</div>

> A modern, secure, and scalable digital banking platform built with enterprise-grade Jakarta EE technologies, demonstrating advanced Java development skills and enterprise architecture patterns.

## Project Overview

Royal Bank is a comprehensive banking management system that showcases enterprise application development using **Jakarta EE 10**, **Maven multi-module architecture**, and **modern security practices**. The system implements core banking functionalities with **role-based access control**, **automated scheduling**, **email notifications**, and **comprehensive audit logging**.

## Architecture & Design Patterns

### Multi-Module Maven Architecture

```
RoyalBank/
├── core/           # Domain models, services interfaces, utilities
├── auth/           # Authentication & user management EJBs
├── banking/        # Banking operations & transaction EJBs
├── timer/          # Scheduled tasks & background processing
├── web/            # Web layer (Servlets, JSPs, Security)
└── ear/            # Enterprise Application Archive
```

### Enterprise Patterns Implemented

- **Repository Pattern** - Data access abstraction
- **Service Layer Pattern** - Business logic encapsulation
- **Interceptor Pattern** - Cross-cutting concerns (Security, Logging)
- **Scheduler Pattern** - Automated background tasks
- **Strategy Pattern** - Email service implementations
- **Factory Pattern** - Mail service provider instantiation

## Key Features

### User Management

- **User Registration** with email verification
- **JWT-based Authentication** with role-based authorization
- **Multi-role Support**: USER, ADMIN, SUPER_ADMIN
- **Account Lifecycle Management**

### Banking Operations

- **Fund Transfers** between accounts
- **Balance Inquiries** with real-time updates
- **Transaction History** with detailed audit trails
- **Scheduled Transfers** for future-dated transactions
- **Monthly Interest Calculation** with automated application

### Security Features

- **Container-Managed Security** with Jakarta Security
- **Custom Authentication Mechanism**
- **Method-Level Authorization** using `@Secured` annotations
- **Password Encryption** using MD5 hashing
- **Session Management** with automatic timeout

### Reporting & Notifications

- **PDF Report Generation** using iText library
- **Monthly Transaction Reports** with detailed breakdowns
- **Email Notifications** for login alerts and daily balances
- **Comprehensive Audit Logging** with user action tracking

### Automation & Scheduling

- **Daily Balance Email Reports** (7:00 AM daily)
- **Monthly Interest Application** (1st of each month)
- **Scheduled Transaction Processing** (1:00 AM daily)
- **Mail Service Provider** with thread pool management

## Technology Stack

### Backend Technologies

| Technology           | Version | Purpose                          |
| -------------------- | ------- | -------------------------------- |
| **Java**             | 11      | Core programming language        |
| **Jakarta EE**       | 10.0    | Enterprise application framework |
| **EJB 4.0**          | Latest  | Business logic containers        |
| **JPA 3.1**          | Latest  | Object-relational mapping        |
| **Jakarta Security** | 3.0     | Authentication & authorization   |
| **Jakarta Mail**     | 2.1     | Email service integration        |

### Frontend & Web Layer

| Technology      | Purpose                         |
| --------------- | ------------------------------- |
| **JSP**         | Server-side view templates      |
| **JSTL**        | Tag library for dynamic content |
| **HTML5/CSS3**  | Modern responsive UI            |
| **Servlet API** | HTTP request handling           |

### Build & Dependencies

| Tool          | Purpose                                  |
| ------------- | ---------------------------------------- |
| **Maven**     | Build automation & dependency management |
| **iText PDF** | PDF report generation                    |
| **MySQL**     | Primary database                         |

## Module Breakdown

### Core Module

- **Domain Models**: User, Account, Transaction, ScheduledTransaction
- **Service Interfaces**: Defining business contracts
- **Utility Classes**: Encryption, environment configuration
- **Custom Annotations**: @Logged, @Secured, @UserLogAlert
- **Exception Handling**: Custom business exceptions

### Auth Module

- **UserBean**: Complete user lifecycle management
- **LogBean**: Comprehensive audit logging system
- **Security Integration**: Jakarta Security implementation

### Banking Module

- **TransactionBean**: Atomic transaction processing
- **AccountBean**: Account management operations
- **ReportBean**: PDF generation with iText
- **InterestBean**: Automated interest calculations
- **ScheduledTransactionBean**: Future transaction management

### Timer Module

- **Interest Timer**: Monthly interest application
- **Daily Balance Timer**: Automated email notifications
- **Scheduled Transaction Timer**: Processing due transfers

### Web Module

- **Security Configuration**: Authentication mechanisms
- **Servlet Controllers**: Request handling and routing
- **JSP Views**: Dynamic user interfaces
- **Error Handling**: Custom error pages

## Security Implementation

### Authentication Flow

```java
@AutoApplySession
@ApplicationScoped
public class AuthMechanism implements HttpAuthenticationMechanism {
    // Custom authentication logic with credential validation
}
```

### Authorization Control

```java
@Secured(roles = {"USER", "ADMIN", "SUPER_ADMIN"})
public void transferAmount(String from, String to, double amount) {
    // Secure transaction processing
}
```

### Audit Logging

```java
@Interceptor
@Logged
public class LoggingInterceptor {
    // Automatic method execution logging
}
```

## Database Schema

### Core Entities

- **Users**: Authentication and profile information
- **Accounts**: Banking account details and balances
- **Transactions**: Complete transaction history
- **ScheduledTransactions**: Future-dated transfers
- **History**: User activity audit trail
- **LogEntries**: System operation logs

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6+
- MySQL 8.0+
- Jakarta EE compatible application server (GlassFish, WildFly, etc.)

### Installation

1. **Clone the repository**

   ```bash
   git clone https://github.com/gitxar7/RoyalBank.git
   cd RoyalBank
   ```

2. **Configure Database**

   ```sql
   CREATE DATABASE royal_bank;
   -- Configure connection in your application server
   ```

3. **Update Configuration**

   ```properties
   # core/src/main/resources/application.properties
   mail-trap.host=your-smtp-host
   mail-trap.port=587
   mail-trap.username=your-username
   mail-trap.password=your-password
   ```

4. **Build the Application**

   ```bash
   mvn clean install
   ```

5. **Deploy EAR File**

   ```bash
   # Deploy target/royal-bank-ear.ear to your application server
   ```

6. **Access the Application**
   ```
   http://localhost:8080/royal-bank
   ```

## User Interface

### Landing Page

Clean, modern interface with gradient backgrounds and responsive design

### User Dashboard

- Real-time balance display
- Transaction history
- Quick action buttons
- Account details panel

### Admin Panel

- User management interface
- System logs monitoring
- Administrative controls

## Configuration

### Email Service

Configure SMTP settings in `application.properties`:

```properties
mail-trap.host=sandbox.smtp.mailtrap.io
mail-trap.port=587
app.email=web@royalbank.lk
```

### Database Connection

Configure JNDI datasource: `royal_bank_connection`

### Security Roles

- **USER**: Basic banking operations
- **ADMIN**: User management capabilities
- **SUPER_ADMIN**: Full system administration

## Performance Features

- **Connection Pooling** for database operations
- **Thread Pool Management** for email services
- **Lazy Loading** for JPA relationships
- **Transaction Optimization** with proper isolation levels
- **Caching Disabled** for real-time data consistency

## Testing Strategy

The application implements comprehensive error handling with:

- Custom exception types for different failure scenarios
- Error page mappings for user-friendly error display
- Transaction rollback on business logic failures
- Validation at multiple application layers

## Development Highlights

### Advanced Jakarta EE Features

- **CDI Integration** for dependency injection
- **Container-Managed Transactions** with proper isolation
- **Method-Level Security** with custom annotations
- **Interceptor Chains** for cross-cutting concerns
- **Timer Service** for automated background tasks

### Code Quality Practices

- **Separation of Concerns** across modules
- **Interface-Based Design** for loose coupling
- **Custom Annotation Processing** for metadata handling
- **Proper Exception Hierarchy** for error management
- **Resource Management** with try-with-resources

## Contributing

This project demonstrates enterprise Java development skills and is open for collaboration. Areas for contribution:

- REST API implementation
- React/Angular frontend
- Microservices migration
- Cloud deployment configurations
- Additional banking features

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Author

**Abdur Rahman Hanas** - _Software Engineer_

- GitHub: [@gitxar7](https://github.com/gitxar7)
- Email: nxt.genar7@gmail.com
- Specialization: Enterprise Java Development, Banking Systems, Security Implementation

---

_This project showcases advanced Jakarta EE development skills, enterprise architecture patterns, and modern security practices suitable for large-scale financial applications._
