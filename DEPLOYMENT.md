# Deployment Checklist

## Pre-Deployment Setup

### 1. Database Configuration
- [ ] MySQL 8.0+ installed and running
- [ ] Database `vocalocart` created
- [ ] Database credentials configured in `application.yml` or environment variables
- [ ] Database connection tested

### 2. Application Configuration
- [ ] Update `application.yml` with production database URL
- [ ] Set `JPA_DDL_AUTO` to `validate` for production (not `update`)
- [ ] Configure email settings if using email features
- [ ] Review and update security settings

### 3. Environment Variables (Production)
```bash
export DB_URL="jdbc:mysql://your-production-db:3306/vocalocart"
export DB_USERNAME="your_db_user"
export DB_PASSWORD="your_secure_password"
export SERVER_PORT="8080"
export JPA_DDL_AUTO="validate"
```

## Build and Package

### Maven Build
```bash
# Clean previous builds
./mvnw clean

# Run tests
./mvnw test

# Package application
./mvnw package -DskipTests

# Verify JAR created
ls -lh target/*.jar
```

### JAR Location
The executable JAR will be at:
```
target/vocaloidshoppingmall-0.0.1-SNAPSHOT.jar
```

## Deployment Options

### Option 1: Direct JAR Execution
```bash
# Run with default settings
java -jar target/vocaloidshoppingmall-0.0.1-SNAPSHOT.jar

# Run with custom port and profile
java -jar -Dserver.port=8080 target/vocaloidshoppingmall-0.0.1-SNAPSHOT.jar

# Run with environment variables
DB_URL="jdbc:mysql://localhost:3306/vocalocart" \
DB_USERNAME="root" \
DB_PASSWORD="password" \
java -jar target/vocaloidshoppingmall-0.0.1-SNAPSHOT.jar
```

### Option 2: System Service (Linux)
Create `/etc/systemd/system/vocalocart.service`:
```ini
[Unit]
Description=VocaloCart Shopping Mall
After=network.target

[Service]
Type=simple
User=vocalocart
WorkingDirectory=/opt/vocalocart
ExecStart=/usr/bin/java -jar /opt/vocalocart/vocaloidshoppingmall.jar
Restart=on-failure
Environment="DB_URL=jdbc:mysql://localhost:3306/vocalocart"
Environment="DB_USERNAME=vocalocart_user"
Environment="DB_PASSWORD=secure_password"

[Install]
WantedBy=multi-user.target
```

Enable and start:
```bash
sudo systemctl enable vocalocart
sudo systemctl start vocalocart
sudo systemctl status vocalocart
```

### Option 3: Docker (Create Dockerfile)
```dockerfile
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

Build and run:
```bash
docker build -t vocalocart:latest .
docker run -d -p 8080:8080 \
  -e DB_URL="jdbc:mysql://host.docker.internal:3306/vocalocart" \
  -e DB_USERNAME="root" \
  -e DB_PASSWORD="password" \
  --name vocalocart \
  vocalocart:latest
```

## Post-Deployment

### 1. Verify Application
- [ ] Application starts without errors
- [ ] Access home page: `http://your-server:8080`
- [ ] Test user registration
- [ ] Test user login
- [ ] Test product browsing
- [ ] Test cart functionality
- [ ] Test checkout process

### 2. Create Admin User
```sql
-- Connect to MySQL
mysql -u root -p vocalocart

-- Create or update admin user
INSERT INTO users (name, email, password, role, created_at, updated_at) 
VALUES ('Admin', 'admin@vocalocart.com', '$2a$10$...', 'ADMIN', NOW(), NOW());
-- Note: Password should be BCrypt hashed

-- Or update existing user
UPDATE users SET role = 'ADMIN' WHERE email = 'your-email@example.com';
```

### 3. Add Sample Data (Optional)
```sql
-- Add categories
INSERT INTO categories (name, created_at) VALUES 
('Figures', NOW()),
('Accessories', NOW()),
('Music', NOW()),
('Apparel', NOW());

-- Add sample products
INSERT INTO products (name, description, price, stock_quantity, image_url, category_id) VALUES
('Hatsune Miku Figure', 'Premium quality figure', 49.99, 10, 'https://example.com/image1.jpg', 1),
('Kagamine Rin Keychain', 'Cute accessory', 9.99, 50, 'https://example.com/image2.jpg', 2);
```

## Monitoring and Maintenance

### Health Check
```bash
curl http://localhost:8080/actuator/health
```

### Log Files
- Application logs: Check console or configure log file in `application.yml`
- Access logs: Configure in Spring Boot if needed

### Database Backup
```bash
# Regular backup
mysqldump -u root -p vocalocart > vocalocart_backup_$(date +%Y%m%d).sql

# Restore from backup
mysql -u root -p vocalocart < vocalocart_backup_20250106.sql
```

## Security Checklist

- [ ] Database credentials are secure and not committed to version control
- [ ] HTTPS enabled (use reverse proxy like Nginx)
- [ ] Firewall configured to allow only necessary ports
- [ ] Database accessible only from application server
- [ ] Regular security updates applied
- [ ] Strong passwords enforced
- [ ] CSRF protection enabled (already configured)
- [ ] SQL injection protected (JPA/Hibernate parameterized queries)

## Performance Optimization

- [ ] Database indexes created for frequently queried columns
- [ ] Connection pooling configured
- [ ] Caching configured if needed
- [ ] Static resources served efficiently
- [ ] Gzip compression enabled (via reverse proxy)

## Troubleshooting

### Application won't start
1. Check Java version: `java -version` (must be 21+)
2. Check database connectivity
3. Review application logs
4. Verify port 8080 is not in use

### Database connection errors
1. Verify MySQL is running: `sudo systemctl status mysql`
2. Test connection: `mysql -h localhost -u username -p`
3. Check firewall rules
4. Verify database name and credentials

### Pages not loading
1. Check application is running: `curl http://localhost:8080/actuator/health`
2. Check browser console for errors
3. Verify templates are in classpath
4. Check Thymeleaf configuration

## Production URL Structure

After deployment, your application will be accessible at:
- Home: `http://your-domain.com/`
- Products: `http://your-domain.com/products`
- Login: `http://your-domain.com/login`
- Register: `http://your-domain.com/register`

## Reverse Proxy Configuration (Nginx)

```nginx
server {
    listen 80;
    server_name your-domain.com;

    location / {
        proxy_pass http://localhost:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }
}
```

## Support

For issues or questions:
1. Check logs for error messages
2. Review documentation in README.md
3. Check TRANSFORMATION_SUMMARY.md for architectural details
4. Open an issue in the repository
