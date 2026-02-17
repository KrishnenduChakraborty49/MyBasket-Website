package com.jpa.concepts.jpa_concepts.Repository;

import com.jpa.concepts.jpa_concepts.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findByName(String name);
    //List<User> findByEmail(String email);
    List<User> findByPassword(String password);
    List<User> findByUserImageUrl(String userImageUrl);
    Optional<User> findByEmail(String username);
}
