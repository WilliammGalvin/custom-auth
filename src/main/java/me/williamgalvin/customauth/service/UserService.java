package me.williamgalvin.customauth.service;

import me.williamgalvin.customauth.data.UserRepository;
import me.williamgalvin.customauth.data.models.User;
import me.williamgalvin.customauth.data.models.UserDTO;
import me.williamgalvin.customauth.exceptions.UserCollisionException;
import me.williamgalvin.customauth.exceptions.UserNotFoundException;
import me.williamgalvin.customauth.utils.HashUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void createUser(UserDTO userDTO) throws UserCollisionException {
        if (userRepository.findByUsername(userDTO.getUsername()) != null)
            throw new UserCollisionException();

        User newUser = new User(
                userDTO.getUsername(),
                HashUtil.hashString(userDTO.getPassword())
        );

        userRepository.save(newUser);
    }

    public void deleteUserById(long id) throws UserNotFoundException {
        if (userRepository.findById(id) == null)
            throw new UserNotFoundException();

        userRepository.deleteById(id);
    }

}
