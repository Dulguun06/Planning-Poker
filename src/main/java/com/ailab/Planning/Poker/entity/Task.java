package com.ailab.Planning.Poker.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description1", nullable = false)
    private String description;

    @Column(name = "due", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date due;

    @Column(name = "estimation")
    private Long estimation;

    @Column(name = "room_id")
    private Long room_id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "room_id", insertable = false, updatable = false)
    private Room room;

    @JsonIgnore
    @OneToMany(mappedBy = "task")
    private List<Vote> votes;
}