package de.bananabonanza.service;

import de.bananabonanza.entity.Product;
import de.bananabonanza.enumeration.ProductStatus;
import de.bananabonanza.respository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public Product save(Product _product){
        return productRepository.save(_product);
    }

    @Transactional(readOnly = true)
    public Product getProduct(long id){
        return productRepository.findById(id).orElseThrow(()->new EntityNotFoundException(String.valueOf(id)));
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProducts(){
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Transactional
    public void delete(long id){
        productRepository.deleteById(id);
    }

    @Transactional
    public Product update(Product _product){
        Product existingProduct = getProduct(_product.getId());

        existingProduct.setTitel(_product.getTitel());
        existingProduct.setDescription(_product.getDescription());
        existingProduct.setCategory(_product.getCategory());
        existingProduct.setReviews(_product.getReviews());
        existingProduct.setStatus(_product.getStatus());

        return productRepository.save(existingProduct);
    }

    @Transactional(readOnly = true)
    public List<Product> getAvailableProducts(){
        return productRepository.findByStatus(ProductStatus.AVAILABLE);
    }

    @Transactional(readOnly = true)
    public List<Product> getOutOfStockProducts(){
        return productRepository.findByStatus(ProductStatus.OUT_OF_STOCK);
    }

    @Transactional(readOnly = true)
    public List<Product> getDiscontinuedProducts(){
        return productRepository.findByStatus(ProductStatus.DISCONTINUED);
    }

    @Transactional(readOnly = true)
    public List<Product> getPreorderProducts(){
        return productRepository.findByStatus(ProductStatus.PREORDER);
    }

    @Transactional(readOnly = true)
    public List<Product> getBackorderProducts(){
        return productRepository.findByStatus(ProductStatus.BACKORDER);
    }

    @Transactional(readOnly = true)
    public long getCountAvailableProducts(){
        return productRepository.countByStatus(ProductStatus.AVAILABLE);
    }

    @Transactional(readOnly = true)
    public long getCountOutOfStockProducts(){
        return productRepository.countByStatus(ProductStatus.OUT_OF_STOCK);
    }

    @Transactional(readOnly = true)
    public long getCountDiscontinuedProducts(){
        return productRepository.countByStatus(ProductStatus.DISCONTINUED);
    }

    @Transactional(readOnly = true)
    public long getCountPreorderProducts(){
        return productRepository.countByStatus(ProductStatus.PREORDER);
    }

    @Transactional(readOnly = true)
    public long getCountBackorderProducts(){
        return productRepository.countByStatus(ProductStatus.BACKORDER);
    }
}
