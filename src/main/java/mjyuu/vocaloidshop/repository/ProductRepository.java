package mjyuu.vocaloidshop.repository;

import mjyuu.vocaloidshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @EntityGraph(attributePaths = {"category"})
    List<Product> findAll();
    
    @EntityGraph(attributePaths = {"category"})
    Optional<Product> findById(Long id);
    
    @EntityGraph(attributePaths = {"category"})
    List<Product> findByCategoryId(Long categoryId);
    
    @EntityGraph(attributePaths = {"category"})
    List<Product> findByNameContaining(String name);
}
