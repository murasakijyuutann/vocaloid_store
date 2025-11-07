# Docker Deployment Guide

## Quick Start

### Production Deployment

1. **Create environment file:**
   ```bash
   cp .env.example .env
   # Edit .env and set secure passwords
   ```

2. **Build and start services:**
   ```bash
   docker-compose -f docker-compose.production.yml up -d
   ```

3. **View logs:**
   ```bash
   docker-compose -f docker-compose.production.yml logs -f app
   ```

4. **Access application:**
   - Application: http://localhost:8080
   - Admin login: admin@vocalocart.com / password123

5. **Stop services:**
   ```bash
   docker-compose -f docker-compose.production.yml down
   ```

### Development Deployment (Hot Reload)

1. **Start development environment:**
   ```bash
   docker-compose -f docker-compose.dev.yml up -d
   ```

2. **Access application:**
   - Application: http://localhost:8081
   - Code changes will automatically reload

3. **Stop development environment:**
   ```bash
   docker-compose -f docker-compose.dev.yml down
   ```

## Architecture

### Production Stack
- **App Container**: Multi-stage build with Maven + Eclipse Temurin 21 JRE
- **Database**: MySQL 8.0 with persistent volume
- **Network**: Isolated bridge network
- **Security**: Non-root user, health checks, environment variables

### Development Stack
- **App Container**: Hot reload with Spring Boot DevTools
- **Database**: MySQL 8.0 (port 3307 to avoid conflicts)
- **Volumes**: Source code mounted for live editing

## Commands

### Build only
```bash
docker-compose -f docker-compose.production.yml build
```

### Rebuild without cache
```bash
docker-compose -f docker-compose.production.yml build --no-cache
```

### View container status
```bash
docker-compose -f docker-compose.production.yml ps
```

### Execute commands in app container
```bash
docker-compose -f docker-compose.production.yml exec app sh
```

### Database backup
```bash
docker-compose -f docker-compose.production.yml exec mysql mysqldump -u root -p vocalocart > backup.sql
```

### Clean up (remove volumes)
```bash
docker-compose -f docker-compose.production.yml down -v
```

## Environment Variables

| Variable | Default | Description |
|----------|---------|-------------|
| `SERVER_PORT` | 8080 | Application port |
| `DB_URL` | jdbc:mysql://mysql:3306/vocalocart | Database connection URL |
| `DB_USERNAME` | vocalocart | Database user |
| `DB_PASSWORD` | changeme | Database password |
| `MYSQL_ROOT_PASSWORD` | changeme | MySQL root password |
| `JPA_DDL_AUTO` | update | Hibernate DDL mode |
| `SPRING_PROFILES_ACTIVE` | prod | Active Spring profile |

## Troubleshooting

### App won't start
```bash
# Check logs
docker-compose -f docker-compose.production.yml logs app

# Check database health
docker-compose -f docker-compose.production.yml exec mysql mysqladmin ping -h localhost
```

### Permission denied errors
```bash
# Rebuild with no cache
docker-compose -f docker-compose.production.yml build --no-cache
```

### Database connection refused
```bash
# Wait for MySQL to be ready (check healthcheck)
docker-compose -f docker-compose.production.yml ps
```

## Production Recommendations

1. **Change default passwords** in `.env`
2. **Use secrets management** for production (Docker Swarm, Kubernetes)
3. **Enable HTTPS** with reverse proxy (nginx, Traefik)
4. **Set up backups** for MySQL data volume
5. **Configure monitoring** (Prometheus, Grafana)
6. **Use orchestration** (Docker Swarm, Kubernetes) for high availability
