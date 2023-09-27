package spring.login.api.service;

import spring.login.dto.UserRequestDTO;
import spring.login.dto.UserResponseDTO;

import java.util.List;

public interface UserService {

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO addUser(UserRequestDTO userDTO);

    boolean checkUser(UserRequestDTO userDTO);

    boolean doesUserExist(UserRequestDTO userDTO);
    UserResponseDTO updateUser(Long userId, UserRequestDTO userDTO);
    void deleteUser(Long userId);
}
