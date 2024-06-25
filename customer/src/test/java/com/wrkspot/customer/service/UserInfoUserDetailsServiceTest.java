package com.wrkspot.customer.service;

import com.wrkspot.customer.entity.UserInfo;
import com.wrkspot.customer.exception.ResourceNotFoundException;
import com.wrkspot.customer.repository.UserInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserInfoUserDetailsServiceTest {

    @Mock
    private UserInfoRepository userInfoRepository;

    @InjectMocks
    private UserInfoUserDetailsService userInfoUserDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadUserByUsernameSuccess() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("user");
        userInfo.setRoles("ADMIN");

        when(userInfoRepository.findByName("user")).thenReturn(Optional.of(userInfo));

        UserDetails userDetails = userInfoUserDetailsService.loadUserByUsername("user");

        assertNotNull(userDetails);
        assertEquals("user", userDetails.getUsername());
    }

    @Test
    void testLoadUserByUsernameNotFound() {
        when(userInfoRepository.findByName("user")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            userInfoUserDetailsService.loadUserByUsername("user");
        });
    }
}
