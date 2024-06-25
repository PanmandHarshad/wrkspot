package com.wrkspot.customer.repository;

import com.wrkspot.customer.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for performing CRUD operations on UserInfo entities.
 * Extends JpaRepository to inherit basic database operations.
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

    /**
     * Finds a user by their username.
     *
     * @param username the username to search for
     * @return an Optional containing the UserInfo if found, or empty otherwise
     */
    Optional<UserInfo> findByName(String username);
}
