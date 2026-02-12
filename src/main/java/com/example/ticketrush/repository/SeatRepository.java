package com.example.ticketrush.repository;

import com.example.ticketrush.entity.Seat;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<@NonNull Seat,@NonNull Long> {
    Optional<Seat> findById(Long id);
}
