package com.example.ticketrush.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BookingRequest {

    @NotNull(message = "User ID can not be empty")
    private Long userId;

    @NotNull(message = "Event ID can not be empty")
    private Long eventId;

    @NotEmpty(message = "Seat ID can not be empty")
    List<Long> seatIds;
}

