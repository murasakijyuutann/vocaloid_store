# Render Deployment Guide - VocaloCart

Complete step-by-step guide to deploy VocaloCart to Render.

---

## 🚀 Quick Overview

**Deployment Time:** 15-20 minutes
**Cost:** Free tier available (750 hours/month)
**Database:** PostgreSQL (recommended) or external MySQL

---

## 📋 Prerequisites

- [x] GitHub account with vocaloid_store repository
- [x] Render account (sign up at https://render.com)
- [x] All changes committed and pushed to `main` branch

---

## 🎯 Deployment Steps

### **Step 1: Sign Up / Login to Render**

1. Go to **https://render.com**
2. Click **"Get Started"** or **"Sign In"**
3. Choose **"Sign in with GitHub"** (easiest method)
4. Authorize Render to access your GitHub repositories

---

### **Step 2: Create PostgreSQL Database**

⚠️ **Important:** Create database FIRST before deploying app

1. From Render Dashboard, click **"New +"** (top right)
2. Select **"PostgreSQL"**
3. Fill in details:
   - **Name:** `vocalocart-db`
   - **Database:** `vocalocart` (auto-filled)
   - **User:** `vocalocart` (auto-filled)
   - **Region:** Choose closest to you (e.g., Oregon, Frankfurt)
   - **Plan:** **Free** (select this!)

4. Click **"Create Database"**

**Wait 2-3 minutes** for database to provision.

5. Once ready, click on the database name
6. Scroll down to **"Connections"** section
7. **Copy these values** (you'll need them):
   ```
   Internal Database URL: postgresql://...
   External Database URL: postgresql://...
   PGDATABASE: vocalocart
   PGHOST: dpg-xxxxx.oregon-postgres.render.com
   PGPASSWORD: xxxxx
   PGPORT: 5432
   PGUSER: vocalocart
   ```

---

### **Step 3: Create Web Service**

1. Click **"New +"** → **"Web Service"**
2. Click **"Build and deploy from a Git repository"**
3. Click **"Connect account"** if needed, or select your repository:
   - Find **"vocaloid_store"**
   - Click **"Connect"**

4. Configure the service:

#### **Basic Settings:**
```
Name: vocalocart
Region: Same as your database (e.g., Oregon)
Branch: main
Root Directory: (leave blank)
Runtime: Docker
```

#### **Build Settings:**
```
Dockerfile Path: Dockerfile.production
Docker Command: (leave blank - uses Dockerfile's ENTRYPOINT)
```

#### **Instance Type:**
```
Plan: Free (512MB RAM, 750 hours/month)
```

---

### **Step 4: Add Environment Variables**

Scroll down to **"Environment Variables"** section.

Click **"Add from .env"** or **"Add Environment Variable"** and add these:

#### **Option A: Using PostgreSQL (Recommended)**

```bash
# Database Connection (PostgreSQL)
DB_URL=jdbc:postgresql://dpg-xxxxx.oregon-postgres.render.com:5432/vocalocart
DB_USERNAME=vocalocart
DB_PASSWORD=your-postgres-password-from-step-2

# Application Settings
SERVER_PORT=8080
JPA_DDL_AUTO=update
SPRING_PROFILES_ACTIVE=prod

# Optional: Disable Flyway if you don't have PostgreSQL migrations
SPRING_FLYWAY_ENABLED=false
```

**Replace with YOUR values from Step 2!**

#### **Option B: Using External MySQL (PlanetScale/AWS RDS)**

If you prefer MySQL:

```bash
# Database Connection (MySQL)
DB_URL=jdbc:mysql://your-mysql-host:3306/vocalocart?serverTimezone=Asia/Seoul&characterEncoding=UTF-8
DB_USERNAME=your-mysql-user
DB_PASSWORD=your-mysql-password

# Application Settings
SERVER_PORT=8080
JPA_DDL_AUTO=update
SPRING_PROFILES_ACTIVE=prod
```

---

### **Step 5: Configure Health Check**

Scroll to **"Health Check Path"** (optional but recommended):

```
Health Check Path: /actuator/health
```

This tells Render to check if your app is running properly.

---

### **Step 6: Deploy!**

1. Scroll to the bottom
2. Click **"Create Web Service"**

**What happens now:**

```
⏳ Building (5-8 minutes)
   - Cloning repository
   - Building Docker image
   - Maven downloads dependencies
   - Compiling Java code
   - Creating JAR file

⏳ Deploying (2-3 minutes)
   - Starting container
   - Connecting to database
   - Running health checks

✅ Live!
```

**Total time: 8-12 minutes**

---

### **Step 7: Get Your URL**

Once deployed, Render gives you a URL:

```
https://vocalocart.onrender.com
```

**Test it:**
1. Open the URL in browser
2. You should see your VocaloCart homepage!

**Check health endpoint:**
```
https://vocalocart.onrender.com/actuator/health
```

Should return: `{"status":"UP"}`

---

## ⚠️ **PostgreSQL vs MySQL**

Your project currently uses MySQL. To use Render's free PostgreSQL:

### **Option 1: Update pom.xml (Switch to PostgreSQL)**

Add PostgreSQL driver:

```xml
<!-- Add to pom.xml after MySQL driver -->
<dependency>
  <groupId>org.postgresql</groupId>
  <artifactId>postgresql</artifactId>
  <scope>runtime</scope>
</dependency>
```

Update application.yml:

```yaml
spring:
  datasource:
    driver-class-name: ${DB_DRIVER:org.postgresql.Driver}
  jpa:
    properties:
      hibernate:
        dialect: ${DB_DIALECT:org.hibernate.dialect.PostgreSQLDialect}
```

### **Option 2: Use External MySQL (Easier)**

Use a free MySQL service:
- **PlanetScale** (free tier, 5GB storage)
- **Clever Cloud** (free tier, 256MB)
- **Railway MySQL** (free $5 credit/month)

Then just set `DB_URL`, `DB_USERNAME`, `DB_PASSWORD` to point to it.

---

## 🔧 **Troubleshooting**

### **Issue 1: Build Fails - "Out of Memory"**

**Fix:** Add Maven memory limit to Dockerfile.production:

```dockerfile
# In build stage, update this line:
RUN mvn clean package -DskipTests -Dmaven.compiler.fork=false -Xmx512m
```

### **Issue 2: "Connection Refused" to Database**

**Fix:**
1. Make sure database is in "Available" status
2. Use **Internal Database URL** (not External) in `DB_URL`
3. Check `DB_USERNAME` and `DB_PASSWORD` match database credentials

### **Issue 3: App Crashes on Startup**

**Check deploy logs:**
1. Go to your web service dashboard
2. Click **"Logs"** tab
3. Look for Java stack traces

**Common causes:**
- Missing environment variables
- Wrong database URL
- Flyway migration errors (disable with `SPRING_FLYWAY_ENABLED=false`)

### **Issue 4: Health Check Timeout**

**Fix:** Increase timeout in Settings:

1. Go to web service **Settings** tab
2. Scroll to **Health & Alerts**
3. Set **Health Check Grace Period** to **300 seconds**

---

## 💰 **Render Free Tier Limits**

**Web Service:**
- 512MB RAM
- 0.1 CPU
- 750 hours/month (sleeps after 15 min inactivity)
- Shared bandwidth

**PostgreSQL:**
- 256MB RAM
- 1GB storage
- Expires after 90 days

**Important:** Free tier services sleep after 15 minutes of inactivity. First request after sleep takes 30-60 seconds to wake up.

---

## 🎯 **Post-Deployment Checklist**

- [ ] App URL works (https://vocalocart.onrender.com)
- [ ] Health check returns `{"status":"UP"}`
- [ ] Can register new user
- [ ] Can browse products
- [ ] Can add items to cart
- [ ] Admin login works (admin@vocalocart.com / password123)
- [ ] Database persists data (check by creating user, then refresh)

---

## 📊 **Monitoring Your App**

### **View Logs:**
1. Dashboard → Your service → **Logs** tab
2. Filter by:
   - Build logs
   - Deploy logs
   - Runtime logs

### **Check Metrics:**
1. Dashboard → Your service → **Metrics** tab
2. See:
   - CPU usage
   - Memory usage
   - Request count
   - Response time

### **Set Up Alerts:**
1. Settings → **Health & Alerts**
2. Add email notification for deploy failures

---

## 🚀 **Next Steps**

### **Auto-Deploy from GitHub:**
✅ Already enabled! Every push to `main` triggers a new deployment.

### **Custom Domain:**
1. Settings → **Custom Domain**
2. Add your domain (e.g., vocalocart.com)
3. Update DNS records as shown

### **Upgrade to Paid Plan:**
If you need:
- No sleep (always-on)
- More RAM (1GB, 2GB, 4GB)
- Faster CPU

Costs: $7-25/month depending on plan

---

## 📝 **Environment Variables Reference**

| Variable | Example | Description |
|----------|---------|-------------|
| `DB_URL` | `jdbc:postgresql://host:5432/db` | Database connection URL |
| `DB_USERNAME` | `vocalocart` | Database username |
| `DB_PASSWORD` | `secret123` | Database password |
| `SERVER_PORT` | `8080` | Application port (must be 8080) |
| `JPA_DDL_AUTO` | `update` | Hibernate DDL mode |
| `SPRING_PROFILES_ACTIVE` | `prod` | Active Spring profile |
| `SPRING_FLYWAY_ENABLED` | `false` | Disable Flyway migrations |

---

## 🔗 **Useful Links**

- **Render Dashboard:** https://dashboard.render.com
- **Render Docs:** https://render.com/docs
- **PostgreSQL Guide:** https://render.com/docs/databases
- **Docker Deployment:** https://render.com/docs/docker

---

## ✅ **Success!**

Your VocaloCart app should now be live at:

**https://vocalocart.onrender.com**

If you encounter any issues, check the troubleshooting section above or review the deploy logs in Render dashboard.

---

**Made with ❤️ by murasakijyuutann**
