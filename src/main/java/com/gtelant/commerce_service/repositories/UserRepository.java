package com.gtelant.commerce_service.repositories;

import com.gtelant.commerce_service.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<Users, Integer>, JpaSpecificationExecutor<Users> {

    Optional<Users> findByEmail(String email);
}
