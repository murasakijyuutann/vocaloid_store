# AWS Elastic Beanstalk Deployment Guide

## Prerequisites

1. **AWS Account** with appropriate permissions
2. **AWS CLI** installed and configured
3. **EB CLI** (Elastic Beanstalk Command Line Interface) installed
4. **AWS RDS MySQL Database** (already set up)
5. **Maven** for building the JAR file

## Installation

### Install EB CLI

```bash
# Using pip (Python package manager)
pip install awsebcli --upgrade --user

# Verify installation
eb --version
```

### Configure AWS CLI

```bash
aws configure
# Enter your AWS Access Key ID
# Enter your AWS Secret Access Key
# Default region: us-east-1 (or your preferred region)
# Default output format: json
```

## Quick Deployment Steps

### 1. Build the Application

```bash
# Clean and package the application
mvn clean package -DskipTests

# Verify JAR file exists
ls -lh target/vocaloidshop-0.0.1-SNAPSHOT.jar
```

### 2. Initialize Elastic Beanstalk

```bash
# Initialize EB application (only needed once)
eb init

# Follow the prompts:
# - Select region: us-east-1 (or your RDS region)
# - Application name: vocaloidstore
# - Platform: Corretto 21 running on 64bit Amazon Linux 2023
# - Use CodeCommit: No
# - SSH: Yes (optional, for debugging)
```

### 3. Create Environment and Deploy

```bash
# Create environment and deploy
eb create vocaloidstore-env --single --instance-type t2.micro

# This will:
# - Create an EC2 instance (t2.micro for free tier)
# - Configure the Java environment
# - Deploy your application
# - Set up a load balancer (optional)
# - Create CloudWatch logs
```

### 4. Set Environment Variables

After deployment, configure your AWS RDS connection:

```bash
# Set database connection details
eb setenv \
  DB_URL="jdbc:mysql://your-rds-endpoint.rds.amazonaws.com:3306/vocaloidshop" \
  DB_USERNAME="admin" \
  DB_PASSWORD="your-rds-password" \
  SPRING_PROFILES_ACTIVE="prod"

# Example with actual values:
# eb setenv \
#   DB_URL="jdbc:mysql://vocaloidshop.c9x8y7z6w5v4.us-east-1.rds.amazonaws.com:3306/vocaloidshop" \
#   DB_USERNAME="admin" \
#   DB_PASSWORD="YourSecurePassword123"
```

### 5. Open Your Application

```bash
# Open the deployed application in your browser
eb open
```

## RDS Security Group Configuration

**IMPORTANT:** You need to allow your Elastic Beanstalk EC2 instance to connect to your RDS database.

### Option 1: Using AWS Console

1. Go to **RDS Console** → Select your database → **Connectivity & security** tab
2. Click on the **VPC security group**
3. Click **Edit inbound rules**
4. Add a new rule:
   - **Type:** MySQL/Aurora
   - **Port:** 3306
   - **Source:** Custom → Select the Elastic Beanstalk security group (sg-xxxxxxxx)
   - **Description:** Allow EB to RDS
5. Save rules

### Option 2: Using AWS CLI

```bash
# Get your EB security group ID
EB_SG=$(aws ec2 describe-security-groups \
  --filters "Name=group-name,Values=*vocaloidstore-env*" \
  --query "SecurityGroups[0].GroupId" \
  --output text)

# Get your RDS security group ID
RDS_SG=$(aws rds describe-db-instances \
  --db-instance-identifier your-rds-instance \
  --query "DBInstances[0].VpcSecurityGroups[0].VpcSecurityGroupId" \
  --output text)

# Add inbound rule to RDS security group
aws ec2 authorize-security-group-ingress \
  --group-id $RDS_SG \
  --protocol tcp \
  --port 3306 \
  --source-group $EB_SG
```

## Useful Commands

### Deploy Updates

```bash
# Build new version
mvn clean package -DskipTests

# Deploy to Elastic Beanstalk
eb deploy
```

### View Logs

```bash
# View recent logs
eb logs

# Stream logs in real-time
eb logs --stream
```

### Check Status

```bash
# Check environment status
eb status

# Check health
eb health
```

