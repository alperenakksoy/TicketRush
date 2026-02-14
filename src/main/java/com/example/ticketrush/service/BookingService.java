package com.example.ticketrush.service;


import com.example.ticketrush.dto.request.BookingRequest;
import com.example.ticketrush.dto.response.BookingResponse;
import com.example.ticketrush.entity.Booking;
import com.example.ticketrush.entity.Event;
import com.example.ticketrush.entity.Seat;
import com.example.ticketrush.entity.User;
import com.example.ticketrush.enums.BookingStatus;
import com.example.ticketrush.enums.SeatStatus;
import com.example.ticketrush.exception.BusinessException;
import com.example.ticketrush.mapper.BookingMapper;
import com.example.ticketrush.repository.BookingRepository;
import com.example.ticketrush.repository.EventRepository;
import com.example.ticketrush.repository.SeatRepository;
import com.example.ticketrush.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final EventRepository eventRepository;
    private final SeatRepository seatRepository;
    private final UserRepository userRepository;

    public BookingResponse createBooking(BookingRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new BusinessException("User not found with id: " + request.getUserId()));

        List<Seat> seats = seatRepository.findAllById(request.getSeatIds());

        Event event = eventRepository.findById(request.getEventId())
                .orElseThrow(() -> new BusinessException("Event not found with id: " + request.getEventId()));

        if(seats.isEmpty()) {
            throw new BusinessException("Seats not found");
        }

        for (Seat seat : seats) {
            if(seat.getStatus() != SeatStatus.AVAILABLE) {
                throw new BusinessException("Seat " + seat.getSeatNumber() +" is not available at the moment");
            }

            if(!seat.getEvent().getId().equals(request.getEventId())) {
                throw new BusinessException("Seat " + seat.getSeatNumber() + " does not belong to the event ");
            }
        }

        double totalAmount = seats.size() * event.getPrice();

        Booking booking = Booking.builder()
                .user(user)
                .status(BookingStatus.COMPLETED)
                .totalAmount(totalAmount)
                .build();

        Booking savedBooking = bookingRepository.save(booking);

        for(Seat seat : seats) {
            seat.setStatus(SeatStatus.BOOKED);
            seat.setBooking(savedBooking);
        }

        seatRepository.saveAll(seats);

        savedBooking.setSeats(seats);

        return BookingMapper.toResponse(savedBooking);
    }

}
