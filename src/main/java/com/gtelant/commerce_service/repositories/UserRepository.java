package com.gtelant.commerce_service.repositories;

import com.gtelant.commerce_service.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {
}
