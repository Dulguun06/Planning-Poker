package com.ailab.Planning.Poker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "image")
    private String image;

    @JsonIgnore
    @ManyToMany
    Set<Room> joined_room;


    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Task> tasks;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Vote> votes;
}