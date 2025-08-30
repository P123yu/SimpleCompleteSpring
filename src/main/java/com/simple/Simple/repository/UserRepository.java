package com.simple.Simple.repository;

import com.simple.Simple.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByCityIgnoreCase(String city);

    boolean existsByCityIgnoreCase(String city);

    @Transactional
    void deleteByCityIgnoreCase(String city);

}
