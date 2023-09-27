package spring.login.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.login.api.service.UserService;
import spring.login.dto.UserRequestDTO;
import spring.login.dto.UserResponseDTO;
import spring.login.exception.custom.InvalidUserException;
import spring.login.exception.custom.UserAlreadyExistsException;
import spring.login.exception.custom.UserNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class LoginController {


    @Autowired
    private UserService userService;

    @GetMapping("/getIds")
    public ResponseEntity<List<Long>> getIds() {
        List<UserResponseDTO> users = userService.getAllUsers();
        if (users.isEmpty()) {
            throw new UserNotFoundException();
        }
        List<Long> userIds = users.stream().map(UserResponseDTO::getId).collect(Collectors.toList());
        return ResponseEntity.ok(userIds);
    }

    @PostMapping("/addUser")
    public ResponseEntity<UserResponseDTO> addUser(@RequestBody UserRequestDTO userDTO) {
        if (userService.doesUserExist(userDTO)) {
            throw new UserAlreadyExistsException();
        }
        UserResponseDTO responseDTO = userService.addUser(userDTO);
        return ResponseEntity.ok(responseDTO);
    }


    @PostMapping("/checkUser")
    public ResponseEntity<Boolean> checkUser(@RequestBody UserRequestDTO userDTO) {
        if (userDTO.getPassword() == null || (userDTO.getUsername() == null && userDTO.getEmail() == null)) {
            throw new InvalidUserException();
        }
        boolean isValid = userService.checkUser(userDTO);
        return ResponseEntity.ok(isValid);
    }


    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long userId, @RequestBody UserRequestDTO userDTO) {
        UserResponseDTO updatedUser = userService.updateUser(userId, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}

