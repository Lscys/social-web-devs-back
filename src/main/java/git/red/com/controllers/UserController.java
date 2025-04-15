package git.red.com.controllers;

import git.red.com.dto.UserDto;
import git.red.com.models.User;
import git.red.com.repository.UserRepository;
import git.red.com.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServices userService;

    private final UserRepository userRepository;

    public UserController(UserServices userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/create")
    private String createUser (@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> allUsers() {
        List <User> users = userService.allUsers();

        return ResponseEntity.ok(users);
    }



}
