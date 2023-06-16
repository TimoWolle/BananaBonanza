package de.bananabonanza.service;

import de.bananabonanza.entity.Product;
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

    public List<Product> getAvailableProducts(){
        return productRepository.findByStatus(ProductStatus.AVAILABLE);
    }

    public List<Product> getOutOfStockProducts(){
        return productRepository.findByStatus(ProductStatus.OUT_OF_STOCK);
    }

    public List<Product> getDiscontinuedProducts(){
        return productRepository.findByStatus(ProductStatus.DISCONTINUED);
    }

    public List<Product> getPreorderProducts(){
        return productRepository.findByStatus(ProductStatus.PREORDER);
    }

    public List<Product> getBackorderProducts(){
        return productRepository.findByStatus(ProductStatus.BACKORDER);
    }

    public long getCountAvailableProducts(){
        return productRepository.countByStatus(ProductStatus.AVAILABLE);
    }

    public long getCountOutOfStockProducts(){
        return productRepository.countByStatus(ProductStatus.OUT_OF_STOCK);
    }

    public long getCountDiscontinuedProducts(){
        return productRepository.countByStatus(ProductStatus.DISCONTINUED);
    }

    public long getCountPreorderProducts(){
        return productRepository.countByStatus(ProductStatus.PREORDER);
    }

    public long getCountBackorderProducts(){
        return productRepository.countByStatus(ProductStatus.BACKORDER);
    }
}
