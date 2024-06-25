package com.wrkspot.customer.service;

import com.wrkspot.customer.entity.UserInfo;
import com.wrkspot.customer.repository.UserInfoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class for initializing and managing user-related operations, such as creating an admin user.
 */
@Service
public class UserService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.roles}")
    private String adminRoles;

    /**
     * Initializes the UserService bean after dependency injection and creates an admin user if it doesn't already exist.
     */
    @PostConstruct
    private void init() {
        createAdminUser();
    }

    /**
     * Creates an admin user if it doesn't already exist in the database.
     */
    private void createAdminUser() {
        if (userInfoRepository.findByName(adminUsername).isEmpty()) {
            UserInfo userInfo = new UserInfo();
            userInfo.setName(adminUsername);
            userInfo.setEmail(adminEmail);
            userInfo.setPassword(passwordEncoder.encode(adminPassword)); // Encode the password
            userInfo.setRoles(adminRoles);
            userInfoRepository.save(userInfo);
        }

//        if (userInfoRepository.findByName("harshad").isEmpty()) {
//            UserInfo userInfo = new UserInfo();
//            userInfo.setName("harshad");
//            userInfo.setEmail("harshad@gmail.com");
//            userInfo.setPassword(passwordEncoder.encode("harshad")); // Encode the password
//            userInfo.setRoles("ROLE_USER");
//            userInfoRepository.save(userInfo);
//        }
    }
}
