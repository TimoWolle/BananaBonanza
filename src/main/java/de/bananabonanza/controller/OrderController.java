package de.bananabonanza.controller;

import de.bananabonanza.dto.create.OrderCreate;
import de.bananabonanza.dto.update.OrderUpdate;
import de.bananabonanza.entity.Order;
import de.bananabonanza.entity.User;
import de.bananabonanza.service.OrderService;
import de.bananabonanza.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@PreAuthorize("hasRole('TODO_FULLRIGHTS')")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final ModelMapper mapper;
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> Order = orderService.getOrderById(id);
        return Order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Order>  createOrder(@RequestBody OrderCreate orderCreate) {
        Order newOrder = orderService.createOrder(mapper.map(orderCreate, Order.class));
        return ResponseEntity.ok(newOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody OrderUpdate orderUpdate) {
        Optional<Order> order = orderService.updateOrder(mapper.map(orderUpdate, Order.class), id);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        if (orderService.getOrderById(id).isPresent()) {
            orderService.deleteOrder(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/cart/submit")
    public ResponseEntity<Order> submitOrder(@AuthenticationPrincipal User _user){
        Optional<User> optionalUser = userService.getUserById(_user.getId());

        User user = optionalUser.get();

        OrderCreate orderCreate = new OrderCreate();

        orderCreate.setUser(user);
        orderCreate.setItems(user.getShoppingCart());
        orderCreate.setPaid(false);

        return createOrder(orderCreate);
    }
}
