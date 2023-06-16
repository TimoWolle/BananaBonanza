package de.bananabonanza.service;

import de.bananabonanza.entity.Warehouse;
import de.bananabonanza.respository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;

    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    public Optional<Warehouse> getWarehouseById(Long id) {
        return warehouseRepository.findById(id);
    }

    public Warehouse createWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    public Optional<Warehouse> updateWarehouse(Warehouse updatedWarehouse, Long id) {
        return warehouseRepository.findById(id)
                .map(warehouse -> {
                    warehouse.setProducts(updatedWarehouse.getProducts());
                    warehouseRepository.save(warehouse);
                    return warehouse;
                });
    }

    public void deleteWarehouse(Long id) {
        warehouseRepository.deleteById(id);
    }
}