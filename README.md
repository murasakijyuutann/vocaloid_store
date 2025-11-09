<div align="center">

# 🎵 VocaloCart - Vocaloid Shopping Mall

### Modern E-Commerce Platform for Vocaloid Merchandise

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.6-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://openjdk.java.net/)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Live Demo](https://img.shields.io/badge/demo-live-success.svg)](https://vocaloidstore-production.up.railway.app)

[Live Demo](https://vocaloidstore-production.up.railway.app) · [Report Bug](https://github.com/murasakijyuutann/vocaloid_store/issues) · [Request Feature](https://github.com/murasakijyuutann/vocaloid_store/issues)

</div>

---

## 📋 Table of Contents

- [About](#-about)
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Screenshots](#-screenshots)
- [Quick Start](#-quick-start)
- [Project Structure](#-project-structure)
- [Design System](#-design-system)
- [Database Schema](#-database-schema)
- [Security Features](#-security-features)
- [Testing](#-testing)
- [Deployment](#-deployment)
- [Roadmap](#-roadmap--future-enhancements)
- [Contributing](#-contributing)
- [License](#-license)

---

## 🎯 About

**VocaloCart** is a full-stack e-commerce web application built with Spring Boot 3 and modern web technologies. Originally designed for Vocaloid merchandise, it provides a complete online shopping experience with user authentication, shopping cart management, order processing, and a comprehensive admin dashboard.

The application features a responsive UI with gradient designs, real-time cart updates, and role-based access control for managing products, orders, and categories.

---

## ✨ Features

### Customer Features

- 🔐 **User Authentication** - Secure registration/login with BCrypt password encryption
- 🛍️ **Product Catalog** - Browse 28+ products across 6 categories (Figures, Plushies, Apparel, Music, Accessories, Posters)
- 🛒 **Shopping Cart** - Add/remove items, update quantities, real-time price calculations
- 💳 **Checkout Process** - Complete orders with shipping address management
- 📦 **Order Tracking** - View order history and status (Pending → Processing → Shipped → Delivered)
- 👤 **User Profile** - Manage account information and saved addresses
- 📱 **Responsive Design** - Mobile-friendly Bootstrap 5 UI with modern gradients and animations

### Admin Features

- 📊 **Dashboard Analytics** - Total products, orders, pending orders statistics
- 📝 **Product Management** - CRUD operations for products (name, price, stock, category, image URL)
- 📋 **Order Management** - View all orders, update status, view customer details
- 🏷️ **Category Management** - Add/remove product categories
- 🎨 **Modern Admin UI** - Clean interface with status badges and data tables

### Technical Highlights

- 🔒 **Role-Based Access Control** - Separate USER and ADMIN roles
- ✅ **Form Validation** - Server-side validation with Jakarta Bean Validation
- 🎨 **Custom Error Pages** - Professional 404, 403, 500 error pages
- 🐳 **Docker Support** - Production and development Docker configurations
- 🗄️ **Database Management** - MySQL 8.0 with Flyway migrations
- 🎯 **Clean Architecture** - Service layer, DTOs, repository pattern

---

## 🛠 Tech Stack

### Backend

- **Java 21** - Latest LTS version with modern language features
- **Spring Boot 3.5.6** - Application framework with auto-configuration
- **Spring Security** - Form-based authentication with BCrypt
- **Spring Data JPA** - ORM with Hibernate for database operations
- **MySQL 8.0** - Relational database (AWS RDS compatible)
- **Maven** - Dependency management and build tool
- **Lombok** - Reduce boilerplate code with annotations

### Frontend

- **Thymeleaf 3.1.3** - Server-side template engine
- **Bootstrap 5.3.2** - Responsive CSS framework
- **Google Fonts** - Inter & Poppins for modern typography
- **jQuery 3.7.1** - DOM manipulation and AJAX
- **Font Awesome** - Icon library

### DevOps

- **Docker** - Containerization with multi-stage builds
- **Docker Compose** - Multi-container orchestration
- **Git** - Version control
- **Railway** - Cloud platform deployment (production)
- **Flyway** - Database migration tool

---

## 📸 Screenshots

### User Interface

![Homepage](https://via.placeholder.com/800x400/667eea/ffffff?text=Modern+Homepage+with+Gradient+Design)
*Modern homepage with animated gradient background and featured products*

![Product Catalog](https://via.placeholder.com/800x400/f093fb/ffffff?text=Product+Catalog+with+Categories)
*Browse products by category with responsive card layout*

![Shopping Cart](https://via.placeholder.com/800x400/4facfe/ffffff?text=Shopping+Cart+%26+Checkout)
*Real-time cart management with quantity updates*

### Admin Dashboard

![Admin Dashboard](https://via.placeholder.com/800x400/764ba2/ffffff?text=Admin+Dashboard+with+Analytics)
*Comprehensive admin panel with product, order, and category management*

---

## 🚀 Quick Start

### Prerequisites

- Java 21 or higher
- Maven 3.9+
- MySQL 8.0 (or use Docker)
- Git

### Option 1: Docker Deployment (Recommended)

**Production:**
```bash
# Clone repository
git clone https://github.com/murasakijyuutann/vocaloid_store.git
cd vocaloid_store

# Configure environment
cp .env.example .env
# Edit .env and set MYSQL_ROOT_PASSWORD and MYSQL_PASSWORD

# Start services
docker-compose -f docker-compose.production.yml up -d

# Access application at http://localhost:8080
```

**Development (Hot Reload):**
```bash
docker-compose -f docker-compose.dev.yml up -d
# Access at http://localhost:8081
# Edit files in src/ and changes reload automatically
```

### Option 2: Local Development

1. **Clone repository:**
   ```bash
   git clone https://github.com/murasakijyuutann/vocaloid_store.git
   cd vocaloid_store
   ````

2. **Configure database:**
   ```bash
   # Create MySQL database
   mysql -u root -p
   CREATE DATABASE vocalocart;
   ```

3. **Update application.yml:**
   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/vocalocart
       username: root
       password: your_password
   ```

4. **Run application:**
   ```bash
   ./mvnw spring-boot:run
   # or
   ./mvnw clean package
   java -jar target/vocaloidshoppingmall-0.0.1-SNAPSHOT.jar
   ```

5. **Access application:**
   - Homepage: http://localhost:8081
   - Admin: admin@vocalocart.com / password123
   - User: alice@example.com / password123

---

## 📂 Project Structure

```
vocaloidshop/
├── src/
│   ├── main/
│   │   ├── java/mjyuu/vocaloidshop/
│   │   │   ├── config/          # Security, CORS configuration
│   │   │   ├── controller/      # REST API and web controllers
│   │   │   │   ├── api/         # REST endpoints
│   │   │   │   └── web/         # Thymeleaf controllers
│   │   │   ├── dto/             # Data Transfer Objects
│   │   │   ├── entity/          # JPA entities (User, Product, Order, etc.)
│   │   │   ├── repository/      # Spring Data JPA repositories
│   │   │   ├── security/        # Custom security configurations
│   │   │   ├── service/         # Business logic layer
│   │   │   └── util/            # Utility classes
│   │   └── resources/
│   │       ├── templates/       # Thymeleaf HTML templates
│   │       │   ├── admin/       # Admin dashboard pages
│   │       │   └── error/       # Custom error pages
│   │       ├── static/          # CSS, JS, images
│   │       └── application.yml  # Application configuration
│   └── test/                    # Unit and integration tests
├── docker-compose.production.yml  # Production Docker setup
├── docker-compose.dev.yml         # Development Docker setup
├── Dockerfile.production          # Production Docker image
├── Dockerfile.dev                 # Development Docker image
├── pom.xml                        # Maven dependencies
└── README.md
```

---

## 🎨 Design System

### Color Palette

- **Primary Gradient**: `#667eea → #764ba2` (Purple)
- **Secondary Gradient**: `#f093fb → #f5576c` (Pink)
- **Success Gradient**: `#4facfe → #00f2fe` (Blue)

### Typography

- **Headings**: Poppins (Google Fonts)
- **Body**: Inter (Google Fonts)

### UI Components

- Modern card-based layouts with glassmorphism
- Smooth CSS animations and transitions
- Responsive Bootstrap 5 grid system
- Custom-styled forms with validation feedback

---

## 🗄️ Database Schema

### Core Entities

- **User** - Authentication, roles (USER/ADMIN), profile info
- **Product** - Name, description, price, stock, category, image URL
- **Category** - Product categorization
- **Cart** - User shopping cart
- **CartItem** - Items in cart with quantity
- **Order** - Order details, status tracking, shipping info
- **OrderItem** - Products in order with quantities
- **Address** - User shipping addresses

### Order Status Flow

```
PENDING → PROCESSING → SHIPPED → DELIVERED
                   ↓
               CANCELLED
```

---

## 🔐 Security Features

- **BCrypt Password Hashing** - Strong password encryption
- **Session Management** - Secure HTTP-only cookies
- **CSRF Protection** - Cross-Site Request Forgery prevention
- **Role-Based Access** - Separate USER and ADMIN permissions
- **Form Validation** - Jakarta Bean Validation (@Valid, @NotBlank, @Email)
- **SQL Injection Prevention** - Parameterized JPA queries
- **Custom Error Pages** - Prevent information leakage

---

## 🧪 Testing

### Sample Data

The application includes pre-loaded sample data:
- **5 Users** (4 customers + 1 admin)
- **28 Products** across 6 categories
- **3 Sample Orders** with various statuses

### Test Accounts

| Role | Email | Password |
|------|-------|----------|
| Admin | admin@vocalocart.com | password123 |
| User | alice@example.com | password123 |
| User | bob@example.com | password123 |
| User | carol@example.com | password123 |

### Running Tests

```bash
# Run all tests
./mvnw test

# Run with coverage
./mvnw test jacoco:report
```

---

## 📦 Deployment

### Docker Production Deployment

```bash
# Build and start
docker-compose -f docker-compose.production.yml up -d

# View logs
docker-compose -f docker-compose.production.yml logs -f app

# Stop and remove
docker-compose -f docker-compose.production.yml down
```

### Manual Deployment

```bash
# Build JAR
./mvnw clean package -DskipTests

# Run JAR
java -jar target/vocaloidshoppingmall-0.0.1-SNAPSHOT.jar
```

### Environment Variables

| Variable | Default | Description |
|----------|---------|-------------|
| `SERVER_PORT` | 8080 | Application port |
| `DB_URL` | - | MySQL connection URL |
| `DB_USERNAME` | - | Database username |
| `DB_PASSWORD` | - | Database password |
| `JPA_DDL_AUTO` | update | Hibernate DDL mode |

---

## 🚧 Roadmap / Future Enhancements

- [ ] Product reviews and ratings system
- [ ] Email notifications (order confirmation, shipping updates)
- [ ] Wishlist functionality
- [ ] Advanced product search with filters and pagination
- [ ] Payment gateway integration (Stripe, PayPal)
- [ ] Multi-language support (i18n)
- [ ] Product inventory alerts
- [ ] Analytics dashboard for admins
- [ ] User password reset via email
- [ ] Integration tests with >70% coverage

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 👤 Author

**murasakijyuutann**

- GitHub: [@murasakijyuutann](https://github.com/murasakijyuutann)
- Repository: [vocaloid_store](https://github.com/murasakijyuutann/vocaloid_store)

---

## 🙏 Acknowledgments

- Spring Boot team for excellent framework and documentation
- Bootstrap team for responsive CSS framework
- Vocaloid community for inspiration
- Google Fonts for Inter and Poppins typefaces
- Font Awesome for icon library

---

## 📞 Support

If you have any questions or issues, please:

1. Check existing [GitHub Issues](https://github.com/murasakijyuutann/vocaloid_store/issues)
2. Create a new issue with detailed description
3. Include error logs and steps to reproduce

---

<div align="center">

Made with ❤️ by [murasakijyuutann](https://github.com/murasakijyuutann)

⭐ Star this repo if you found it helpful!

</div>
