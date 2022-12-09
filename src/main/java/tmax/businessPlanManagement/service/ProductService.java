package tmax.businessPlanManagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tmax.businessPlanManagement.domain.Product;
import tmax.businessPlanManagement.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void save(Product product) {
        productRepository.save(product);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    public Product findByPart(String part) {
        return productRepository.findByPart(part).get();
    }
}