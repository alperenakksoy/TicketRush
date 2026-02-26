package com.example.ticketrush.mapper;

import com.example.ticketrush.dto.response.BookingResponse;
import com.example.ticketrush.entity.Booking;
import com.example.ticketrush.entity.Seat;
import com.example.ticketrush.enums.BookingStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Booking Mapper Unit Test")
class BookingMapperTest {
    private BookingMapper bookingMapper;
    private Booking booking;
    private Seat seat1;
    private Seat seat2;
    @BeforeEach
    void setUp() {
        bookingMapper = new BookingMapper();

        seat1 = new Seat();
        seat1.setSeatNumber("A1");

        seat2 = new Seat();
        seat2.setSeatNumber("A2");

        booking = Booking.builder()
                .status(BookingStatus.COMPLETED)
                .totalAmount(250.0)
                .bookingDate(LocalDateTime.of(2024, 5, 20, 15, 30))
                .build();

        booking.setSeats(List.of(seat1,seat2));
    }

    @Test
    @DisplayName("Should map Booking entity to BookingResponse DTO")
    void toResponse_Success() {
        BookingResponse response = bookingMapper.toResponse(booking);

        assertNotNull(response);
        assertEquals(BookingStatus.COMPLETED, response.getBookingStatus());
        assertEquals(250.0, response.getTotalAmount());
        assertEquals(LocalDateTime.of(2024, 5, 20, 15, 30), response.getBookingDate());
        assertEquals(2, response.getSeatNumbers().size());

        assertTrue(response.getSeatNumbers().contains("A1"));
        assertTrue(response.getSeatNumbers().contains("A2"));

    }

    @Test
    @DisplayName("Should return null when booking is null")
    void toResponse_NullBooking() {
        BookingResponse response = bookingMapper.toResponse(null);
        assertNull(response);
    }
}