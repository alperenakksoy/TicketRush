package com.example.ticketrush.repository;


import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name="bookings")
@Getter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


}
