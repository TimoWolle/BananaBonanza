package de.bananabonanza;

import de.bananabonanza.entity.*;
import de.bananabonanza.enumeration.ProductCategory;
import de.bananabonanza.enumeration.ProductStatus;
import de.bananabonanza.enumeration.Rating;
import de.bananabonanza.respository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Erstelle Adressen
        Address address = new Address(null,"Germany", "PostalCode", "City", "Street", "HouseNumber", true);
        Address address2 = new Address(null,"Sweden", "PostalCode", "City", "Street", "HouseNumber", false);

        addressRepository.save(address);
        addressRepository.save(address2);

        // Erstelle einige Produkte
        Product product = new Product();
        product.setTitle("Bananen Smoothie");
        product.setDescription("Erfrischender Smoothie aus reifen Bananen und frischer Milch.");
        product.setImageURL("bananen_smoothie.jpg");
        product.setPrice(4.99);
        product.setOldPrice(5.99);
        product.setCategory(ProductCategory.FOOD_AND_DRINKS);
        product.setStatus(ProductStatus.AVAILABLE);

// Zweites Produkt
        Product product2 = new Product();
        product2.setTitle("Bananenbrot");
        product2.setDescription("Selbstgemachtes Bananenbrot, perfekt zum Frühstück oder zum Kaffee.");
        product2.setImageURL("bananenbrot.jpg");
        product2.setPrice(3.99);
        product2.setOldPrice(4.99);
        product2.setCategory(ProductCategory.FOOD_AND_DRINKS);
        product2.setStatus(ProductStatus.AVAILABLE);

// Drittes Produkt
        Product product3 = new Product();
        product3.setTitle("Bananen Eiscreme");
        product3.setDescription("Cremiges Bananen Eiscreme, das perfekte Dessert.");
        product3.setImageURL("bananen_eiscreme.jpg");
        product3.setPrice(6.99);
        product3.setOldPrice(7.99);
        product3.setCategory(ProductCategory.FOOD_AND_DRINKS);
        product3.setStatus(ProductStatus.AVAILABLE);

// Vierter Produkt
        Product product4 = new Product();
        product4.setTitle("Bananen Muffin");
        product4.setDescription("Flaumige Muffins mit frischen Bananenstücken.");
        product4.setImageURL("bananen_muffin.jpg");
        product4.setPrice(2.99);
        product4.setOldPrice(3.99);
        product4.setCategory(ProductCategory.FOOD_AND_DRINKS);
        product4.setStatus(ProductStatus.AVAILABLE);

// Fünftes Produkt
        Product product5 = new Product();
        product5.setTitle("Bananen Schokolade");
        product5.setDescription("Milchschokolade mit Bananenfüllung.");
        product5.setImageURL("bananen_schokolade.jpg");
        product5.setPrice(1.99);
        product5.setOldPrice(2.99);
        product5.setCategory(ProductCategory.FOOD_AND_DRINKS);
        product5.setStatus(ProductStatus.AVAILABLE);
// Sechstes Produkt
        Product product6 = new Product();
        product6.setTitle("Bananen Chips");
        product6.setDescription("Knusprige, getrocknete Bananenchips, ideal als Snack.");
        product6.setImageURL("bananen_chips.jpg");
        product6.setPrice(1.99);
        product6.setOldPrice(2.99);
        product6.setCategory(ProductCategory.FOOD_AND_DRINKS);
        product6.setStatus(ProductStatus.AVAILABLE);

// Siebtes Produkt
        Product product7 = new Product();
        product7.setTitle("Bananen Shampoo");
        product7.setDescription("Natürliches Shampoo mit Bananenextrakt, perfekt für trockenes Haar.");
        product7.setImageURL("bananen_shampoo.jpg");
        product7.setPrice(6.99);
        product7.setOldPrice(7.99);
        product7.setCategory(ProductCategory.BEAUTY);
        product7.setStatus(ProductStatus.AVAILABLE);

// Achtes Produkt
        Product product8 = new Product();
        product8.setTitle("Bananen Lotion");
        product8.setDescription("Feuchtigkeitsspendende Körperlotion mit Bananenduft.");
        product8.setImageURL("bananen_lotion.jpg");
        product8.setPrice(8.99);
        product8.setOldPrice(9.99);
        product8.setCategory(ProductCategory.BEAUTY);
        product8.setStatus(ProductStatus.AVAILABLE);

