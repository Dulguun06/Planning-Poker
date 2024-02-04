package com.ailab.Planning.Poker.controller;


import com.ailab.Planning.Poker.entity.Card;
import com.ailab.Planning.Poker.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/card")
public class CardController {
    @Autowired
    CardRepository cardRepository;

    @GetMapping("")
    public List<Card> getCards(){
        return cardRepository.findAll();
    }
}
