package com.example.ticketrush.repository;

import com.example.ticketrush.entity.Seat;
import com.example.ticketrush.enums.SeatStatus;
import io.lettuce.core.dynamic.annotation.Param;
import jakarta.persistence.LockModeType;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<@NonNull Seat,@NonNull Long> {

    List<Seat> findByEventIdAndStatus(Long eventId, SeatStatus status);

    long countByEventIdAndStatus(Long eventId, SeatStatus status);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM Seat s WHERE s.id = :id")
    Optional<Seat> findByIdWithLock(@Param("id") Long id);}
