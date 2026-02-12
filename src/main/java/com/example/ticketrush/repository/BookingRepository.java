package com.example.ticketrush.repository;

import com.example.ticketrush.entity.Booking;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<@NonNull Booking,@NonNull Long> {
    Optional<Booking> findByUserId(Long userId);
}
