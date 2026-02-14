package com.example.ticketrush.controller;


import com.example.ticketrush.dto.response.SeatDto;
import com.example.ticketrush.service.EventService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping("/{id}/seats")
    public ResponseEntity<@NonNull List<SeatDto>> getEventSeats(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getSeatsByEventId(id));
    }
}
