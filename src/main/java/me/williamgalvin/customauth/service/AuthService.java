package me.williamgalvin.customauth.service;

import me.williamgalvin.customauth.data.UserRepository;
import me.williamgalvin.customauth.data.models.User;
import me.williamgalvin.customauth.data.models.UserDTO;
import me.williamgalvin.customauth.exceptions.UserNotFoundException;
import me.williamgalvin.customauth.utils.HashUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean authenticate(UserDTO userDTO) throws UserNotFoundException {
        User user = userRepository.findByUsername(userDTO.getUsername());

        if (user == null)
            throw new UserNotFoundException();

        return HashUtil.equals(userDTO.getPassword(), user.getPassword());
    }

}
