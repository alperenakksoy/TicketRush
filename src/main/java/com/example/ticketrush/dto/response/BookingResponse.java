package com.example.ticketrush.dto.response;

import com.example.ticketrush.enums.BookingStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class BookingResponse {

    private BookingStatus bookingStatus;

    private double totalAmount;

    private LocalDateTime bookingDate;

    private List<String> seatNumbers;
}
