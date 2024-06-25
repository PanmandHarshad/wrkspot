package com.wrkspot.customer.service;

import com.wrkspot.customer.config.UserInfoUserDetails;
import com.wrkspot.customer.entity.UserInfo;
import com.wrkspot.customer.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class that implements the UserDetailsService interface to load user details for authentication.
 */
@Service
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    /**
     * Loads user details by username from the UserInfoRepository.
     *
     * @param username the username of the user to load details for.
     * @return UserDetails containing the user details loaded from UserInfoRepository.
     * @throws UsernameNotFoundException if the user is not found in the repository.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = userInfoRepository.findByName(username);
        return userInfo.map(UserInfoUserDetails::new).orElseThrow(() ->
                new UsernameNotFoundException("User not found : " + username));
    }
}
