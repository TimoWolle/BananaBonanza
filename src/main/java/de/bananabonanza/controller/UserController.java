package de.bananabonanza.controller;

import de.bananabonanza.dto.ShoppingCartItem;
import de.bananabonanza.entity.Product;
import de.bananabonanza.entity.User;
import de.bananabonanza.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@PreAuthorize("hasRole('TODO_FULLRIGHTS')")
public class UserController {
    private final UserService userService;
    @PutMapping
    @PreAuthorize("hasRole('TODO_UPDATE')")
    public User update(@Valid @RequestBody User _user){
        User toDo = this.userService.getUserById(_user.getId());
        return userService.updateUser(toDo);
    }

    @PutMapping("/ShoppingCart-Add")
    public User inShoppingCart(@AuthenticationPrincipal User _user, @Valid @RequestBody ShoppingCartItem _shoppingcartitem){
        User user = this.userService.getUserById(_user.getId());
        user.setShoppingCart(_shoppingcartitem.getShoppingCart());
        return userService.updateUser(user);
    }

}
