package com.wrkspot.customer.config;

import com.wrkspot.customer.entity.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class implements the UserDetails interface, providing core user information.
 * It serves as a bridge between the application's UserInfo entity and Spring Security's user details service.
 */
public class UserInfoUserDetails implements UserDetails {

    /**
     * The username of the user.
     */
    private final String name;

    /**
     * The password of the user.
     */
    private final String password;

    /**
     * The authorities granted to the user.
     */
    private final List<GrantedAuthority> authorities;

    /**
     * Constructs a UserInfoUserDetails object using the provided UserInfo.
     *
     * @param userInfo the UserInfo object containing user details.
     */
    public UserInfoUserDetails(UserInfo userInfo) {
        this.name = userInfo.getName(); // Sets the username from the UserInfo object.
        this.password = userInfo.getPassword(); // Sets the password from the UserInfo object.

        // Converts roles from a comma-separated string to a list of GrantedAuthority objects.
        authorities = Arrays.stream(userInfo.getRoles().split(","))
                .map(SimpleGrantedAuthority::new) // Maps each role to a SimpleGrantedAuthority object.
                .collect(Collectors.toList()); // Collects the authorities into a list.
    }

    /**
     * Returns the authorities granted to the user.
     *
     * @return a collection of GrantedAuthority objects.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * Returns the password of the user.
     *
     * @return the user's password.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Returns the username of the user.
     *
     * @return the user's username.
     */
    @Override
    public String getUsername() {
        return name;
    }
}
