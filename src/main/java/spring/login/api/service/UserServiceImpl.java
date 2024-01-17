package spring.login.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.login.dto.UserRequestDTO;
import spring.login.dto.UserResponseDTO;
import spring.login.exception.custom.InvalidUserException;
import spring.login.exception.custom.UserAlreadyExistsException;
import spring.login.exception.custom.UserNotFoundException;
import spring.login.orm.entity.User;
import spring.login.orm.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertToUserResponseDTO).collect(Collectors.toList());
    }

    private UserResponseDTO convertToUserResponseDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setEmail(user.getEmail());
        return userResponseDTO;
    }

    public boolean doesUserExist(UserRequestDTO userDTO) {
        return userRepository.findByUsername(userDTO.getUsername()).isPresent();
    }

    @Override
    public Long checkUser(UserRequestDTO userDTO) {
        Optional<User> userOptional;
        if (userDTO.getUsername() != null) {
            userOptional = userRepository.findByUsername(userDTO.getUsername());
        } else {
            userOptional = userRepository.findByEmail(userDTO.getEmail());
        }

        if (userOptional.isPresent() && userDTO.getPassword().equals(userOptional.get().getPassword())) {
            return userOptional.get().getId();
        } else {
            throw new InvalidUserException();
        }
    }

    public UserResponseDTO getUserDetailsById(Long userId) {
        User user = userRepository.findUserById(userId).orElseThrow(UserNotFoundException::new);
        return convertToUserResponseDTO(user);
    }

    @Override
    public UserResponseDTO addUser(UserRequestDTO userDTO) {
        Optional<User> existingUser = userRepository.findByUsername(userDTO.getUsername());
        if(existingUser.isPresent()) {
            throw new UserAlreadyExistsException();
        }
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user = userRepository.save(user);
        return convertToUserResponseDTO(user);
    }

    @Override
    public UserResponseDTO updateUser(Long userId, UserRequestDTO userDTO) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user = userRepository.save(user);
        return convertToUserResponseDTO(user);
    }

    @Override
    public void deleteUser(Long userId) {
        if(!userRepository.existsById(userId)) {
            throw new UserNotFoundException();
        }
        userRepository.deleteById(userId);
    }
}
