package de.bananabonanza.service;

import de.bananabonanza.entity.Product;
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

public class WarehouseService {
    private final WarehouseRepository warehouseRepository;

    public Warehouse saveWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    public Optional<Warehouse> getWarehouseById(Long id) {
        return warehouseRepository.findById(id);
    }

    public List<Warehouse> getAllWarehouse() {
        return warehouseRepository.findAll();
    }

    @Transactional
    public void deleteWarehouse(Long id) {
        warehouseRepository.deleteById(id);
    }

    @Transactional
    public Warehouse updateWarehouse(Warehouse _warehouse) {
        Warehouse existingWarehouse = getWarehouseById(_warehouse.getId()).orElseThrow(() -> new IllegalArgumentException("Wishlist with the id " + _warehouse.getId() + " not found."));

        existingWarehouse.setProducts(_warehouse.getProducts());

        return warehouseRepository.save(existingWarehouse);
    }

}