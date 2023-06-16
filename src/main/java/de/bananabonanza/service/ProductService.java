package de.bananabonanza.service;

import de.bananabonanza.entity.Product;
import de.bananabonanza.enumeration.ProductCategory;
import de.bananabonanza.enumeration.ProductStatus;
import de.bananabonanza.respository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Optional<Product> getProductById(long id){
        return productRepository.findById(id);
    }

    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public Optional<Product> updateProduct(Product updatedProduct, long id){

        return productRepository.findById(id)
                .map(product -> {
                    product.setTitel(updatedProduct.getTitel());
                    product.setDescription(updatedProduct.getDescription());
                    product.setCategory(updatedProduct.getCategory());
                    product.setReviews(updatedProduct.getReviews());
                    product.setStatus(updatedProduct.getStatus());
                    productRepository.save(product);
                    return product;
                });
    }

    public void deleteProduct(long id){
        productRepository.deleteById(id);
    }

    public List<Product> getProductsByCategory(ProductCategory category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> getProductsByStatus(ProductStatus status) {
        return productRepository.findByStatus(status);
    }

}
