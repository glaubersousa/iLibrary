package com.isystem.ilibraryapi.repository;

import com.isystem.ilibraryapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByCpf(String cpf);

    List<User> findByName(String name);

    Optional<User> findByEmail(String email);

    Optional<User> findByPhone(Long phone);
}
