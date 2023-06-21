package de.bananabonanza.controller;

import com.fasterxml.jackson.annotation.JsonView;
import de.bananabonanza.dto.WarehouseItemUpdateRequest;
import de.bananabonanza.dto.create.ProductCreate;
import de.bananabonanza.dto.create.ReviewCreate;
import de.bananabonanza.dto.create.WarehouseCreate;
import de.bananabonanza.dto.update.ProductUpdate;
import de.bananabonanza.dto.update.ReviewUpdate;
import de.bananabonanza.dto.update.WarehouseUpdate;
import de.bananabonanza.entity.Product;
import de.bananabonanza.entity.Review;
import de.bananabonanza.entity.Views;
import de.bananabonanza.entity.Warehouse;
import de.bananabonanza.enumeration.ProductCategory;
import de.bananabonanza.enumeration.ProductStatus;
import de.bananabonanza.service.ProductService;
import de.bananabonanza.service.ReviewService;
import de.bananabonanza.service.WarehouseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/warehouse")
@RequiredArgsConstructor
public class WarehouseController {
    private final WarehouseService warehouseService;
    private final ProductService productService;
    private final ReviewService reviewService;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouses() {
        return ResponseEntity.ok(warehouseService.getAllWarehouses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable Long id) {
        Optional<Warehouse> warehouse = warehouseService.getWarehouseById(id);
        return warehouse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody WarehouseCreate warehouseCreate) {
        Warehouse newWarehouse = warehouseService.createWarehouse(mapper.map(warehouseCreate, Warehouse.class));
        return ResponseEntity.ok(newWarehouse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Warehouse> updateWarehouse(@RequestBody WarehouseUpdate updatedWarehouse, @PathVariable Long id) {
        Optional<Warehouse> warehouse = warehouseService.updateWarehouse(mapper.map(updatedWarehouse, Warehouse.class), id);
        return warehouse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/Wishlist-Item-Update")
    public ResponseEntity<?> updateWarehouseItem(@Valid @RequestBody WarehouseItemUpdateRequest request) {
        Optional<Warehouse> optionalWishlist = warehouseService.getWarehouseById(request.getId());
        if (optionalWishlist.isPresent()) {
            Warehouse warehouse = optionalWishlist.get();
            switch (request.getAction()) {
                case "add" -> warehouse.getProducts().put(request.getProduct(), request.getQuantity());
                case "remove" -> warehouse.getProducts().remove(request.getProduct());
                case "update" -> {
                    if (warehouse.getProducts().containsKey(request.getProduct())) {
                        warehouse.getProducts().put(request.getProduct(), request.getQuantity());
                    } else {
                        return ResponseEntity.badRequest().body("Product not found in warehouse");
                    }
                }
                default -> {
                    return ResponseEntity.badRequest().body("Invalid action");
                }
            }
            optionalWishlist = warehouseService.updateWarehouse(warehouse, request.getId()); // Hier wird die Methode "update" des WishlistService aufgerufen
            return optionalWishlist.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable Long id) {
        if (warehouseService.getWarehouseById(id).isPresent()) {
            warehouseService.deleteWarehouse(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/products")
    @JsonView(Views.Basic.class)
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> productOptional = productService.getProductById(id);
        return productOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@Valid @PathVariable("category") ProductCategory category) {
        return productService.getProductsByCategory(category);
    }

    @GetMapping("/status/{status}")
    public List<Product> getProductsByAvailability(@Valid @PathVariable("status") ProductStatus status) {
        return productService.getProductsByStatus(status);
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody ProductCreate productCreate) {
        return productService.createProduct(mapper.map(productCreate, Product.class));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductUpdate updatedProduct) {
        Optional<Product> productOptional = productService.updateProduct(mapper.map(updatedProduct, Product.class), id);
        return productOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (productService.getProductById(id).isPresent()){
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/product/review/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        Optional<Review> reviewOptional = reviewService.getReviewById(id);
        return reviewOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/product/review")
    public Review createReview(@RequestBody ReviewCreate reviewCreate) {
        return reviewService.createReview(mapper.map(reviewCreate, Review.class));
    }

    @PutMapping("/product/review/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody ReviewUpdate updatedReview) {
        Optional<Review> reviewOptional = reviewService.updateReview(mapper.map(updatedReview, Review.class), id);
        return reviewOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/product/review/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        if (reviewService.getReviewById(id).isPresent()) {
            reviewService.deleteReview(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
