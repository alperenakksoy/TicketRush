package com.example.ticketrush.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name="events")
@Getter
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String name;

    @Setter
    @Column(nullable = true)
    private String description;

    @Setter
    @Column(name = "event_date", nullable = false)
    private LocalDateTime date;

    @CreatedDate
    @Column(nullable = false,  updatable = false)
    private LocalDateTime createdAt;

}
