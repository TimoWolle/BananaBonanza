package de.bananabonanza;

import de.bananabonanza.entity.*;
import de.bananabonanza.enumeration.ProductCategory;
import de.bananabonanza.enumeration.ProductStatus;
import de.bananabonanza.enumeration.Rating;
import de.bananabonanza.respository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class DatabasePupolator implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final WarehouseRepository warehouseRepository;
    private final AddressRepository addressRepository;
    private final ReviewRepository reviewRepository;
    private final WishlistRepository wishlistRepository;

    @Override
    public void run(String... args) throws Exception {
        // Erstelle Adressen
        Address address = new Address(null,"Germany", "PostalCode", "City", "Street", "HouseNumber", true);
        Address address2 = new Address(null,"Sweden", "PostalCode", "City", "Street", "HouseNumber", false);

        addressRepository.save(address);
        addressRepository.save(address2);

        // Erstelle einige Produkte
        Product product = new Product(null, "Schlafanzug", "Description1", ProductCategory.GADGETS, Collections.emptyList(), ProductStatus.AVAILABLE);
        Product product2 = new Product(null, "Bananen", "Description1", ProductCategory.GADGETS, Collections.emptyList(), ProductStatus.AVAILABLE);

        product = productRepository.save(product);
        product2 = productRepository.save(product2);

        // Erstelle Wunschliste
        Wishlist wishlist = new Wishlist();
        wishlist.setItems(Collections.singletonMap(product, 1));
        wishlist.setItems(Collections.singletonMap(product2, 3));

        wishlist=wishlistRepository.save(wishlist);

        // Erstelle User
        User user = new User();
        user.setFirstname("First");
        user.setLastname("Last");
        user.setEmail("test@example.com");
        user.setPasswort("password");
        user.setAddresses(Collections.singletonList(address));
        user.setShoppingCart(Collections.singletonMap(product, 1));
        user.setWishlist(Collections.singletonList(wishlist));
        user.setSaveforlaterlist(Collections.singletonList(product));
        userRepository.save(user);

        // Erstelle eine Review
        Review review = new Review();
        review.setTitle("Great product");
        review.setDescription("I really enjoyed this product. High quality and good price.");
        review.setAuthor(user);
        review.setRating(Rating.FIVE_STARS);
        review = reviewRepository.save(review);

        // Füge die Review zum Produkt hinzu
        product.getReviews().add(review);
        product = productRepository.save(product);

        // Erstelle Order
        Order order = new Order();
        order.setUser(user);
        order.setItems(Collections.singletonMap(product, 1));
        order.setPaid(false);
        order.setBuydate(LocalDateTime.now());
        order.setLastedit(LocalDateTime.now());
        orderRepository.save(order);

        // Erstelle Warehouse
        Warehouse warehouse = new Warehouse();
        warehouse.setProducts(Collections.singletonMap(product, 100));
        warehouseRepository.save(warehouse);
    }
}