// Neuntes Produkt
        Product product9 = new Product();
        product9.setTitle("Bananen Pyjama");
        product9.setDescription("Komfortabler Pyjama mit lustigem Bananen-Muster.");
        product9.setImageURL("bananen_pyjama.jpg");
        product9.setPrice(19.99);
        product9.setOldPrice(24.99);
        product9.setCategory(ProductCategory.CLOTHING);
        product9.setStatus(ProductStatus.AVAILABLE);

// Zehntes Produkt
        Product product10 = new Product();
        product10.setTitle("Bananen Kissen");
        product10.setDescription("Weiches, gemütliches Kissen in Bananenform.");
        product10.setImageURL("bananen_kissen.jpg");
        product10.setPrice(14.99);
        product10.setOldPrice(19.99);
        product10.setCategory(ProductCategory.HOME_DECOR);
        product10.setStatus(ProductStatus.AVAILABLE);

// Elftes Produkt
        Product product11 = new Product();
        product11.setTitle("Bananen Sticker Set");
        product11.setDescription("Set mit lustigen Bananen-Aufklebern, perfekt für Notebooks, Handys und mehr.");
        product11.setImageURL("bananen_sticker_set.jpg");
        product11.setPrice(2.99);
        product11.setOldPrice(3.99);
        product11.setCategory(ProductCategory.ACCESSORIES);
        product11.setStatus(ProductStatus.AVAILABLE);

// Zwölftes Produkt
        Product product12 = new Product();
        product12.setTitle("Bananen Anhänger");
        product12.setDescription("Niedlicher Bananen-Anhänger, perfekt für Schlüssel oder Taschen.");
        product12.setImageURL("bananen_anhaenger.jpg");
        product12.setPrice(4.99);
        product12.setOldPrice(6.99);
        product12.setCategory(ProductCategory.ACCESSORIES);
        product12.setStatus(ProductStatus.AVAILABLE);

        Product pruduct13 = new Product();
        pruduct13.setTitle("Bananen Kissen");
        pruduct13.setDescription("Weiches, gemütliches Kissen in Bananenform.");
        pruduct13.setImageURL("bananen_kissen.jpg");
        pruduct13.setPrice(14.99);
        pruduct13.setOldPrice(19.99);


        product = productRepository.save(product);
        product2 = productRepository.save(product2);
        product3 = productRepository.save(product3);
        product4 = productRepository.save(product4);
        product5 = productRepository.save(product5);
        product6 = productRepository.save(product6);
        product7 = productRepository.save(product7);
        product8 = productRepository.save(product8);
        product9 = productRepository.save(product9);
        product10 = productRepository.save(product10);
        product11 = productRepository.save(product11);
        product12 = productRepository.save(product12);

        // Erstelle Wunschliste
        Wishlist wishlist = new Wishlist();
        wishlist.setItems(Collections.singletonMap(product, 1));
        wishlist.setItems(Collections.singletonMap(product2, 3));
        wishlist=wishlistRepository.save(wishlist);


        Map<Product, Integer> shoppingCart = new HashMap<>();

       shoppingCart.put(product, 7);
       shoppingCart.put(product4, 7);
       shoppingCart.put(product5, 2);
       shoppingCart.put(product2, 3);

        // Erstelle User
        User user = new User();
        user.setFirstname("First");
        user.setLastname("Last");
        user.setEmail("test@example.com");
        user.setPasswort("password");
        user.setAddresses(Collections.singletonList(address));
        user.setShoppingCart(shoppingCart);
        user.setWishlist(Collections.singletonList(wishlist));
        user.setSaveforlaterlist(Collections.singletonList(product));
        user.setPasswort(passwordEncoder.encode(user.getPasswort()));
        userRepository.save(user);

        // Erstelle eine Review
        Review review = new Review();
        review.setTitle("Great product");
        review.setDescription("I really enjoyed this product. High quality and good price.");
        review.setAuthor(user);
        review.setRating(Rating.FIVE_STARS);
        review = reviewRepository.save(review);

        // Füge die Review zum Produkt hinzu
        //product.setReviews(Collections.singletonList(review));
        //product = productRepository.save(product);

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

