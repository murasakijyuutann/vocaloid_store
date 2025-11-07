# 🚂 Railway Deployment Guide - VocaloCart

## Prerequisites
- GitHub account with your code pushed
- Railway account (sign up free at https://railway.app)

---

## 🚀 Quick Deploy (5 Minutes)

### Step 1: Create Railway Project

1. Go to https://railway.app
2. Click **"Start a New Project"**
3. Select **"Deploy from GitHub repo"**
4. Authorize Railway to access your GitHub
5. Select repository: `murasakijyuutann/vocaloid_store`

### Step 2: Add MySQL Database

1. In your Railway project, click **"New"**
2. Select **"Database"** → **"Add MySQL"**
3. Railway will provision a MySQL 8 instance
4. **Save these credentials** (found in MySQL service → Variables tab):
   - `MYSQLHOST`
   - `MYSQLPORT` 
   - `MYSQLDATABASE`
   - `MYSQLUSER`
   - `MYSQLPASSWORD`

### Step 3: Configure Application Environment Variables

Click on your **app service** → **Variables** tab → Add these:

#### Required Variables:
```bash
# Database Configuration (use values from MySQL service)
DB_URL=jdbc:mysql://${{MYSQLHOST}}:${{MYSQLPORT}}/${{MYSQLDATABASE}}?serverTimezone=Asia/Seoul&characterEncoding=UTF-8
DB_USERNAME=${{MYSQLUSER}}
DB_PASSWORD=${{MYSQLPASSWORD}}

# Application Configuration
SERVER_PORT=8080
JPA_DDL_AUTO=update
SPRING_PROFILES_ACTIVE=prod
```

#### Optional Variables:
```bash
# Mail Configuration (if you want email features)
MAIL_HOST=smtp.sendgrid.net
MAIL_PORT=587
MAIL_USERNAME=apikey
MAIL_PASSWORD=your-sendgrid-api-key
```

### Step 4: Configure Build Settings

1. Go to **Settings** tab
2. Under **Build**, select:
   - **Builder**: Dockerfile
   - **Dockerfile Path**: `Dockerfile.production`
3. Under **Deploy**:
   - **Start Command**: (leave empty, Dockerfile handles it)
   - **Health Check Path**: `/actuator/health`

### Step 5: Deploy!

1. Click **"Deploy"** button
2. Watch the build logs (takes 3-5 minutes)
3. Once deployed, Railway gives you a public URL like:
   ```
   https://vocaloidshop-production.up.railway.app
   ```

### Step 6: Generate Domain (Optional)

1. Go to **Settings** → **Domains**
2. Click **"Generate Domain"**
3. You'll get: `your-app.up.railway.app`
4. Or add custom domain if you have one

---

## 📊 Post-Deployment

### Test Your Application

1. Visit your Railway URL
2. Register a new account
3. Test shopping cart and checkout
4. Login as admin:
   - Email: `admin@vocalocart.com`
   - Password: `password123`

### View Logs

1. Click on your app service
2. Go to **"Deployments"** tab
3. Click on latest deployment
4. View real-time logs

### Monitor Resources

Railway free tier includes:
- ✅ $5 credit/month (enough for small apps)
- ✅ 500 MB RAM
- ✅ 1 GB storage
- ✅ Shared CPU

---

## 🔧 Troubleshooting

### Build Failed?

**Check Dockerfile:**
```bash
# Verify Dockerfile.production exists
ls -la Dockerfile.production

# Test locally first
docker build -f Dockerfile.production -t vocaloidshop .
```

**Common Issues:**
- Maven dependencies not downloading → Check `pom.xml`
- OOM error → Add to Dockerfile: `-Xmx512m`

### Database Connection Failed?

**Verify environment variables:**
1. Go to Variables tab
2. Check `DB_URL` format:
   ```
   jdbc:mysql://HOST:PORT/DATABASE?serverTimezone=Asia/Seoul
   ```
3. Ensure `${{VARIABLE}}` syntax is used for Railway variables

**Test connection:**
```bash
# In Railway CLI or logs
curl $DATABASE_URL
```

### Application Won't Start?

**Check logs for:**
- Port binding issues (use `SERVER_PORT=8080`)
- Missing environment variables
- Database migration errors

**Fix port binding:**
Railway automatically sets `PORT` variable. Update `application.yml`:
```yaml
server:
  port: ${PORT:8080}
```

### Health Check Failing?

1. Go to **Settings** → **Deploy**
2. Change health check path to:
   ```
   /actuator/health
   ```
3. Ensure Spring Actuator is enabled in `pom.xml`

---

## 💰 Cost Estimation

### Free Tier (Hobby Plan)
- **Monthly Credit**: $5
- **Typical Usage**: 
  - Small app: $3-4/month
  - With MySQL: $5-7/month
- **Upgrade**: $5/month for additional credits

### Tips to Reduce Costs:
1. **Sleep inactive apps** (Railway auto-sleeps after 30 min)
2. **Use shared MySQL** (don't provision multiple databases)
3. **Optimize Dockerfile** (smaller image = faster builds)

---

## 🔄 Continuous Deployment

Railway auto-deploys on every push to `main` branch!

```bash
# Make changes locally
git add .
git commit -m "feat: add new feature"
git push origin main

# Railway automatically:
# 1. Detects push
# 2. Builds new image
# 3. Deploys with zero downtime
```

---

## 🌐 Custom Domain Setup

### Add Custom Domain:

1. Go to **Settings** → **Domains**
2. Click **"Add Domain"**
3. Enter your domain: `vocaloidshop.com`
4. Add DNS records at your domain registrar:
   ```
   CNAME www -> your-app.up.railway.app
   A     @   -> Railway IP (shown in settings)
   ```
5. Wait for DNS propagation (5-30 minutes)

### Free SSL:
Railway automatically provisions Let's Encrypt SSL certificates!

---

## 📈 Scaling (Paid Plans)

Need more resources?

**Pro Plan ($20/month):**
- Up to 8 GB RAM
- Priority builds
- Team collaboration

**Upgrade settings:**
1. Go to project settings
2. Click **"Upgrade to Pro"**
3. Set resource limits:
   - Memory: 1-8 GB
   - CPU: Shared to Dedicated

---

## 🎯 Production Checklist

Before going live:

- [ ] Change default admin password
- [ ] Set `JPA_DDL_AUTO=validate` (not `update`)
- [ ] Enable HTTPS redirect
- [ ] Set up database backups
- [ ] Configure error logging (Sentry, LogDNA)
- [ ] Add monitoring (Datadog, New Relic)
- [ ] Set up alerts for downtime
- [ ] Test payment flows thoroughly
- [ ] Add rate limiting
- [ ] Enable CORS properly

---

## 🆘 Support

**Railway Documentation:**
- https://docs.railway.app

**Railway Discord:**
- https://discord.gg/railway

**Project Issues:**
- https://github.com/murasakijyuutann/vocaloid_store/issues

---

## 🎉 Success!

Your VocaloCart is now live on Railway! 

**Share your live URL:**
```
https://your-app.up.railway.app
```

**Add to your resume/portfolio:**
- ✅ Live Demo Link
- ✅ GitHub Repository
- ✅ Tech Stack Documentation
- ✅ Deployment Documentation

Perfect for interviews! 🚀
