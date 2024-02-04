package com.ailab.Planning.Poker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "url")
    private String url;

    @Column(name = "capacity")
    private Long capacity;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(
            name = "room_users",
            uniqueConstraints = @UniqueConstraint(columnNames = {"username", "room_id"}),
            joinColumns = @JoinColumn(
                    name = "room_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "username", referencedColumnName = "username")
    )
    private List<User> users;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room", cascade = CascadeType.ALL)
    private List<Task> tasks;

    public void addUser(User user) {
        this.getUsers().add(user);
    }

    public void removeUser(User user) {
        this.getUsers().remove(user);
    }
}