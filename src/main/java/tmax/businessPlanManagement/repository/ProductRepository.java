package tmax.businessPlanManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tmax.businessPlanManagement.domain.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product save(Product product);
    Optional<Product> findById(Long id);
    Optional<Product> findByPart(String part);

}
