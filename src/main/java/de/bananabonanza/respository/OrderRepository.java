package de.bananabonanza.respository;

import de.bananabonanza.entity.Order;
import de.bananabonanza.entity.User;
import de.bananabonanza.enumeration.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByDeliveryStatus(DeliveryStatus deliveryStatus);
    List<Order> findByUser(User user);
    long countByDeliveryStatus(DeliveryStatus deliveryStatus);
}
