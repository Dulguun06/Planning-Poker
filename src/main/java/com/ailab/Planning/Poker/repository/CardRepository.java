package com.ailab.Planning.Poker.repository;

import com.ailab.Planning.Poker.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository <Card, Long>{
}
