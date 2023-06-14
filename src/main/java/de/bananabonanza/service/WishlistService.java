package de.bananabonanza.service;

import de.bananabonanza.entity.Product;
import de.bananabonanza.entity.Wishlist;
import de.bananabonanza.entity.Warehouse;
import de.bananabonanza.entity.Wishlist;
import de.bananabonanza.respository.WarehouseRepository;
import de.bananabonanza.respository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class WishlistService {
    private final WishlistRepository wishlistRepository;

    public Wishlist saveWishlist(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    public Optional<Wishlist> getWishlistById(Long id) {
        return wishlistRepository.findById(id);
    }

    public List<Wishlist> getAllWishlist() {
        return wishlistRepository.findAll();
    }

    @Transactional
    public void deleteWishlist(Long id) {
        wishlistRepository.deleteById(id);
    }

    @Transactional
    public Wishlist updateWishlist(Wishlist _wishlist) {
        Wishlist existingWishlist = getWishlistById(_wishlist.getId()).orElseThrow(() -> new IllegalArgumentException("Wishlist with the id " + _wishlist.getId() + " not found."));

        existingWishlist.setItems(_wishlist.getItems());
        existingWishlist.setSharedUsers(_wishlist.getSharedUsers());

        return wishlistRepository.save(existingWishlist);
    }

}
