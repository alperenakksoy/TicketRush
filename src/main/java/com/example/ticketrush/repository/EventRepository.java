package com.example.ticketrush.repository;

import com.example.ticketrush.entity.Event;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface EventRepository extends JpaRepository<@NonNull Event,@NonNull Long> {

    Page<@NonNull Event> findByDateAfter(LocalDateTime date, Pageable pageable);

    Page<@NonNull Event> findByNameContainingIgnoreCase(String name, Pageable pageable);
}