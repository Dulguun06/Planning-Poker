package com.ailab.Planning.Poker.repository;

import com.ailab.Planning.Poker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
