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

    @JsonIgnore
    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<Room> rooms;

    @Id
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Vote> votes;

}