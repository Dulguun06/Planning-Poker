package com.ailab.Planning.Poker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"task_id", "username"})})
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "estimation")
    private Long estimation;

    @Column(name = "task_id", nullable = false)
    private Long taskId;

    @Column(name = "username", nullable = false)
    private String username;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "task_id", insertable = false, updatable = false)
    private Task task;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "username", insertable = false, updatable = false)
    private User user;
}