### SSH into Instance

```bash
# Connect to EC2 instance
eb ssh
```

### Environment Management

```bash
# List all environments
eb list

# Terminate environment (to save costs)
eb terminate vocaloidstore-env

# Restart application
eb restart
```

## Configuration Files Explained

### `.ebextensions/01_environment.config`
- Sets JVM memory limits (-Xmx512m for t2.micro)
- Configures Spring profile to 'prod'
- Sets port to 5000 (EB default)
- Uses t2.micro instance (free tier eligible)

### `.ebextensions/02_nginx.config`
- Increases max upload size to 20MB
- Configures nginx reverse proxy

### `Procfile`
- Tells EB how to start your application
- Sets server port to 5000

### `.elasticbeanstalk/config.yml`
- EB CLI configuration
- Specifies deployment artifact (JAR file)
- Sets platform to Corretto 21 (Java 21)

## Troubleshooting

### Application Won't Start

```bash
# Check logs for errors
eb logs

# Common issues:
# - Database connection failed: Check RDS security group
# - Port mismatch: Verify SERVER_PORT=5000
# - Memory issues: Check JVM settings in .ebextensions
```

### Database Connection Timeout

```bash
# Test from EB instance
eb ssh
mysql -h your-rds-endpoint.rds.amazonaws.com -u admin -p

# If this fails, check:
# 1. RDS security group allows EB security group
# 2. RDS is in the same VPC as EB
# 3. RDS endpoint is correct
```

### Health Check Failed

```bash
# Check environment health
eb health --refresh

# Adjust health check settings if needed
eb config

# Look for:
# aws:elasticbeanstalk:application:
#   Application Healthcheck URL: /actuator/health
```

## Cost Optimization

### Free Tier Resources
- **EC2 t2.micro:** 750 hours/month (free for 12 months)
- **RDS db.t3.micro:** 750 hours/month (free for 12 months)
- **Elastic Beanstalk:** Free (you only pay for AWS resources)

### Terminate When Not Using

```bash
# Terminate environment to stop charges
eb terminate vocaloidstore-env

# Recreate later when needed
eb create vocaloidstore-env --single --instance-type t2.micro
```

## Adding Custom Domain

### 1. Route 53 Configuration

```bash
# Get EB environment URL
eb status | grep CNAME

# Example output: vocaloidstore-env.us-east-1.elasticbeanstalk.com
```

### 2. Add CNAME Record

1. Go to **Route 53** → **Hosted zones** → Your domain
2. Create record:
   - **Record name:** store (or www)
   - **Record type:** CNAME
   - **Value:** vocaloidstore-env.us-east-1.elasticbeanstalk.com
   - **TTL:** 300

### 3. SSL Certificate (Optional)

```bash
# Request certificate in AWS Certificate Manager
aws acm request-certificate \
  --domain-name yourdomain.com \
  --validation-method DNS \
  --region us-east-1

# Configure in EB console:
# Configuration → Load balancer → Listeners → Add HTTPS
```

## Next Steps After Deployment

1. ✅ Verify application is running: `eb open`
2. ✅ Check database is populated with sample data
3. ✅ Test admin login: `admin@vocalocart.com` / `password123`
4. ✅ Monitor logs: `eb logs --stream`
5. ✅ Set up CloudWatch alarms (optional)
6. ✅ Configure auto-scaling (if needed)
7. ✅ Add custom domain (optional)
8. ✅ Set up CI/CD with GitHub Actions (optional)

## Comparison: Railway vs Elastic Beanstalk

| Feature | Railway | Elastic Beanstalk |
|---------|---------|-------------------|
| **Setup** | Easier | More complex |
| **Database** | Built-in MySQL | Use existing RDS |
| **Cost** | $5/month credit | AWS Free Tier |
| **Scaling** | Automatic | Manual config |
| **Control** | Less | More |
| **Best For** | Quick demos | Production apps |

## Support

- **AWS Documentation:** https://docs.aws.amazon.com/elasticbeanstalk/
- **EB CLI Guide:** https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/eb-cli3.html
- **Troubleshooting:** https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/troubleshooting.html
