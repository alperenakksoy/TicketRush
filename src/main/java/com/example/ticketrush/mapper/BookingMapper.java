package com.example.ticketrush.mapper;

import com.example.ticketrush.dto.response.BookingResponse;
import com.example.ticketrush.entity.Booking;
import com.example.ticketrush.entity.Seat;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.List;

@Component
public class BookingMapper {
// toEntity method is in BookinService
    public BookingResponse toResponse(Booking booking) {
        if(booking == null) {
            return null;
        }

        List<String> seatNumbers = booking.getSeats() != null ? booking.getSeats().stream().map(Seat::getSeatNumber).toList()
                : Collections.emptyList();

        return BookingResponse.builder()
                .bookingStatus(booking.getStatus())
                .totalAmount(booking.getTotalAmount())
                .bookingDate(booking.getBookingDate())
                .seatNumbers(seatNumbers)
                .build();
    }
}
