package com.globalseguros.lead.service;


import com.globalseguros.lead.dto.UserDTO;
import com.globalseguros.lead.entity.User;
import com.globalseguros.lead.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // encriptar contraseña
        return userRepository.save(user);
    }

    public String authenticate(UserDTO userDTO) throws Exception {
        Optional<User> userOptional = userRepository.findByEmail(userDTO.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
                return jwtService.generateToken(user.getEmail()); // Generar JWT
            } else {
                throw new Exception("Credenciales inválidas");
            }
        } else {
            throw new Exception("El usuario no existe");
        }
    }
}
