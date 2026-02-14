package com.example.ticketrush.controller;

import com.example.ticketrush.dto.request.BookingRequest;
import com.example.ticketrush.dto.response.BookingResponse;
import com.example.ticketrush.service.BookingService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<@NonNull BookingResponse> createBooking(@Valid @RequestBody BookingRequest request) {

        BookingResponse bookingResponse = bookingService.createBooking(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingResponse);
    }
}
