package com.zeroone.service;

import com.zeroone.datatransferobjects.UserDtoGET;
import com.zeroone.datatransferobjects.UserDtoPOST;
import com.zeroone.exceptions.EmailAlreadyExistsException;
import com.zeroone.exceptions.FieldTooShortException;
import com.zeroone.exceptions.PasswordTooShortException;
import com.zeroone.model.ConfirmationToken;
import com.zeroone.model.Role;
import com.zeroone.model.User;
import com.zeroone.repository.ConfirmationTokenRepository;
import com.zeroone.repository.RoleRepository;
import com.zeroone.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

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

    public void registerUser(UserDtoPOST userDTOPOST) throws EmailAlreadyExistsException, FieldTooShortException, PasswordTooShortException {

        if (userRepository.existsByEmail(userDTOPOST.getEmail())) {
            throw new EmailAlreadyExistsException("User already exists");
        }

        validateUserFields(userDTOPOST);

        User user = User.builder()
                .firstName(userDTOPOST.getFirstName())
                .lastName(userDTOPOST.getLastName())
                .email(userDTOPOST.getEmail())
                .password(passwordEncoder.encode(userDTOPOST.getPassword()))
                .createdDate(new Date())
                .isDisabled(true)
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


    private void validateUserFields(UserDtoPOST userDTOPOST) throws PasswordTooShortException, FieldTooShortException {
        if (userDTOPOST.getPassword().length() < 7) {
            throw new PasswordTooShortException("Password must be at least 7 characters long");
        }
        if (userDTOPOST.getFirstName().length() < 2) {
            throw new FieldTooShortException("First name must be at least 2 characters long");
        }
        if (userDTOPOST.getLastName().length() < 2) {
            throw new FieldTooShortException("Last name must be at least 2 characters long");
        }
    }

    public List<UserDtoGET> getAllUsersData() {
        List<User> usersList = userRepository.findAll();
        return usersList.stream()
                .map(user -> new UserDtoGET(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getRole(), user.getCreatedDate(), user.isDisabled()))
                .toList();
    }

    private String id;
    private String firstName;
    private String lastName;
    private String password;
    private Role role;
    private Date createdDate;
    public UserDtoPOST getUserData(String email) {
        User user = userRepository.findUserByEmail(email);
        UserDtoPOST userDtoPOST = UserDtoPOST.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .createdDate(user.getCreatedDate())
                .role(user.getRole())
                .build();

        return userDtoPOST;
    }


//    public void registerUser(UserDtoPOST userDTO) {
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
