package de.bananabonanza.service;

import de.bananabonanza.entity.Order;
import de.bananabonanza.enumeration.DeliveryStatus;
import de.bananabonanza.respository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public Order saveOrder(Order order) {
        order.setBuydate(LocalDateTime.now());
        order.setLastedit(LocalDateTime.now());
        return orderRepository.save(order);
    }

    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Transactional
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Transactional
    public Order updateOrder(Order _order) {
        Order existingOrder = getOrderById(_order.getId()).orElseThrow(() -> new IllegalArgumentException("Order with the id " + _order.getId() + " not found."));

        existingOrder.setUser(_order.getUser());
        existingOrder.setItems(_order.getItems());
        existingOrder.setPaymentMethod(_order.getPaymentMethod());
        existingOrder.setPaid(_order.getPaid());
        existingOrder.setDeliveryStatus(_order.getDeliveryStatus());
        existingOrder.setLastedit(LocalDateTime.now());

        return orderRepository.save(existingOrder);
    }

    @Transactional(readOnly = true)
    public List<Order> getOrdersInTransit(){
        return orderRepository.findByDeliveryStatus(DeliveryStatus.IN_TRANSIT);
    }

    @Transactional(readOnly = true)
    public List<Order> getDeliveredOrders(){
        return orderRepository.findByDeliveryStatus(DeliveryStatus.DELIVERED);
    }

    @Transactional(readOnly = true)
    public List<Order> getReturnedOrders(){
        return orderRepository.findByDeliveryStatus(DeliveryStatus.RETURNED);
    }

    @Transactional(readOnly = true)
    public List<Order> getDamagedOrders(){
        return orderRepository.findByDeliveryStatus(DeliveryStatus.DAMAGED);
    }

    @Transactional(readOnly = true)
    public List<Order> getOnHoldOrders(){
        return orderRepository.findByDeliveryStatus(DeliveryStatus.ON_HOLD);
    }

    @Transactional(readOnly = true)
    public long getCountOrdersInTransit(){
        return orderRepository.countByDeliveryStatus(DeliveryStatus.IN_TRANSIT);
    }

    @Transactional(readOnly = true)
    public long getCountDeliveredOrders(){
        return orderRepository.countByDeliveryStatus(DeliveryStatus.DELIVERED);
    }

    @Transactional(readOnly = true)
    public long getCountReturnedOrders(){
        return orderRepository.countByDeliveryStatus(DeliveryStatus.RETURNED);
    }

    @Transactional(readOnly = true)
    public long getCountDamagedOrders(){
        return orderRepository.countByDeliveryStatus(DeliveryStatus.DAMAGED);
    }

    @Transactional(readOnly = true)
    public long getCountOnHoldOrders(){
        return orderRepository.countByDeliveryStatus(DeliveryStatus.ON_HOLD);
    }
}
