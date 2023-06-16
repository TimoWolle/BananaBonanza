package de.bananabonanza.service;

import de.bananabonanza.entity.Wishlist;
import de.bananabonanza.respository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class WishlistService {
    private final WishlistRepository wishlistRepository;

    public List<Wishlist> getAllWishlists() {
        return wishlistRepository.findAll();
    }

    public Optional<Wishlist> getWishlistById(Long id) {
        return wishlistRepository.findById(id);
    }

    public Wishlist createWishlist(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    public Optional<Wishlist> updateWishlist(Wishlist updatedWishlist, Long id) {
        return wishlistRepository.findById(id)
                .map(wishlist -> {
                    wishlist.setItems(updatedWishlist.getItems());
                    wishlist.setSharedUsers(updatedWishlist.getSharedUsers());
                    wishlistRepository.save(wishlist);
                    return wishlist;
                });
    }


    public void deleteWishlist(Long id) {
        wishlistRepository.deleteById(id);
    }



}
