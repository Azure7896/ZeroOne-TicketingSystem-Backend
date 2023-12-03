package com.zeroone.service;

import com.zeroone.datatransferobjects.UserDto;
import com.zeroone.exceptions.EmailAlreadyExistsException;
import com.zeroone.exceptions.FieldTooShortException;
import com.zeroone.exceptions.PasswordTooShortException;
import com.zeroone.model.ConfirmationToken;
import com.zeroone.model.Role;
import com.zeroone.model.User;
import com.zeroone.repository.ConfirmationTokenRepository;
import com.zeroone.repository.RoleRepository;
import com.zeroone.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;


import java.util.Date;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, ConfirmationTokenRepository confirmationTokenRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public void registerUser(UserDto userDTO) throws EmailAlreadyExistsException, FieldTooShortException, PasswordTooShortException {

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new EmailAlreadyExistsException("User already exists");
        }

        validateUserFields(userDTO);

        User user = User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .createdDate(new Date())
                .isActive(false)
                .role(roleRepository.findRoleByName("USER"))
                .build();

        userRepository.save(user);
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationTokenRepository.save(confirmationToken);

    }

    public boolean validatePassword(String email, String password) {
        User user = userRepository.findUserByEmail(email);
        return !passwordEncoder.matches(password, user.getPassword());
    }


    private void validateUserFields(UserDto userDTO) throws PasswordTooShortException, FieldTooShortException {
        if (userDTO.getPassword().length() < 7) {
            throw new PasswordTooShortException("Password must be at least 7 characters long");
        }
        if (userDTO.getFirstName().length() < 2) {
            throw new FieldTooShortException("First name must be at least 2 characters long");
        }
        if (userDTO.getLastName().length() < 2) {
            throw new FieldTooShortException("Last name must be at least 2 characters long");
        }
    }

    public UserDto getUserData(String email) {
        User user = userRepository.findUserByEmail(email);
        UserDto userDto = UserDto.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();

        return userDto;
    }


//    public void registerUser(UserDto userDTO) {
//        User user = User.builder()
//                .firstName(userDTO.getFirstName())
//                .lastName(userDTO.getLastName())
//                .email(userDTO.getEmail())
//                .password(passwordEncoder.encode(userDTO.getPassword()))
//                .createdDate(new Date())
//                .isActive(false)
//                .roles(List.of(new Role("USER")))
//                .build();
//
//        userRepository.save(user);
//        ConfirmationToken confirmationToken = new ConfirmationToken(user);
//        confirmationTokenRepository.save(confirmationToken);
//        emailSenderService.sendEmail(user.getEmail(), "Witaj w OrionStore, "
//                + user.getFirstName() + "!", "Aby potwierdzić swoje konto kliknij w link: "
//                + "http://localhost:8080/confirm-account?token=" + confirmationToken.getConfirmationToken());
//    }

//    public boolean activateAccount(String confirmationToken) {
//        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
//        if (token != null) {
//            User user = userRepository.findByEmail(token.getUser().getEmail());
//            user.setActive(true);
//            userRepository.save(user);
//            confirmationTokenRepository.delete(token);
//            return true;
//        } else {
//            return false;
//        }
//    }
}
