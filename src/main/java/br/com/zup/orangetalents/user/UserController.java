package br.com.zup.orangetalents.user;

import br.com.zup.orangetalents.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable("id") Integer id) throws UserNotFoundException {
        return userService.getUserById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntity saveUser(@RequestBody UserEntity user) {
        return userService.saveUser(user);
    }
}
