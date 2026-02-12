package com.example.ticketrush.repository;

import com.example.ticketrush.entity.Event;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<@NonNull Event,@NonNull Long> {
    Optional<Event> findByUserId(Long userId);
}
