package mjyuu.vocaloidshop.repository;

import mjyuu.vocaloidshop.entity.Order;
import mjyuu.vocaloidshop.entity.OrderStatus;
import mjyuu.vocaloidshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
    List<Order> findByUser(User user);
    List<Order> findByUserOrderByOrderedAtDesc(User user);
    
    @EntityGraph(attributePaths = {"user", "items", "items.product"})
    List<Order> findAllByOrderByCreatedAtDesc();
    
    @EntityGraph(attributePaths = {"user"})
    List<Order> findTop10ByOrderByCreatedAtDesc();
    
    @EntityGraph(attributePaths = {"user", "items", "items.product"})
    Optional<Order> findById(Long id);
    
    long countByStatus(OrderStatus status);
}
