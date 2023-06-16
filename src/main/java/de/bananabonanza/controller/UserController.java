package de.bananabonanza.controller;

import de.bananabonanza.dto.UserShoppingCartItem;
import de.bananabonanza.dto.create.AddressCreate;
import de.bananabonanza.dto.create.UserCreate;
import de.bananabonanza.dto.update.AddressUpdate;
import de.bananabonanza.dto.update.UserUpdate;
import de.bananabonanza.entity.Address;
import de.bananabonanza.entity.User;
import de.bananabonanza.service.AddressService;
import de.bananabonanza.service.OrderService;
import de.bananabonanza.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AddressService addressService;
    private final OrderService orderService;
    private final ModelMapper mapper;

    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User>  createUser(@RequestBody UserCreate userCreate) {
        User newUser = userService.createUser(mapper.map(userCreate, User.class));
        return ResponseEntity.ok(newUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserUpdate userUpdates) {
        Optional<User> user = userService.updateUser(mapper.map(userUpdates, User.class), id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userService.getUserById(id).isPresent()) {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> checkUserByEmail(@PathVariable("email") String email){
        Optional<User> userOptional = userService.getUserByEmail(email);
        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @GetMapping("/address/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
        Optional<Address> optionalAddress = addressService.getAddressById(id);
        return optionalAddress.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/address")
    public ResponseEntity<Address> createAddress(@RequestBody AddressCreate addressCreate) {
        Address newAddress = addressService.createAddress(mapper.map(addressCreate, Address.class));
        return ResponseEntity.ok(newAddress);
    }

    @PutMapping("/address/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody AddressUpdate addressUpdates) {
        Optional<Address> optionalUpdatedAddress = addressService.updateAddress(mapper.map(addressUpdates, Address.class), id);
        return optionalUpdatedAddress.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        if (addressService.getAddressById(id).isPresent()) {
            addressService.deleteAddress(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/shopping-cart/update")
    public ResponseEntity<?> updateShoppingCart(@AuthenticationPrincipal User _user, @Valid @RequestBody UserShoppingCartItem request) {
        Optional<User> optionalUser = userService.getUserById(_user.getId());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            switch (request.getAction()) {
                case "add": user.getShoppingCart().put(request.getProduct(), request.getQuantity());
                case "remove": user.getShoppingCart().remove(request.getProduct());
                case "update":
                    if (user.getShoppingCart().containsKey(request.getProduct())) {
                        user.getShoppingCart().put(request.getProduct(), request.getQuantity());
                    } else {
                        return ResponseEntity.badRequest().body("Product not found in shopping cart");
                    }
                    break;
                default:
                    return ResponseEntity.badRequest().body("Invalid action");
            }
            optionalUser = userService.updateUser(user, _user.getId());
            return optionalUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
