package de.bananabonanza.respository;

import de.bananabonanza.entity.Address;
import de.bananabonanza.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
