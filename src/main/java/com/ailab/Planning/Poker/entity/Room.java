package com.ailab.Planning.Poker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "password1", nullable = false)
    private String password;

    @Column(name = "room_name", nullable = false)
    private String room_name;

    @Column(name = "url", nullable = false)
    private String url;

    @OneToMany(mappedBy = "room")
    private List<Task> tasks;

}