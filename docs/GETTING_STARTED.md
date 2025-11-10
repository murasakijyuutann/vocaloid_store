# Getting Started with VocaloCart

## Quick Setup Guide

### 1. Database Setup

First, create the MySQL database:

```sql
CREATE DATABASE vocalocart CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 2. Configure Application

Edit `src/main/resources/application.yml` and update:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/vocalocart
    username: your_mysql_username
    password: your_mysql_password
```

### 3. Build and Run

```bash
# Clean and build the project
./mvnw clean package

# Run the application
./mvnw spring-boot:run
```

### 4. Access the Application

Open your browser and go to: **http://localhost:8080**

### 5. Create Your First Account

1. Click on "Register" in the navigation bar
2. Fill in your details (name, email, password)
3. Click "Register"
4. Login with your credentials

### 6. Add Sample Products (Optional)

You can add products manually through:
- The database directly
- Or create an admin controller endpoint

To make a user an admin:
```sql
UPDATE users SET role = 'ADMIN' WHERE email = 'your_email@example.com';
```

## Troubleshooting

### Port Already in Use
If port 8080 is already in use, change it in `application.yml`:
```yaml
server:
  port: 8081  # Or any available port
```

### Database Connection Issues
- Make sure MySQL is running
- Verify username and password
- Check if database exists
- Ensure MySQL allows connections from localhost

### Application Won't Start
- Check Java version: `java -version` (should be 21+)
- Clean Maven cache: `./mvnw clean`
- Check logs in the console for specific errors

## Next Steps

1. Browse products at `/products`
2. Add items to your cart
3. Create shipping addresses in your profile
4. Complete a test order

## Development Mode

The application runs with:
- Thymeleaf cache disabled for hot-reload of templates
- DevTools enabled for automatic restart on code changes
- SQL logging enabled to see database queries

Happy shopping! 🎵
