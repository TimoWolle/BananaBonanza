package de.bananabonanza.service;

import de.bananabonanza.entity.Order;
import de.bananabonanza.enumeration.DeliveryStatus;
import de.bananabonanza.respository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }


    public Order createOrder(Order order) {
        order.setBuydate(LocalDateTime.now());
        order.setLastedit(LocalDateTime.now());
        return orderRepository.save(order);
    }


    public Optional<Order> updateOrder(Order updatedOrder, Long id) {
        return orderRepository.findById(id)
                .map(order -> {
                    order.setUser(updatedOrder.getUser());
                    order.setItems(updatedOrder.getItems());
                    order.setPaymentMethod(updatedOrder.getPaymentMethod());
                    order.setPaid(updatedOrder.getPaid());
                    order.setDeliveryStatus(updatedOrder.getDeliveryStatus());
                    order.setLastedit(LocalDateTime.now());
                    orderRepository.save(order);
                    return order;
                });
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public List<Order> getOrdersInTransit(){
        return orderRepository.findByDeliveryStatus(DeliveryStatus.IN_TRANSIT);
    }

    public List<Order> getDeliveredOrders(){
        return orderRepository.findByDeliveryStatus(DeliveryStatus.DELIVERED);
    }

    public List<Order> getReturnedOrders(){
        return orderRepository.findByDeliveryStatus(DeliveryStatus.RETURNED);
    }

    public List<Order> getDamagedOrders(){
        return orderRepository.findByDeliveryStatus(DeliveryStatus.DAMAGED);
    }

    public List<Order> getOnHoldOrders(){
        return orderRepository.findByDeliveryStatus(DeliveryStatus.ON_HOLD);
    }

    public long getCountOrdersInTransit(){
        return orderRepository.countByDeliveryStatus(DeliveryStatus.IN_TRANSIT);
    }

    public long getCountDeliveredOrders(){
        return orderRepository.countByDeliveryStatus(DeliveryStatus.DELIVERED);
    }

    public long getCountReturnedOrders(){
        return orderRepository.countByDeliveryStatus(DeliveryStatus.RETURNED);
    }

    public long getCountDamagedOrders(){
        return orderRepository.countByDeliveryStatus(DeliveryStatus.DAMAGED);
    }

    public long getCountOnHoldOrders(){
        return orderRepository.countByDeliveryStatus(DeliveryStatus.ON_HOLD);
    }
}
