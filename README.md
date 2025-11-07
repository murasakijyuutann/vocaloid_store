<div align="center"><p align="center">

  <h1 align="center">🎵 VocaloCart - Vocaloid Shopping Mall</h1>

# 🎵 VocaloCart  <p align="center">

    A modern, full-featured e-commerce platform for Vocaloid merchandise

### Modern E-Commerce Platform for Vocaloid Merchandise    <br />

    <a href="https://github.com/murasakijyuutann/vocaloidshop"><strong>View on GitHub »</strong></a>

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.6-brightgreen.svg)](https://spring.io/projects/spring-boot)    <br />

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://openjdk.java.net/)    <br />

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)    <a href="#-features">Features</a>

[![Live Demo](https://img.shields.io/badge/demo-live-success.svg)](https://vocaloidstore-production.up.railway.app)    ·

    <a href="#-demo">Demo</a>

[Live Demo](https://vocaloidstore-production.up.railway.app) · [Report Bug](https://github.com/murasakijyuutann/vocaloid_store/issues) · [Request Feature](https://github.com/murasakijyuutann/vocaloid_store/issues)    ·

    <a href="#-tech-stack">Tech Stack</a>

</div>    ·

    <a href="#-quick-start">Quick Start</a>

---  </p>

</p>

## 📋 Table of Contents

---

- [About](#-about)

- [Features](#-features)## 📸 Screenshots

- [Tech Stack](#-tech-stack)

- [Architecture](#-architecture)### User Interface

- [Getting Started](#-getting-started)![Homepage](https://via.placeholder.com/800x400/667eea/ffffff?text=Modern+Homepage+with+Gradient+Design)

- [Configuration](#-configuration)*Modern homepage with animated gradient background and featured products*

- [Deployment](#-deployment)

- [API Documentation](#-api-documentation)![Product Catalog](https://via.placeholder.com/800x400/f093fb/ffffff?text=Product+Catalog+with+Categories)

- [Screenshots](#-screenshots)*Browse products by category with responsive card layout*

- [Contributing](#-contributing)

- [License](#-license)![Shopping Cart](https://via.placeholder.com/800x400/4facfe/ffffff?text=Shopping+Cart+%26+Checkout)

*Real-time cart management with quantity updates*

---

### Admin Dashboard

## 🎯 About![Admin Dashboard](https://via.placeholder.com/800x400/764ba2/ffffff?text=Admin+Dashboard+with+Analytics)

*Comprehensive admin panel with product, order, and category management*

**VocaloCart** is a full-stack e-commerce web application built with Spring Boot 3 and modern web technologies. Originally designed for Vocaloid merchandise, it provides a complete online shopping experience with user authentication, product management, shopping cart functionality, and order processing.

---

The application features a responsive UI with gradient designs, real-time cart updates, and a comprehensive admin dashboard for managing products, orders, and categories.

## ✨ Features

### Key Highlights

### Customer Features

- ✨ Modern, gradient-based UI with Bootstrap 5- 🔐 **User Authentication** - Secure registration/login with BCrypt password encryption

- 🔐 Secure authentication with Spring Security- 🛍️ **Product Catalog** - Browse 28+ products across 6 categories (Figures, Plushies, Apparel, Music, Accessories, Posters)

- 💳 Complete checkout flow with address management- 🛒 **Shopping Cart** - Add/remove items, update quantities, real-time price calculations

- 📊 Admin dashboard with analytics- 💳 **Checkout Process** - Complete orders with shipping address management

- 🌐 Deployed on Railway with MySQL database- 📦 **Order Tracking** - View order history and status (Pending → Processing → Shipped → Delivered)

- 🚀 Production-ready with proper error handling- 👤 **User Profile** - Manage account information and saved addresses

- 📱 **Responsive Design** - Mobile-friendly Bootstrap 5 UI with modern gradients and animations

---

### Admin Features

## ✨ Features- 📊 **Dashboard Analytics** - Total products, orders, pending orders statistics

- 📝 **Product Management** - CRUD operations for products (name, price, stock, category, image URL)

### 🛍️ Customer Features- 📋 **Order Management** - View all orders, update status, view customer details

- 🏷️ **Category Management** - Add/remove product categories

- **User Authentication**- 🎨 **Modern Admin UI** - Clean interface with status badges and data tables

  - Secure registration and login with BCrypt encryption

  - Session management with Spring Security### Technical Highlights

  - Password validation and confirmation- � **Role-Based Access Control** - Separate USER and ADMIN roles

  - ✅ **Form Validation** - Server-side validation with Jakarta Bean Validation

- **Product Browsing**- 🎨 **Custom Error Pages** - Professional 404, 403, 500 error pages

  - Browse products across multiple categories (Figures, Plushies, Apparel, Music, Accessories, Posters)- 🐳 **Docker Support** - Production and development Docker configurations

  - Search functionality with keyword filtering- 🗄️ **Database Management** - MySQL 8.0 with Flyway migrations

  - Product details with images, descriptions, and pricing in Japanese Yen (¥)- 🎯 **Clean Architecture** - Service layer, DTOs, repository pattern

  - Stock availability tracking

  ---

- **Shopping Cart**

  - Add/remove products with quantity selection## 🛠 Tech Stack

  - Real-time price calculations

  - Persistent cart across sessions### Backend

  - Visual quantity controls with update/delete buttons- **Java 21** - Latest LTS version with modern language features

  - **Spring Boot 3.5.6** - Application framework with auto-configuration

- **Checkout & Orders**- **Spring Security** - Form-based authentication with BCrypt

  - Multi-step checkout process- **Spring Data JPA** - ORM with Hibernate for database operations

  - Address management (add, edit, delete, set default)- **MySQL 8.0** - Relational database (AWS RDS compatible)

  - Order placement with inventory validation- **Maven** - Dependency management and build tool

  - Order history with status tracking- **Lombok** - Reduce boilerplate code with annotations

  - Order status progression: Pending → Processing → Shipped → Delivered

  ### Frontend

- **User Profile**- **Thymeleaf 3.1.3** - Server-side template engine

  - Account information management- **Bootstrap 5.3.2** - Responsive CSS framework

  - Saved shipping addresses- **Google Fonts** - Inter & Poppins for modern typography

  - Order history viewing- **jQuery 3.7.1** - DOM manipulation and AJAX

- **Font Awesome** - Icon library

### 🔧 Admin Features

### DevOps

- **Dashboard**- **Docker** - Containerization with multi-stage builds

  - Total products, orders, and pending orders statistics- **Docker Compose** - Multi-container orchestration

  - Recent orders table with quick actions- **Git** - Version control

  - Real-time analytics- **AWS RDS** - Cloud database hosting (production)

  

- **Product Management**---

  - Create, edit, and delete products

  - Upload product images## 🚀 Quick Start

  - Manage stock quantities

  - Category assignment### Prerequisites

  - Price management (integer yen values)- Java 21 or higher

  - Maven 3.9+

- **Order Management**- MySQL 8.0 (or use Docker)

  - View all orders with customer details- Git

  - Update order status (Pending, Processing, Shipped, Delivered, Cancelled)

  - Order detail view with line items### Option 1: Docker Deployment (Recommended)

  - Customer information and shipping details

  **Production:**

- **Category Management**```bash

  - Create and manage product categories# Clone repository

  - Category-based product filteringgit clone https://github.com/murasakijyuutann/vocaloidshop.git

cd vocaloidshop

### 🎨 UI/UX Features

# Configure environment

- Fully responsive design (mobile, tablet, desktop)cp .env.example .env

- Modern gradient color schemes# Edit .env and set MYSQL_ROOT_PASSWORD and MYSQL_PASSWORD

- Smooth animations and transitions

- Font Awesome icons throughout# Start services

- Bootstrap 5 componentsdocker-compose -f docker-compose.production.yml up -d

- Thymeleaf templating with layout inheritance

- Form validation with error messages# Access application at http://localhost:8080

- Success/error flash messages```



---**Development (Hot Reload):**

```bash

## 🛠️ Tech Stackdocker-compose -f docker-compose.dev.yml up -d

# Access at http://localhost:8081

### Backend# Edit files in src/ and changes reload automatically

```

- **Framework:** Spring Boot 3.5.6

- **Language:** Java 21### Option 2: Local Development

- **Security:** Spring Security 6

- **Database:** MySQL 8.0 (Production), PostgreSQL support1. **Clone repository:**

- **ORM:** Spring Data JPA (Hibernate)   ```bash

- **Validation:** Jakarta Bean Validation   git clone https://github.com/murasakijyuutann/vocaloidshop.git

- **Build Tool:** Maven 3.9+   cd vocaloidshop

- **Migration:** Flyway   ```



### Frontend2. **Configure database:**

   ```bash

- **Template Engine:** Thymeleaf   # Create MySQL database

- **CSS Framework:** Bootstrap 5.3.2   mysql -u root -p

- **Icons:** Font Awesome 6.4   CREATE DATABASE vocalocart;

- **JavaScript:** jQuery 3.7.1   ```

- **Fonts:** Google Fonts (Inter, Poppins)

3. **Update application.yml:**

### Development Tools   ```yaml

   spring:

- **Lombok** - Reduce boilerplate code     datasource:

- **Spring DevTools** - Hot reload during development       url: jdbc:mysql://localhost:3306/vocalocart

- **ModelMapper** - Object mapping       username: root

- **Dotenv** - Environment variable management       password: your_password

   ```

### Deployment

4. **Run application:**

- **Platform:** Railway   ```bash

- **Database:** Railway MySQL   ./mvnw spring-boot:run

- **Version Control:** Git/GitHub   # or

- **CI/CD:** Automatic deployment on push to main branch   ./mvnw clean package

   java -jar target/vocaloidshoppingmall-0.0.1-SNAPSHOT.jar

---   ```



## 🏗️ Architecture5. **Access application:**

   - Homepage: http://localhost:8081

### Project Structure   - Admin: admin@vocalocart.com / password123

   - User: alice@example.com / password123

```

vocaloidshop/---

├── src/---

│   ├── main/

│   │   ├── java/mjyuu/vocaloidshop/## 📂 Project Structure

│   │   │   ├── config/              # Configuration classes

│   │   │   │   ├── DataInitializer.java```

│   │   │   │   ├── ModelMapperConfig.javavocaloidshop/

│   │   │   │   └── SecurityConfig.java├── src/

│   │   │   ├── controller/          # Web controllers│   ├── main/

│   │   │   │   └── web/│   │   ├── java/mjyuu/vocaloidshop/

│   │   │   │       ├── AdminController.java│   │   │   ├── config/          # Security, CORS configuration

│   │   │   │       ├── AuthWebController.java│   │   │   ├── controller/      # REST API and web controllers

│   │   │   │       ├── CartWebController.java│   │   │   │   ├── api/         # REST endpoints

│   │   │   │       ├── CheckoutWebController.java│   │   │   │   └── web/         # Thymeleaf controllers

│   │   │   │       ├── HomeController.java│   │   │   ├── dto/             # Data Transfer Objects

│   │   │   │       ├── OrderWebController.java│   │   │   ├── entity/          # JPA entities (User, Product, Order, etc.)

│   │   │   │       └── ProductWebController.java│   │   │   ├── repository/      # Spring Data JPA repositories

│   │   │   ├── dto/                 # Data Transfer Objects│   │   │   ├── security/        # Custom security configurations

│   │   │   │   ├── AddToCartRequest.java│   │   │   ├── service/         # Business logic layer

│   │   │   │   ├── LoginRequest.java│   │   │   └── util/            # Utility classes

│   │   │   │   ├── ProductRequestDTO.java│   │   └── resources/

│   │   │   │   └── RegisterRequest.java│   │       ├── templates/       # Thymeleaf HTML templates

│   │   │   ├── entity/              # JPA Entities│   │       │   ├── admin/       # Admin dashboard pages

│   │   │   │   ├── Address.java│   │       │   └── error/       # Custom error pages

│   │   │   │   ├── CartItem.java│   │       ├── static/          # CSS, JS, images

│   │   │   │   ├── Category.java│   │       └── application.yml  # Application configuration

│   │   │   │   ├── Order.java│   └── test/                    # Unit and integration tests

│   │   │   │   ├── OrderItem.java├── docker-compose.production.yml  # Production Docker setup

│   │   │   │   ├── OrderStatus.java (enum)├── docker-compose.dev.yml         # Development Docker setup

│   │   │   │   ├── Product.java├── Dockerfile.production          # Production Docker image

│   │   │   │   ├── Role.java (enum)├── Dockerfile.dev                 # Development Docker image

│   │   │   │   └── User.java├── pom.xml                        # Maven dependencies

│   │   │   ├── exception/           # Custom exceptions└── README.md

│   │   │   │   └── ResourceNotFoundException.java```

│   │   │   ├── repository/          # Spring Data JPA repositories

│   │   │   │   ├── AddressRepository.java---

│   │   │   │   ├── CartItemRepository.java

│   │   │   │   ├── CategoryRepository.java## 🎨 Design System

│   │   │   │   ├── OrderRepository.java

│   │   │   │   ├── ProductRepository.java### Color Palette

│   │   │   │   └── UserRepository.java- **Primary Gradient**: `#667eea → #764ba2` (Purple)

│   │   │   ├── security/            # Security implementation- **Secondary Gradient**: `#f093fb → #f5576c` (Pink)

│   │   │   │   ├── CustomUserDetails.java- **Success Gradient**: `#4facfe → #00f2fe` (Blue)

│   │   │   │   └── CustomUserDetailsService.java

│   │   │   ├── service/             # Business logic### Typography

│   │   │   │   ├── AddressService.java- **Headings**: Poppins (Google Fonts)

│   │   │   │   ├── CartService.java- **Body**: Inter (Google Fonts)

│   │   │   │   ├── CategoryService.java

│   │   │   │   ├── OrderService.java### UI Components

│   │   │   │   ├── ProductService.java- Modern card-based layouts with glassmorphism

│   │   │   │   └── WishlistService.java- Smooth CSS animations and transitions

│   │   │   └── VocaloidshopApplication.java- Responsive Bootstrap 5 grid system

│   │   └── resources/- Custom-styled forms with validation feedback

│   │       ├── application.yml      # Main configuration

│   │       ├── db/migration/        # Flyway migrations---

│   │       └── templates/           # Thymeleaf templates

│   │           ├── admin/           # Admin panel pages## 🗄️ Database Schema

│   │           │   ├── categories.html

│   │           │   ├── dashboard.html### Core Entities

│   │           │   ├── order-detail.html- **User** - Authentication, roles (USER/ADMIN), profile info

│   │           │   ├── orders.html- **Product** - Name, description, price, stock, category, image URL

│   │           │   ├── product-form.html- **Category** - Product categorization

│   │           │   └── products.html- **Cart** - User shopping cart

│   │           ├── error/           # Error pages- **CartItem** - Items in cart with quantity

│   │           │   ├── 403.html- **Order** - Order details, status tracking, shipping info

│   │           │   ├── 404.html- **OrderItem** - Products in order with quantities

│   │           │   ├── 500.html

│   │           │   └── generic.html### Order Status Flow

│   │           ├── cart.html        # Shopping cart```

│   │           ├── checkout.html    # Checkout pagePENDING → PROCESSING → SHIPPED → DELIVERED

│   │           ├── index.html       # Homepage                    ↓

│   │           ├── layout.html      # Base layout                CANCELLED

│   │           ├── login.html       # Login page```

│   │           ├── order-detail.html

│   │           ├── orders.html      # Order history---

│   │           ├── product-detail.html

│   │           ├── products.html    # Product catalog## 🔐 Security Features

│   │           ├── profile.html     # User profile

│   │           └── register.html    # Registration- **BCrypt Password Hashing** - Strong password encryption

│   └── test/                        # Unit and integration tests- **Session Management** - Secure HTTP-only cookies

├── .env.example                     # Environment variables template- **CSRF Protection** - Cross-Site Request Forgery prevention

├── Dockerfile                       # Docker configuration- **Role-Based Access** - Separate USER and ADMIN permissions

├── pom.xml                          # Maven dependencies- **Form Validation** - Jakarta Bean Validation (@Valid, @NotBlank, @Email)

└── README.md                        # This file- **SQL Injection Prevention** - Parameterized JPA queries

```- **Custom Error Pages** - Prevent information leakage



### Database Schema---



**Key Entities:**## 🧪 Testing



- **User** - Customer accounts with roles (USER, ADMIN)### Sample Data

- **Product** - Product catalog with name, description, price, stock, imagesThe application includes pre-loaded sample data:

- **Category** - Product categorization- **5 Users** (4 customers + 1 admin)

- **CartItem** - Shopping cart line items- **28 Products** across 6 categories

- **Order** - Order headers with totals and status- **3 Sample Orders** with various statuses

- **OrderItem** - Order line items with quantity and price snapshot

- **Address** - User shipping addresses### Test Accounts

| Role | Email | Password |

**Relationships:**|------|-------|----------|

| Admin | admin@vocalocart.com | password123 |

- User → CartItem (1:N)| User | alice@example.com | password123 |

- User → Order (1:N)| User | bob@example.com | password123 |

- User → Address (1:N)| User | carol@example.com | password123 |

- Product → CartItem (1:N)

- Product → OrderItem (1:N)### Running Tests

- Product → Category (N:1)```bash

- Order → OrderItem (1:N)# Run all tests

./mvnw test

---

# Run with coverage

## 🚀 Getting Started./mvnw test jacoco:report

```

### Prerequisites

---

- Java 21 or higher

- Maven 3.9+## 📦 Deployment

- MySQL 8.0+ or PostgreSQL 13+

- Git### Docker Production Deployment

```bash

### Installation# Build and start

docker-compose -f docker-compose.production.yml up -d

1. **Clone the repository**

# View logs

```bashdocker-compose -f docker-compose.production.yml logs -f app

git clone https://github.com/murasakijyuutann/vocaloid_store.git

cd vocaloid_store# Stop and remove

```docker-compose -f docker-compose.production.yml down

```

2. **Set up the database**

### Manual Deployment

Create a MySQL database:```bash

# Build JAR

```sql./mvnw clean package -DskipTests

CREATE DATABASE vocaloidshop CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

```# Run JAR

java -jar target/vocaloidshoppingmall-0.0.1-SNAPSHOT.jar

3. **Configure environment variables**```



Create a `.env` file in the project root:### Environment Variables

| Variable | Default | Description |

```env|----------|---------|-------------|

# Database Configuration| `SERVER_PORT` | 8080 | Application port |

DB_URL=jdbc:mysql://localhost:3306/vocaloidshop| `DB_URL` | - | MySQL connection URL |

DB_USERNAME=your_mysql_username| `DB_USERNAME` | - | Database username |

DB_PASSWORD=your_mysql_password| `DB_PASSWORD` | - | Database password |

| `JPA_DDL_AUTO` | update | Hibernate DDL mode |

# Server Configuration

SERVER_PORT=8080See [DOCKER_DEPLOYMENT.md](DOCKER_DEPLOYMENT.md) for detailed deployment guide.



# Optional: Email Configuration (for future features)---

MAIL_HOST=smtp.gmail.com

MAIL_PORT=587## 🚧 Roadmap / Future Enhancements

MAIL_USERNAME=your-email@gmail.com

MAIL_PASSWORD=your-app-password- [ ] Product reviews and ratings system

```- [ ] Email notifications (order confirmation, shipping updates)

- [ ] Wishlist functionality

Or use environment variables directly in your IDE/terminal.- [ ] Advanced product search with filters and pagination

- [ ] Payment gateway integration (Stripe, PayPal)

4. **Build the project**- [ ] Multi-language support (i18n)

- [ ] Product inventory alerts

```bash- [ ] Analytics dashboard for admins

mvn clean install- [ ] User password reset via email

```- [ ] Integration tests with >70% coverage



5. **Run the application**---



```bash## 🤝 Contributing

mvn spring-boot:run

```Contributions are welcome! Please follow these steps:



Or run the JAR directly:1. Fork the repository

2. Create a feature branch (`git checkout -b feature/AmazingFeature`)

```bash3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)

java -jar target/vocaloidshoppingmall-0.0.1-SNAPSHOT.jar4. Push to the branch (`git push origin feature/AmazingFeature`)

```5. Open a Pull Request



6. **Access the application**---



Open your browser and navigate to:## 📝 License



- **Frontend:** http://localhost:8080This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

- **Admin Panel:** http://localhost:8080/admin

- **Health Check:** http://localhost:8080/actuator/health---



### Default Accounts## 👤 Author



The application includes a `DataInitializer` that creates sample data on first run:**murasakijyuutann**

- GitHub: [@murasakijyuutann](https://github.com/murasakijyuutann)

**Admin Account:**- Repository: [vocaloidshop](https://github.com/murasakijyuutann/vocaloidshop)

- Email: `admin@vocaloidshop.com`

- Password: `admin123`---



**User Account:**## 🙏 Acknowledgments

- Email: `user@vocaloidshop.com`

- Password: `user123`- Spring Boot team for excellent framework and documentation

- Bootstrap team for responsive CSS framework

**Sample Data:**- Vocaloid community for inspiration

- 4 Categories (Figures, Plushies, Apparel, Accessories)- Google Fonts for Inter and Poppins typefaces

- 5 Sample Products- Font Awesome for icon library



------



## ⚙️ Configuration## 📞 Support



### Application PropertiesIf you have any questions or issues, please:

1. Check existing [GitHub Issues](https://github.com/murasakijyuutann/vocaloidshop/issues)

Key configuration in `application.yml`:2. Create a new issue with detailed description

3. Include error logs and steps to reproduce

```yaml

spring:---

  datasource:

    url: ${DB_URL}<p align="center">

    username: ${DB_USERNAME}  Made with ❤️ by <a href="https://github.com/murasakijyuutann">murasakijyuutann</a>

    password: ${DB_PASSWORD}  <br />

    driver-class-name: com.mysql.cj.jdbc.Driver  ⭐ Star this repo if you found it helpful!

  </p>

  jpa:

    hibernate:

      ddl-auto: validate  # Use Flyway for schema management4. **Access the Application**└── docker-templates/       # Docker deployment templates

    show-sql: false

    properties:   ```

      hibernate:

        format_sql: true   Open your browser and navigate to:

        dialect: org.hibernate.dialect.MySQL8Dialect

     ```**Note:** All documentation has been moved to `/docs` folder at project root for better organization.

  flyway:

    enabled: true   http://localhost:8080

    baseline-on-migrate: true

     ```## API Endpoints

  security:

    user:

      name: admin

      password: admin## 📋 Environment Variables- `/api/auth/register` - User registration



server:- `/api/auth/login` - User login

  port: ${SERVER_PORT:8080}

  error:You can configure the application using environment variables:- `/api/products` - Product CRUD

    whitelabel:

      enabled: false- `/api/categories` - Category CRUD

```

- `SERVER_PORT`: Server port (default: 8080)- `/api/cart` - Shopping cart

### Database Migration

- `DB_URL`: Database connection URL- `/api/orders` - Order management

Flyway migrations are located in `src/main/resources/db/migration/`. The application will automatically run migrations on startup.

- `DB_USERNAME`: Database username

To create a new migration:

- `DB_PASSWORD`: Database password## 📚 Documentation

```bash

# Create a new migration file- `JPA_DDL_AUTO`: Hibernate DDL auto mode (default: update)

touch src/main/resources/db/migration/V2__description.sql

```All project documentation is now organized in `/docs` folder:



### Security Configuration## 🎨 Application Structure- **Setup Guides:** `/docs/guides/`



Spring Security is configured in `SecurityConfig.java`:- **Architecture:** `/docs/architecture/`



- BCrypt password encoding```- **Deployment:** `/docs/deployment/`

- Role-based access control (USER, ADMIN)

- Custom login pagesrc/- **API Reference:** `/docs/reference/`

- Remember-me functionality

- CSRF protection enabled├── main/



---│   ├── java/See `/docs/README.md` for complete documentation index.



## 🌐 Deployment│   │   └── mjyuu/vocaloidshop/

│   │       ├── config/          # Security and app configuration

### Railway Deployment│   │       ├── controller/      # REST API controllers

│   │       │   └── web/         # Thymeleaf web controllers

This application is deployed on Railway with automatic deployments:│   │       ├── dto/             # Data Transfer Objects

│   │       ├── entity/          # JPA entities

1. **Connect GitHub Repository**│   │       ├── exception/       # Exception handling

   - Link your GitHub repository to Railway│   │       ├── repository/      # Data access layer

   - Enable automatic deployments from main branch│   │       ├── security/        # Security components

│   │       ├── service/         # Business logic

2. **Configure Environment Variables**│   │       └── util/            # Utility classes

│   └── resources/

In Railway dashboard, add:│       ├── templates/           # Thymeleaf templates

│       └── application.yml      # Application configuration

```└── test/                        # Unit and integration tests

DB_URL=jdbc:mysql://mysql.railway.internal:3306/railway```

DB_USERNAME=root

DB_PASSWORD=<railway-generated-password>## 🔐 Default Admin Setup

SERVER_PORT=8080

```To create an admin user, you can either:



3. **Database Setup**1. Register a normal user through the web interface

2. Manually update the user's role in the database:

Railway automatically provisions a MySQL database. The connection details are available in the Railway dashboard.   ```sql

   UPDATE users SET role = 'ADMIN' WHERE email = 'admin@example.com';

4. **Build Configuration**   ```



Railway detects the Maven project and uses:## 📦 Key Pages



```bash- `/` - Home page with featured products

mvn clean package -DskipTests- `/products` - Product catalog with search and filters

java -jar target/vocaloidshoppingmall-0.0.1-SNAPSHOT.jar- `/products/{id}` - Product detail page

```- `/cart` - Shopping cart

- `/checkout` - Checkout process

5. **Access the deployed application**- `/orders` - Order history

- `/profile` - User profile and address management

- **Live URL:** https://vocaloidstore-production.up.railway.app- `/login` - User login

- `/register` - User registration

### Docker Deployment

## 🧪 Testing

Build and run with Docker:

Run tests with:

```bash```bash

# Build image./mvnw test

docker build -t vocaloidshop .```



# Run container## 📝 Database Schema

docker run -p 8080:8080 \

  -e DB_URL=jdbc:mysql://host.docker.internal:3306/vocaloidshop \The application uses Flyway for database migrations. Initial schema includes:

  -e DB_USERNAME=root \- Users (with authentication)

  -e DB_PASSWORD=yourpassword \- Products and Categories

  vocaloidshop- Shopping Cart

```- Orders and Order Items

- Addresses

---

## 🤝 Contributing

## 📚 API Documentation

Contributions are welcome! Please feel free to submit a Pull Request.

### Public Endpoints

## 📄 License

| Method | Endpoint | Description |

|--------|----------|-------------|This project is open source and available under the MIT License.

| GET | `/` | Homepage with featured products |

| GET | `/products` | Product catalog with search/filter |## 📧 Contact

| GET | `/products/{id}` | Product detail page |

| GET | `/login` | Login page |For questions or support, please open an issue in the repository.

| GET | `/register` | Registration page |# vocaloid_store

| POST | `/register` | Create new user account |

### Authenticated User Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/cart` | View shopping cart |
| POST | `/cart/add` | Add product to cart |
| POST | `/cart/update` | Update cart item quantity |
| POST | `/cart/remove` | Remove item from cart |
| GET | `/checkout` | Checkout page |
| POST | `/checkout` | Place order |
| GET | `/orders` | View order history |
| GET | `/orders/{id}` | View order details |
| GET | `/profile` | User profile page |

### Admin Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/admin` | Admin dashboard |
| GET | `/admin/products` | Manage products |
| GET | `/admin/products/new` | Add new product form |
| POST | `/admin/products/save` | Create/update product |
| POST | `/admin/products/delete/{id}` | Delete product |
| GET | `/admin/orders` | Manage orders |
| GET | `/admin/orders/{id}` | View order detail |
| POST | `/admin/orders/{id}/status` | Update order status |
| GET | `/admin/categories` | Manage categories |
| POST | `/admin/categories/save` | Create/update category |
| POST | `/admin/categories/delete/{id}` | Delete category |

---

## 📸 Screenshots

### Customer Interface

**Homepage**
- Modern gradient design with featured products
- Category navigation
- Responsive grid layout

**Product Catalog**
- Category filtering
- Search functionality
- Stock availability indicators
- Price display in Japanese Yen (¥)

**Shopping Cart**
- Real-time quantity updates
- Subtotal calculations
- Remove items functionality
- Order summary with totals

**Checkout**
- Address selection/management
- Order review
- Place order confirmation

### Admin Dashboard

**Dashboard**
- Statistics cards (total products, orders, pending orders)
- Recent orders table
- Quick action buttons

**Product Management**
- Product listing with images
- Create/edit product forms
- Stock management
- Category assignment

**Order Management**
- Order listing with customer details
- Status update functionality
- Order detail view with line items

---

## 🧪 Testing

Run the test suite:

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=ProductServiceTest

# Run with coverage
mvn clean test jacoco:report
```

Test coverage includes:
- Unit tests for service layer
- Controller integration tests
- Repository tests with H2 in-memory database

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Coding Standards

- Follow Java naming conventions
- Write meaningful commit messages
- Add tests for new features
- Update documentation as needed
- Use Lombok annotations to reduce boilerplate

---

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 👥 Authors

- **murasakijyuutann** - *Initial work* - [GitHub](https://github.com/murasakijyuutann)

---

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- Bootstrap team for the UI components
- Railway for hosting the application
- Font Awesome for the icon library
- Vocaloid community for inspiration

---

## 📞 Support

For support, email murasakijyuutann@example.com or open an issue on GitHub.

---

<div align="center">

**[⬆ Back to Top](#-vocalocart)**

Made with ❤️ by [murasakijyuutann](https://github.com/murasakijyuutann)

</div>
