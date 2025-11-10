# 카페 키오스크 풀스택 프로젝트 - 완전 통합 구조
## React + Spring Boot REST API

---

## 📚 목차
1. [프로젝트 개요](#프로젝트-개요)
2. [완전한 프로젝트 구조](#완전한-프로젝트-구조)
3. [백엔드 (Spring Boot)](#백엔드-spring-boot)
4. [프론트엔드 (React)](#프론트엔드-react)
5. [데이터베이스 스키마](#데이터베이스-스키마)
6. [초기 설정 가이드](#초기-설정-가이드)
7. [개발 워크플로우](#개발-워크플로우)
8. [배포 가이드](#배포-가이드)

---

## 프로젝트 개요

### 아키텍처
```
┌─────────────────────────────────────────────────────────┐
│                     Client Browser                       │
│              (React SPA on port 5173)                    │
└────────────────────┬────────────────────────────────────┘
                     │ HTTP/HTTPS
                     │ Axios Requests
                     │
┌────────────────────▼────────────────────────────────────┐
│              Spring Boot REST API                        │
│                 (port 8080)                              │
│  ┌────────────────────────────────────────────────┐    │
│  │  REST Controllers (/api/*)                     │    │
│  │  - MenuApiController                           │    │
│  │  - OrderApiController                          │    │
│  └────────────┬───────────────────────────────────┘    │
│               │                                          │
│  ┌────────────▼───────────────────────────────────┐    │
│  │  Service Layer                                  │    │
│  │  - MenuService                                  │    │
│  │  - OrderService                                 │    │
│  └────────────┬───────────────────────────────────┘    │
│               │                                          │
│  ┌────────────▼───────────────────────────────────┐    │
│  │  Repository Layer (Spring Data JPA)            │    │
│  └────────────┬───────────────────────────────────┘    │
└───────────────┼──────────────────────────────────────────┘
                │
┌───────────────▼──────────────────────────────────────────┐
│                    MySQL Database                         │
│         (categories, menu_items, orders, order_items)     │
└───────────────────────────────────────────────────────────┘
```

### 기술 스택
- **Frontend**: React 18, Vite, React Router v6, Axios, Bootstrap 5
- **Backend**: Spring Boot 3.5.6, Spring Data JPA, Spring Web
- **Database**: MySQL 8.0
- **Build Tools**: Maven (Backend), npm (Frontend)

---

## 완전한 프로젝트 구조

```
cafe-kiosk-fullstack/
│
├── README.md                              # 프로젝트 전체 설명
├── .gitignore                             # Git 무시 파일
├── docker-compose.yml                     # Docker 통합 설정 (선택)
│
├── backend/                               # 🟢 Spring Boot 프로젝트
│   ├── pom.xml                            # Maven 설정
│   ├── .env                               # 환경 변수 (DB 비밀번호 등)
│   ├── .gitignore
│   │
│   └── src/
│       ├── main/
│       │   ├── java/com/cafekiosk/
│       │   │   ├── CafekioskApplication.java
│       │   │   │
│       │   │   ├── config/                        # 설정 클래스
│       │   │   │   ├── WebConfig.java             # CORS 설정
│       │   │   │   └── AppConfig.java             # 기타 설정
│       │   │   │
│       │   │   ├── model/                         # 엔티티 (DB 모델)
│       │   │   │   ├── Category.java
│       │   │   │   ├── MenuItem.java
│       │   │   │   ├── Order.java
│       │   │   │   ├── OrderItem.java
│       │   │   │   └── OrderStatus.java
│       │   │   │
│       │   │   ├── repository/                    # JPA 리포지토리
│       │   │   │   ├── CategoryRepository.java
│       │   │   │   ├── MenuItemRepository.java
│       │   │   │   └── OrderRepository.java
│       │   │   │
│       │   │   ├── dto/                           # 데이터 전송 객체
│       │   │   │   ├── request/
│       │   │   │   │   ├── OrderRequest.java
│       │   │   │   │   └── CartItemDTO.java
│       │   │   │   └── response/
│       │   │   │       ├── OrderResponse.java
│       │   │   │       ├── MenuItemResponse.java
│       │   │   │       └── CategoryResponse.java
│       │   │   │
│       │   │   ├── service/                       # 비즈니스 로직
│       │   │   │   ├── MenuService.java
│       │   │   │   └── OrderService.java
│       │   │   │
│       │   │   ├── controller/                    # REST API 컨트롤러
│       │   │   │   ├── MenuApiController.java     # /api/menu/*
│       │   │   │   └── OrderApiController.java    # /api/orders/*
│       │   │   │
│       │   │   ├── exception/                     # 예외 처리
│       │   │   │   ├── GlobalExceptionHandler.java
│       │   │   │   ├── ResourceNotFoundException.java
│       │   │   │   └── ErrorResponse.java
│       │   │   │
│       │   │   └── util/                          # 유틸리티
│       │   │       └── DTOMapper.java
│       │   │
│       │   └── resources/
│       │       ├── application.yml                # Spring Boot 설정
│       │       ├── application-dev.yml            # 개발 환경
│       │       ├── application-prod.yml           # 운영 환경
│       │       ├── data.sql                       # 샘플 데이터
│       │       └── static/                        # 정적 파일 (배포 시 React 빌드)
│       │
│       └── test/
│           └── java/com/cafekiosk/
│               ├── service/
│               │   ├── MenuServiceTest.java
│               │   └── OrderServiceTest.java
│               └── repository/
│                   ├── MenuItemRepositoryTest.java
│                   └── OrderRepositoryTest.java
│
└── frontend/                              # 🔵 React 프로젝트
    ├── package.json                       # npm 설정
    ├── vite.config.js                     # Vite 설정
    ├── .gitignore
    ├── index.html                         # HTML 진입점
    │
    ├── public/                            # 정적 파일
    │   ├── favicon.ico
    │   └── images/
    │       ├── logo.png
    │       └── menu/                      # 메뉴 이미지
    │           ├── americano.jpg
    │           ├── latte.jpg
    │           └── ...
    │
    └── src/
        ├── main.jsx                       # React 진입점
        ├── App.jsx                        # 메인 App 컴포넌트
        ├── App.css                        # 전역 스타일
        │
        ├── api/                           # API 통신
        │   ├── axiosConfig.js             # Axios 설정
        │   └── cafekioskApi.js            # API 함수들
        │
        ├── context/                       # Context API (상태 관리)
        │   └── CartContext.jsx            # 장바구니 상태
        │
        ├── components/                    # 재사용 가능한 컴포넌트
        │   ├── common/
        │   │   ├── Header.jsx
        │   │   ├── Footer.jsx
        │   │   ├── Loading.jsx
        │   │   └── ErrorMessage.jsx
        │   │
        │   ├── menu/
        │   │   ├── CategoryFilter.jsx
        │   │   ├── MenuItem.jsx
        │   │   └── MenuList.jsx
        │   │
        │   ├── cart/
        │   │   ├── Cart.jsx
        │   │   └── CartItem.jsx
        │   │
        │   └── order/
        │       └── OrderComplete.jsx
        │
        ├── pages/                         # 페이지 컴포넌트
        │   ├── HomePage.jsx
        │   ├── MenuPage.jsx
        │   ├── CartPage.jsx
        │   └── OrderCompletePage.jsx
        │
        ├── hooks/                         # 커스텀 훅
        │   └── useAsync.js                # 비동기 처리 훅
        │
        └── utils/                         # 유틸리티 함수
            └── formatters.js              # 가격 포맷 등
```

**총 파일 수**:
- Backend: ~25개
- Frontend: ~25개
- **Total: ~50개 파일**

---

## 백엔드 (Spring Boot)

### 📁 주요 파일별 설명

#### 1. `pom.xml` - Maven 설정

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
    </parent>

    <groupId>com.cafekiosk</groupId>
    <artifactId>cafe-kiosk-backend</artifactId>
    <version>1.0.0</version>
    <name>Cafe Kiosk Backend</name>
    <description>REST API for Cafe Kiosk</description>

    <properties>
        <java.version>21</java.version>
    </properties>

    <dependencies>
        <!-- Spring Boot Web (REST API) -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- Spring Data JPA -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <!-- MySQL Driver -->
        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- Validation -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <!-- DevTools (개발 편의) -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- dotenv (환경 변수) -->
        <dependency>
            <groupId>me.paulschwarz</groupId>
            <artifactId>spring-dotenv</artifactId>
            <version>4.0.0</version>
        </dependency>

        <!-- Test -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

---

#### 2. `application.yml` - Spring Boot 설정

```yaml
spring:
  application:
    name: cafe-kiosk-backend

  # 프로파일 활성화
  profiles:
    active: dev

  # 데이터베이스 설정
  datasource:
    url: jdbc:mysql://localhost:3306/cafe_kiosk?useSSL=false&serverTimezone=Asia/Seoul&characterEncoding=UTF-8
    username: ${DB_USERNAME:root}
    password: ${DB_PASSWORD:password}
    driver-class-name: com.mysql.cj.jdbc.Driver

  # JPA 설정
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
        dialect: org.hibernate.dialect.MySQLDialect
    open-in-view: false

  # Jackson 설정 (JSON)
  jackson:
    serialization:
      write-dates-as-timestamps: false
    time-zone: Asia/Seoul

# 서버 설정
server:
  port: 8080
  servlet:
    context-path: /
    encoding:
      charset: UTF-8
      force: true

# 로깅
logging:
  level:
    com.cafekiosk: DEBUG
    org.hibernate.SQL: DEBUG
    org.springframework.web: INFO
```

---

#### 3. `WebConfig.java` - CORS 설정

```java
package com.cafekiosk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins(
                    "http://localhost:5173",      // Vite dev server
                    "http://localhost:3000",      // Create React App
                    "http://localhost:8080"       // Production (same origin)
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
```

---

#### 4. Entity 예제 - `MenuItem.java`

```java
package com.cafekiosk.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "menu_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "image_url", length = 300)
    private String imageUrl;

    @Column(nullable = false)
    @Builder.Default
    private Boolean available = true;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonBackReference
    private Category category;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // JSON 직렬화를 위한 카테고리 정보
    @JsonProperty("categoryId")
    public Long getCategoryId() {
        return category != null ? category.getId() : null;
    }

    @JsonProperty("categoryName")
    public String getCategoryName() {
        return category != null ? category.getName() : null;
    }
}
```

---

#### 5. REST Controller - `MenuApiController.java`

```java
package com.cafekiosk.controller;

import com.cafekiosk.model.Category;
import com.cafekiosk.model.MenuItem;
import com.cafekiosk.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class MenuApiController {

    private final MenuService menuService;

    /**
     * 모든 카테고리 조회
     * GET /api/menu/categories
     */
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories() {
        log.info("GET /api/menu/categories - 카테고리 목록 조회");
        List<Category> categories = menuService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    /**
     * 모든 메뉴 조회
     * GET /api/menu/items
     */
    @GetMapping("/items")
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        log.info("GET /api/menu/items - 전체 메뉴 조회");
        List<MenuItem> items = menuService.getAllAvailableMenuItems();
        return ResponseEntity.ok(items);
    }

    /**
     * 카테고리별 메뉴 조회
     * GET /api/menu/items/category/{categoryId}
     */
    @GetMapping("/items/category/{categoryId}")
    public ResponseEntity<List<MenuItem>> getMenuItemsByCategory(
            @PathVariable Long categoryId) {
        log.info("GET /api/menu/items/category/{} - 카테고리별 메뉴 조회", categoryId);
        List<MenuItem> items = menuService.getMenuItemsByCategory(categoryId);
        return ResponseEntity.ok(items);
    }

    /**
     * 특정 메뉴 조회
     * GET /api/menu/items/{id}
     */
    @GetMapping("/items/{id}")
    public ResponseEntity<MenuItem> getMenuItem(@PathVariable Long id) {
        log.info("GET /api/menu/items/{} - 메뉴 상세 조회", id);
        MenuItem item = menuService.getMenuItemById(id);
        return ResponseEntity.ok(item);
    }
}
```

---

#### 6. DTO - `OrderRequest.java`

```java
package com.cafekiosk.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {

    @Size(max = 100, message = "이름은 100자를 초과할 수 없습니다")
    private String customerName;

    @NotEmpty(message = "주문 항목이 비어있습니다")
    @Valid
    private List<CartItemDTO> items;
}
```

```java
package com.cafekiosk.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemDTO {

    @NotNull(message = "메뉴 ID는 필수입니다")
    private Long menuItemId;

    private String menuItemName;

    @NotNull(message = "가격은 필수입니다")
    private BigDecimal price;

    @NotNull(message = "수량은 필수입니다")
    @Min(value = 1, message = "수량은 1 이상이어야 합니다")
    private Integer quantity;

    private BigDecimal subtotal;
}
```

---

#### 7. Service - `OrderService.java`

```java
package com.cafekiosk.service;

import com.cafekiosk.dto.request.CartItemDTO;
import com.cafekiosk.dto.request.OrderRequest;
import com.cafekiosk.dto.response.OrderResponse;
import com.cafekiosk.exception.ResourceNotFoundException;
import com.cafekiosk.model.*;
import com.cafekiosk.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final MenuService menuService;

    /**
     * 주문 생성
     */
    public OrderResponse createOrder(OrderRequest request) {
        log.info("주문 생성 시작 - 고객: {}, 항목 수: {}",
                 request.getCustomerName(), request.getItems().size());

        // 주문 번호 생성
        String orderNumber = generateOrderNumber();

        // 총액 계산
        BigDecimal totalAmount = request.getItems().stream()
                .map(CartItemDTO::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 주문 엔티티 생성
        Order order = Order.builder()
                .orderNumber(orderNumber)
                .customerName(request.getCustomerName())
                .totalAmount(totalAmount)
                .status(OrderStatus.PENDING)
                .build();

        // 주문 항목 추가
        for (CartItemDTO cartItem : request.getItems()) {
            MenuItem menuItem = menuService.getMenuItemById(cartItem.getMenuItemId());

            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .menuItem(menuItem)
                    .quantity(cartItem.getQuantity())
                    .price(cartItem.getPrice())
                    .build();
            orderItem.calculateSubtotal();

            order.getOrderItems().add(orderItem);
        }

        // 저장
        Order savedOrder = orderRepository.save(order);
        log.info("주문 생성 완료 - 주문번호: {}", orderNumber);

        return convertToResponse(savedOrder);
    }

    /**
     * 주문 조회
     */
    @Transactional(readOnly = true)
    public OrderResponse getOrderByNumber(String orderNumber) {
        log.info("주문 조회 - 주문번호: {}", orderNumber);
        Order order = orderRepository.findByOrderNumber(orderNumber)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "주문을 찾을 수 없습니다: " + orderNumber));
        return convertToResponse(order);
    }

    /**
     * 주문 번호 생성
     */
    private String generateOrderNumber() {
        String dateStr = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        long count = orderRepository.count() + 1;
        return String.format("ORD-%s-%04d", dateStr, count);
    }

    /**
     * Order → OrderResponse 변환
     */
    private OrderResponse convertToResponse(Order order) {
        List<CartItemDTO> items = order.getOrderItems().stream()
                .map(orderItem -> CartItemDTO.builder()
                        .menuItemId(orderItem.getMenuItem().getId())
                        .menuItemName(orderItem.getMenuItem().getName())
                        .price(orderItem.getPrice())
                        .quantity(orderItem.getQuantity())
                        .subtotal(orderItem.getSubtotal())
                        .build())
                .collect(Collectors.toList());

        return OrderResponse.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .customerName(order.getCustomerName())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .items(items)
                .orderedAt(order.getOrderedAt())
                .build();
    }
}
```

---

#### 8. Exception Handler - `GlobalExceptionHandler.java`

```java
package com.cafekiosk.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(
            ResourceNotFoundException ex, WebRequest request) {
        log.error("Resource not found: {}", ex.getMessage());

        ErrorResponse error = ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(
            MethodArgumentNotValidException ex) {
        log.error("Validation error: {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("message", "입력 값이 올바르지 않습니다");
        response.put("errors", errors);
        response.put("timestamp", LocalDateTime.now());

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex, WebRequest request) {
        log.error("Unexpected error: ", ex);

        ErrorResponse error = ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("서버 오류가 발생했습니다: " + ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
```

---

## 프론트엔드 (React)

### 📁 주요 파일별 설명

#### 1. `package.json`

```json
{
  "name": "cafe-kiosk-frontend",
  "private": true,
  "version": "1.0.0",
  "type": "module",
  "scripts": {
    "dev": "vite",
    "build": "vite build",
    "preview": "vite preview",
    "lint": "eslint . --ext js,jsx"
  },
  "dependencies": {
    "axios": "^1.6.5",
    "bootstrap": "^5.3.2",
    "react": "^18.2.0",
    "react-dom": "^18.2.0",
    "react-router-dom": "^6.21.0"
  },
  "devDependencies": {
    "@types/react": "^18.2.48",
    "@types/react-dom": "^18.2.18",
    "@vitejs/plugin-react": "^4.2.1",
    "eslint": "^8.56.0",
    "eslint-plugin-react": "^7.33.2",
    "vite": "^5.0.11"
  }
}
```

---

#### 2. `vite.config.js`

```javascript
import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

export default defineConfig({
  plugins: [react()],
  server: {
    port: 5173,
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true,
        secure: false,
      }
    }
  },
  build: {
    outDir: 'dist',
    sourcemap: true,
  }
})
```

---

#### 3. `src/api/axiosConfig.js`

```javascript
import axios from 'axios';

const axiosInstance = axios.create({
  baseURL: '/api',
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json',
  },
});

// 요청 인터셉터
axiosInstance.interceptors.request.use(
  (config) => {
    console.log(`[API Request] ${config.method?.toUpperCase()} ${config.url}`);
    return config;
  },
  (error) => {
    console.error('[API Request Error]', error);
    return Promise.reject(error);
  }
);

// 응답 인터셉터
axiosInstance.interceptors.response.use(
  (response) => {
    console.log(`[API Response] ${response.config.url}`, response.data);
    return response;
  },
  (error) => {
    console.error('[API Response Error]', error.response?.data || error.message);

    if (error.response) {
      // 서버가 응답했지만 에러
      const { status, data } = error.response;

      if (status === 404) {
        console.error('리소스를 찾을 수 없습니다:', data.message);
      } else if (status === 400) {
        console.error('잘못된 요청:', data.errors || data.message);
      } else if (status === 500) {
        console.error('서버 오류:', data.message);
      }
    } else if (error.request) {
      // 요청은 보냈지만 응답이 없음
      console.error('서버가 응답하지 않습니다. 네트워크를 확인하세요.');
    }

    return Promise.reject(error);
  }
);

export default axiosInstance;
```

---

#### 4. `src/api/cafekioskApi.js`

```javascript
import axiosInstance from './axiosConfig';

// ============================================
// Menu API
// ============================================

export const getCategories = async () => {
  const response = await axiosInstance.get('/menu/categories');
  return response.data;
};

export const getAllMenuItems = async () => {
  const response = await axiosInstance.get('/menu/items');
  return response.data;
};

export const getMenuItemsByCategory = async (categoryId) => {
  const response = await axiosInstance.get(`/menu/items/category/${categoryId}`);
  return response.data;
};

export const getMenuItemById = async (id) => {
  const response = await axiosInstance.get(`/menu/items/${id}`);
  return response.data;
};

// ============================================
// Order API
// ============================================

export const createOrder = async (orderData) => {
  const response = await axiosInstance.post('/orders', orderData);
  return response.data;
};

export const getOrderByNumber = async (orderNumber) => {
  const response = await axiosInstance.get(`/orders/${orderNumber}`);
  return response.data;
};

// Export default
export default {
  getCategories,
  getAllMenuItems,
  getMenuItemsByCategory,
  getMenuItemById,
  createOrder,
  getOrderByNumber,
};
```

---

#### 5. `src/utils/formatters.js`

```javascript
/**
 * 가격 포맷 (원화)
 */
export const formatPrice = (price) => {
  return new Intl.NumberFormat('ko-KR', {
    style: 'currency',
    currency: 'KRW',
  }).format(price);
};

/**
 * 가격 포맷 (숫자만)
 */
export const formatNumber = (number) => {
  return new Intl.NumberFormat('ko-KR').format(number);
};

/**
 * 날짜 포맷
 */
export const formatDate = (dateString) => {
  if (!dateString) return '';

  const date = new Date(dateString);
  return date.toLocaleString('ko-KR', {
    year: 'numeric',
    month: '2-digit',
    day: '2-digit',
    hour: '2-digit',
    minute: '2-digit',
    second: '2-digit',
  });
};

/**
 * 주문 상태 한글 변환
 */
export const formatOrderStatus = (status) => {
  const statusMap = {
    PENDING: '대기중',
    PREPARING: '준비중',
    READY: '준비완료',
    COMPLETED: '완료',
    CANCELLED: '취소',
  };

  return statusMap[status] || status;
};

export default {
  formatPrice,
  formatNumber,
  formatDate,
  formatOrderStatus,
};
```

---

#### 6. `src/hooks/useAsync.js`

```javascript
import { useState, useEffect } from 'react';

/**
 * 비동기 작업을 처리하는 커스텀 훅
 */
const useAsync = (asyncFunction, immediate = true) => {
  const [status, setStatus] = useState('idle');
  const [data, setData] = useState(null);
  const [error, setError] = useState(null);

  const execute = async (...params) => {
    setStatus('loading');
    setData(null);
    setError(null);

    try {
      const response = await asyncFunction(...params);
      setData(response);
      setStatus('success');
      return response;
    } catch (err) {
      setError(err);
      setStatus('error');
      throw err;
    }
  };

  useEffect(() => {
    if (immediate) {
      execute();
    }
  }, []);

  return { execute, status, data, error };
};

export default useAsync;
```

---

#### 7. `src/components/common/Loading.jsx`

```javascript
import React from 'react';

const Loading = ({ message = '로딩 중...' }) => {
  return (
    <div className="text-center my-5">
      <div className="spinner-border text-primary" role="status">
        <span className="visually-hidden">Loading...</span>
      </div>
      <p className="mt-3">{message}</p>
    </div>
  );
};

export default Loading;
```

---

#### 8. `src/components/common/ErrorMessage.jsx`

```javascript
import React from 'react';

const ErrorMessage = ({ message, onRetry }) => {
  return (
    <div className="alert alert-danger" role="alert">
      <h4 className="alert-heading">오류 발생</h4>
      <p>{message || '알 수 없는 오류가 발생했습니다.'}</p>
      {onRetry && (
        <button className="btn btn-danger mt-2" onClick={onRetry}>
          다시 시도
        </button>
      )}
    </div>
  );
};

export default ErrorMessage;
```

---

#### 9. `src/App.jsx`

```javascript
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { CartProvider } from './context/CartContext';

// Components
import Header from './components/common/Header';
import Footer from './components/common/Footer';

// Pages
import HomePage from './pages/HomePage';
import MenuPage from './pages/MenuPage';
import CartPage from './pages/CartPage';
import OrderCompletePage from './pages/OrderCompletePage';

function App() {
  return (
    <CartProvider>
      <Router>
        <div className="d-flex flex-column min-vh-100">
          <Header />

          <main className="container my-4 flex-grow-1">
            <Routes>
              <Route path="/" element={<HomePage />} />
              <Route path="/menu" element={<MenuPage />} />
              <Route path="/cart" element={<CartPage />} />
              <Route path="/order-complete" element={<OrderCompletePage />} />
            </Routes>
          </main>

          <Footer />
        </div>
      </Router>
    </CartProvider>
  );
}

export default App;
```

---

## 데이터베이스 스키마

### ERD

```
┌─────────────────┐
│   categories    │
├─────────────────┤
│ id (PK)         │
│ name (UNIQUE)   │
│ description     │
│ display_order   │
│ created_at      │
└────────┬────────┘
         │
         │ 1:N
         ▼
┌─────────────────┐
│   menu_items    │
├─────────────────┤
│ id (PK)         │
│ name            │
│ description     │
│ price           │
│ image_url       │
│ available       │
│ category_id(FK) │
│ created_at      │
│ updated_at      │
└────────┬────────┘
         │
         │ N:M
         ▼
┌─────────────────┐         ┌─────────────────┐
│     orders      │ 1:N     │  order_items    │
├─────────────────┤◄────────┤─────────────────┤
│ id (PK)         │         │ id (PK)         │
│ order_number    │         │ order_id (FK)   │
│ customer_name   │         │ menu_item_id(FK)│
│ total_amount    │         │ quantity        │
│ status          │         │ price           │
│ ordered_at      │         │ subtotal        │
│ completed_at    │         └─────────────────┘
│ created_at      │
│ updated_at      │
└─────────────────┘
```

### SQL 스크립트

**`backend/src/main/resources/data.sql`:**

```sql
-- 카테고리 데이터
INSERT INTO categories (id, name, description, display_order, created_at) VALUES
(1, '커피', '신선한 원두로 만든 커피', 1, NOW()),
(2, '디저트', '달콤한 디저트', 2, NOW()),
(3, '음료', '시원한 음료', 3, NOW())
ON DUPLICATE KEY UPDATE name=name;

-- 메뉴 아이템 데이터
INSERT INTO menu_items (id, name, description, price, image_url, available, category_id, created_at, updated_at) VALUES
-- 커피
(1, '아메리카노', '깔끔한 에스프레소와 물', 3000.00, '/images/menu/americano.jpg', TRUE, 1, NOW(), NOW()),
(2, '카페라떼', '부드러운 우유와 에스프레소', 3500.00, '/images/menu/latte.jpg', TRUE, 1, NOW(), NOW()),
(3, '카푸치노', '풍성한 거품과 에스프레소', 3500.00, '/images/menu/cappuccino.jpg', TRUE, 1, NOW(), NOW()),
(4, '바닐라 라떼', '달콤한 바닐라 시럽', 4000.00, '/images/menu/vanilla-latte.jpg', TRUE, 1, NOW(), NOW()),
(5, '카라멜 마키아또', '카라멜과 에스프레소', 4500.00, '/images/menu/caramel-macchiato.jpg', TRUE, 1, NOW(), NOW()),

-- 디저트
(6, '초콜릿 케이크', '진한 초콜릿 케이크', 5000.00, '/images/menu/choco-cake.jpg', TRUE, 2, NOW(), NOW()),
(7, '치즈케이크', '부드러운 뉴욕 스타일', 5500.00, '/images/menu/cheesecake.jpg', TRUE, 2, NOW(), NOW()),
(8, '크루아상', '바삭한 버터 크루아상', 3000.00, '/images/menu/croissant.jpg', TRUE, 2, NOW(), NOW()),
(9, '마카롱', '달콤한 프랑스 마카롱', 2000.00, '/images/menu/macaron.jpg', TRUE, 2, NOW(), NOW()),
(10, '티라미수', '이탈리아 디저트', 6000.00, '/images/menu/tiramisu.jpg', TRUE, 2, NOW(), NOW()),

-- 음료
(11, '오렌지 주스', '신선한 오렌지 주스', 4000.00, '/images/menu/orange-juice.jpg', TRUE, 3, NOW(), NOW()),
(12, '딸기 스무디', '달콤한 딸기 스무디', 4500.00, '/images/menu/strawberry-smoothie.jpg', TRUE, 3, NOW(), NOW()),
(13, '녹차 라떼', '고소한 녹차 라떼', 4000.00, '/images/menu/green-tea-latte.jpg', TRUE, 3, NOW(), NOW()),
(14, '망고 스무디', '열대 망고 스무디', 5000.00, '/images/menu/mango-smoothie.jpg', TRUE, 3, NOW(), NOW()),
(15, '아이스티', '상큼한 레몬 아이스티', 3500.00, '/images/menu/iced-tea.jpg', TRUE, 3, NOW(), NOW())
ON DUPLICATE KEY UPDATE name=name;
```

---

## 초기 설정 가이드

### 1. 프로젝트 클론 및 설정

```bash
# 1. 프로젝트 폴더 생성
mkdir cafe-kiosk-fullstack
cd cafe-kiosk-fullstack

# 2. Git 초기화
git init
echo "node_modules/" >> .gitignore
echo "target/" >> .gitignore
echo ".env" >> .gitignore
echo "dist/" >> .gitignore
```

---

### 2. 백엔드 설정

```bash
# Spring Initializr에서 프로젝트 다운로드
# https://start.spring.io/
# - Group: com.cafekiosk
# - Artifact: cafe-kiosk-backend
# - Dependencies: Web, JPA, MySQL, Lombok, Validation

# backend 폴더로 압축 해제
unzip demo.zip -d backend
cd backend

# .env 파일 생성
cat > .env << EOF
DB_USERNAME=root
DB_PASSWORD=your_password
EOF

# MySQL 데이터베이스 생성
mysql -u root -p
CREATE DATABASE cafe_kiosk CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
EXIT;

# 백엔드 실행
mvn spring-boot:run
```

---

### 3. 프론트엔드 설정

```bash
# 루트 폴더로 돌아가기
cd ..

# React 프로젝트 생성
npm create vite@latest frontend -- --template react
cd frontend

# 의존성 설치
npm install axios react-router-dom bootstrap

# 개발 서버 실행
npm run dev
```

---

### 4. 전체 실행

**터미널 1 (백엔드):**
```bash
cd backend
mvn spring-boot:run
# Running on http://localhost:8080
```

**터미널 2 (프론트엔드):**
```bash
cd frontend
npm run dev
# Running on http://localhost:5173
```

**브라우저:**
```
http://localhost:5173
```

---

## 개발 워크플로우

### 팀 역할 분담

#### 팀원 1-2: 백엔드 (Backend)
**파일:**
- Entity, Repository (2일)
- Service, DTO (2일)
- REST Controller (1일)
- Exception Handler (1일)

**총 예상 시간:** 6일

---

#### 팀원 3-4: 프론트엔드 (Frontend)
**파일:**
- API 통신, Context (1일)
- 공통 컴포넌트 (1일)
- 메뉴/장바구니 컴포넌트 (2일)
- 페이지 컴포넌트 (2일)

**총 예상 시간:** 6일

---

### Git 브랜치 전략

```bash
main
├── backend-dev      # 백엔드 개발 브랜치
│   ├── feature/entity
│   ├── feature/service
│   └── feature/controller
│
└── frontend-dev     # 프론트엔드 개발 브랜치
    ├── feature/components
    ├── feature/pages
    └── feature/api
```

**워크플로우:**
1. 기능별 브랜치 생성 (`feature/xxx`)
2. 작업 완료 후 Pull Request
3. 코드 리뷰 후 `dev` 브랜치에 병합
4. 테스트 완료 후 `main` 브랜치에 병합

---

## 배포 가이드

### 1. 프론트엔드 빌드

```bash
cd frontend
npm run build
# dist/ 폴더에 빌드 파일 생성
```

---

### 2. Spring Boot에 통합

**방법 1: 수동 복사**
```bash
# 빌드 파일을 Spring Boot static 폴더로 복사
cp -r frontend/dist/* backend/src/main/resources/static/
```

**방법 2: Maven 플러그인** (권장)

`backend/pom.xml`에 추가:
```xml
<build>
    <plugins>
        <!-- Frontend build plugin -->
        <plugin>
            <groupId>com.github.eirslett</groupId>
            <artifactId>frontend-maven-plugin</artifactId>
            <version>1.15.0</version>
            <configuration>
                <workingDirectory>../frontend</workingDirectory>
            </configuration>
            <executions>
                <execution>
                    <id>install node and npm</id>
                    <goals>
                        <goal>install-node-and-npm</goal>
                    </goals>
                    <configuration>
                        <nodeVersion>v20.10.0</nodeVersion>
                    </configuration>
                </execution>
                <execution>
                    <id>npm install</id>
                    <goals>
                        <goal>npm</goal>
                    </goals>
                    <configuration>
                        <arguments>install</arguments>
                    </configuration>
                </execution>
                <execution>
                    <id>npm run build</id>
                    <goals>
                        <goal>npm</goal>
                    </goals>
                    <configuration>
                        <arguments>run build</arguments>
                    </configuration>
                </execution>
            </executions>
        </plugin>

        <!-- Copy frontend build to static -->
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-resources-plugin</artifactId>
            <executions>
                <execution>
                    <id>copy-frontend</id>
                    <phase>process-resources</phase>
                    <goals>
                        <goal>copy-resources</goal>
                    </goals>
                    <configuration>
                        <outputDirectory>${project.build.outputDirectory}/static</outputDirectory>
                        <resources>
                            <resource>
                                <directory>../frontend/dist</directory>
                            </resource>
                        </resources>
                    </configuration>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

---

### 3. JAR 파일 빌드

```bash
cd backend
mvn clean package

# JAR 파일 생성: target/cafe-kiosk-backend-1.0.0.jar
```

---

### 4. 실행

```bash
java -jar target/cafe-kiosk-backend-1.0.0.jar
```

이제 `http://localhost:8080`에서 **React + Spring Boot** 통합 앱이 실행됩니다!

---

### 5. Docker 배포 (선택사항)

**`Dockerfile` (backend/):**
```dockerfile
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
```

**`docker-compose.yml` (루트):**
```yaml
version: '3.8'

services:
  mysql:
    image: mysql:8.0
    environment:
      MYSQL_DATABASE: cafe_kiosk
      MYSQL_ROOT_PASSWORD: password
    ports:
      - "3306:3306"
    volumes:
      - mysql_data:/var/lib/mysql

  backend:
    build: ./backend
    ports:
      - "8080:8080"
    environment:
      DB_USERNAME: root
      DB_PASSWORD: password
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/cafe_kiosk
    depends_on:
      - mysql

volumes:
  mysql_data:
```

**실행:**
```bash
docker-compose up -d
```

---

## 테스트 시나리오

### API 테스트 (Postman)

1. **카테고리 조회**
   ```
   GET http://localhost:8080/api/menu/categories
   ```

2. **메뉴 조회**
   ```
   GET http://localhost:8080/api/menu/items
   ```

3. **주문 생성**
   ```
   POST http://localhost:8080/api/orders
   Content-Type: application/json

   {
     "customerName": "홍길동",
     "items": [
       {
         "menuItemId": 1,
         "menuItemName": "아메리카노",
         "price": 3000,
         "quantity": 2,
         "subtotal": 6000
       }
     ]
   }
   ```

---

### 프론트엔드 테스트

1. ✅ 메뉴 페이지 접속
2. ✅ 카테고리 필터링
3. ✅ 장바구니 추가
4. ✅ 수량 변경
5. ✅ 주문하기
6. ✅ 주문 완료 확인

---

## 자주 묻는 질문

**Q: CORS 오류가 나요.**
A: `WebConfig.java`에서 React 개발 서버 주소(`http://localhost:5173`)가 허용되었는지 확인하세요.

**Q: API가 404 오류를 반환해요.**
A: 백엔드가 8080 포트에서 실행 중인지, URL 경로가 `/api/`로 시작하는지 확인하세요.

**Q: 빌드 후 React 라우팅이 안 돼요.**
A: Spring Boot에 다음 컨트롤러를 추가하세요:
```java
@Controller
public class SpaRedirectController {
    @GetMapping(value = "/{path:[^\\.]*}")
    public String redirect() {
        return "forward:/index.html";
    }
}
```

**Q: 이미지가 안 보여요.**
A: `frontend/public/images/menu/` 폴더에 이미지를 넣고, DB의 `image_url`을 `/images/menu/파일명.jpg`로 설정하세요.

---

## 마무리

이 구조를 따라하면 **완전한 풀스택 카페 키오스크 앱**을 만들 수 있습니다!

### 핵심 장점
✅ **현대적인 기술 스택** (React + Spring Boot)
✅ **명확한 역할 분담** (Frontend/Backend)
✅ **확장 가능한 구조** (컴포넌트, 서비스)
✅ **포트폴리오에 적합** (취업 시장에서 인기)
✅ **배포 가능** (단일 JAR 또는 Docker)

**화이팅! 🚀**
