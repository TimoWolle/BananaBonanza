package de.bananabonanza.respository;

import de.bananabonanza.entity.Product;
import de.bananabonanza.enumeration.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>  {
    List<Product> findByStatus(ProductStatus status);
    Long countByStatus(ProductStatus status);

}
