package com.doit_well.customerservice.repository;

import com.doit_well.customerservice.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByEmail(String email);
}
