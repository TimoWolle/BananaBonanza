package de.bananabonanza.controller;

import de.bananabonanza.dto.create.OrderCreate;
import de.bananabonanza.entity.Order;
import de.bananabonanza.entity.User;
import de.bananabonanza.service.OrderService;
import de.bananabonanza.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final ModelMapper mapper;
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> Order = orderService.getOrderById(id);
        return Order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{id}")
    public List<Order> getOrderByUserId(@AuthenticationPrincipal User _user) {
        return orderService.getAllOrdersByUser(_user);
    }

    @PostMapping("/shopping-cart/submit")
    public ResponseEntity<Order> submitOrder(@AuthenticationPrincipal User _user){
        Optional<User> optionalUser = userService.getUserById(_user.getId());

        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            OrderCreate orderCreate = new OrderCreate();

            orderCreate.setUser(user);
            orderCreate.setItems(user.getShoppingCart());
            orderCreate.setPaid(false);

            return createOrder(orderCreate);
        }

        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<Order>  createOrder(@RequestBody OrderCreate orderCreate) {
        Order newOrder = orderService.createOrder(mapper.map(orderCreate, Order.class));
        return ResponseEntity.ok(newOrder);
    }
}
