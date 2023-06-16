package de.bananabonanza.controller;

import de.bananabonanza.dto.create.WishlistCreate;
import de.bananabonanza.dto.WishlistItemUpdateRequest;
import de.bananabonanza.dto.update.WishlistUpdate;
import de.bananabonanza.dto.WishlistUserUpdateRequest;
import de.bananabonanza.entity.User;
import de.bananabonanza.entity.Wishlist;
import de.bananabonanza.service.UserService;
import de.bananabonanza.service.WishlistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/wishlists")
@RequiredArgsConstructor
public class WishlistController {
    private final WishlistService wishlistService;
    private final UserService userService;
    private final ModelMapper mapper;

    @GetMapping
    public List<Wishlist> getAllWishlists() {
        return wishlistService.getAllWishlists();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wishlist> getWishlistById(@PathVariable Long id) {
        Optional<Wishlist> wishlist = wishlistService.getWishlistById(id);
        return wishlist.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Wishlist createWishlist(@RequestBody WishlistCreate wishlistCreate) {
        return wishlistService.createWishlist(mapper.map(wishlistCreate, Wishlist.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Wishlist> updateWishlist(@RequestBody WishlistUpdate updatedWishlist, @PathVariable Long id) {
        Optional<Wishlist> wishlist = wishlistService.updateWishlist(mapper.map(updatedWishlist, Wishlist.class), id);
        return wishlist.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWishlist(@PathVariable Long id) {
        if (wishlistService.getWishlistById(id).isPresent()) {
            wishlistService.deleteWishlist(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/Wishlist-Item-Update")
    public ResponseEntity<?> updateWishlistItem(@Valid @RequestBody WishlistItemUpdateRequest request) {
        Optional<Wishlist> optionalWishlist = wishlistService.getWishlistById(request.getId());
        if (optionalWishlist.isPresent()) {
            Wishlist wishlist = optionalWishlist.get();
            switch (request.getAction()) {
                case "add" -> wishlist.getItems().put(request.getProduct(), request.getQuantity());
                case "remove" -> wishlist.getItems().remove(request.getProduct());
                case "update" -> {
                    if (wishlist.getItems().containsKey(request.getProduct())) {
                        wishlist.getItems().put(request.getProduct(), request.getQuantity());
                    } else {
                        return ResponseEntity.badRequest().body("Product not found in warehouse");
                    }
                }
                default -> {
                    return ResponseEntity.badRequest().body("Invalid action");
                }
            }
            optionalWishlist = wishlistService.updateWishlist(wishlist, request.getId()); // Hier wird die Methode "update" des WishlistService aufgerufen
            return optionalWishlist.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/Wishlist-User-Update")
    public ResponseEntity<?> updateWishlistUsers(@Valid @RequestBody WishlistUserUpdateRequest request) {
        Optional<Wishlist> optionalWishlist = wishlistService.getWishlistById(request.getId());

        if (optionalWishlist.isPresent()) {
            Wishlist wishlist = optionalWishlist.get();

            Optional<User> optionalUser = userService.getUserByEmail(request.getUserEmail() );
            if (optionalUser.isEmpty()) {
                return ResponseEntity.badRequest().body("User not found");
            }
            User user = optionalUser.get();

            if (request.getAction().equals("add")) {
                wishlist.getSharedUsers().add(user);
            } else if(request.getAction().equals("remove")){
                wishlist.getSharedUsers().remove(user);
            } else {
                return ResponseEntity.badRequest().body("Invalid action");
            }

            optionalWishlist = wishlistService.updateWishlist(wishlist, request.getId()); // Hier wird die Methode "update" des WishlistService aufgerufen
            return optionalWishlist.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
