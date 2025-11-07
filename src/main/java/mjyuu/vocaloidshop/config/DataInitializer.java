package mjyuu.vocaloidshop.config;

import lombok.RequiredArgsConstructor;
import mjyuu.vocaloidshop.entity.Category;
import mjyuu.vocaloidshop.entity.Product;
import mjyuu.vocaloidshop.entity.User;
import mjyuu.vocaloidshop.repository.CategoryRepository;
import mjyuu.vocaloidshop.repository.ProductRepository;
import mjyuu.vocaloidshop.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {
    
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            // Check if data already exists
            if (userRepository.count() > 0) {
                return; // Data already loaded
            }
            
            // Create Admin User
            User admin = User.builder()
                    .email("admin@vocalocart.com")
                    .password(passwordEncoder.encode("password123"))
                    .name("Admin User")
                    .phone("010-0000-0000")
                    .address("1 Admin Street, Seoul")
                    .role(User.Role.ADMIN)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            userRepository.save(admin);
            
            // Create Sample Users
            User alice = User.builder()
                    .email("alice@example.com")
                    .password(passwordEncoder.encode("password123"))
                    .name("Alice Johnson")
                    .phone("010-1234-5678")
                    .address("123 Gangnam Street, Seoul")
                    .role(User.Role.USER)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            userRepository.save(alice);
            
            // Create Categories
            Category figures = Category.builder()
                    .name("Figures")
                    .description("Vocaloid figures and collectibles")
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            categoryRepository.save(figures);
            
            Category plushies = Category.builder()
                    .name("Plushies")
                    .description("Soft and cuddly Vocaloid plush toys")
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            categoryRepository.save(plushies);
            
            Category apparel = Category.builder()
                    .name("Apparel")
                    .description("Vocaloid themed clothing and accessories")
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            categoryRepository.save(apparel);
            
            Category music = Category.builder()
                    .name("Music")
                    .description("Vocaloid music albums and singles")
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            categoryRepository.save(music);
            
            // Create Sample Products
            Product p1 = Product.builder()
                    .name("Hatsune Miku Racing Ver. 2023 Figure")
                    .description("Premium 1/8 scale figure of Hatsune Miku in her 2023 Racing outfit")
                    .price(18999)
                    .stockQuantity(25)
                    .imageUrl("https://images.unsplash.com/photo-1601814933824-fd0b574dd592?w=500")
                    .category(figures)
                    .build();
            productRepository.save(p1);
            
            Product p2 = Product.builder()
                    .name("Kagamine Rin/Len Plush Set")
                    .description("Adorable plush toys of the twin Vocaloids")
                    .price(4599)
                    .stockQuantity(50)
                    .imageUrl("https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w=500")
                    .category(plushies)
                    .build();
            productRepository.save(p2);
            
            Product p3 = Product.builder()
                    .name("Miku Expo 2024 T-Shirt")
                    .description("Official Miku Expo 2024 concert merchandise")
                    .price(2999)
                    .stockQuantity(100)
                    .imageUrl("https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=500")
                    .category(apparel)
                    .build();
            productRepository.save(p3);
            
            Product p4 = Product.builder()
                    .name("Project DIVA Mega Mix+ Album")
                    .description("Complete soundtrack from Project DIVA Mega Mix+")
                    .price(2499)
                    .stockQuantity(75)
                    .imageUrl("https://images.unsplash.com/photo-1511379938547-c1f69419868d?w=500")
                    .category(music)
                    .build();
            productRepository.save(p4);
            
            Product p5 = Product.builder()
                    .name("Luka Megurine Anniversary Figure")
                    .description("Limited edition anniversary figure")
                    .price(15999)
                    .stockQuantity(15)
                    .imageUrl("https://images.unsplash.com/photo-1593085512500-5d55148d6f0d?w=500")
                    .category(figures)
                    .build();
            productRepository.save(p5);
            
            System.out.println("✅ Sample data loaded successfully!");
            System.out.println("👤 Admin: admin@vocalocart.com / password123");
            System.out.println("👤 User: alice@example.com / password123");
        };
    }
}
