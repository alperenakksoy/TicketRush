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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
@DisplayName("BookingService Unit Test")
 class BookingServiceTest {
    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private EventRepository eventRepository;
    @Mock
    private SeatRepository seatRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private BookingMapper bookingMapper;
    @InjectMocks
    private BookingService bookingService;

    private static final long USER_ID = 1L;
    private static final Long EVENT_ID = 100L;
    private static final Long SEAT1_ID = 10L;
    private static final Long SEAT2_ID = 11L;

    private User user;
    private Event event;
    private Seat seat1;
    private Seat seat2;
    private BookingRequest request;
    @BeforeEach
     void setUp() {
        user = new User();
        ReflectionTestUtils.setField(user, "id", USER_ID);
        user.setEmail("test@test.com");
        user.setFullName("Ahmet Alperen Aksoy");

        event = new Event();
        ReflectionTestUtils.setField(event, "id", EVENT_ID);
        event.setPrice(50.0);

        seat1 = createSeat(SEAT1_ID,"A1",event);
        seat2 = createSeat(SEAT2_ID,"A2", event);

       request = BookingRequest.builder()
               .eventId(EVENT_ID)
               .seatIds(List.of(SEAT1_ID,SEAT2_ID))
               .userId(USER_ID)
               .build();
    }

    @Nested
    @DisplayName("Create Booking Tests")
    class createBookingTest {
       @Test
       @DisplayName("Should create")
       void createBooking_Success(){
          // arrange

          when(userRepository.findById(request.getUserId()))
                  .thenReturn(Optional.of(user));

          when(seatRepository.findAllById(request.getSeatIds()))
                  .thenReturn(List.of(seat1, seat2));


          when(eventRepository.findById(request.getEventId())).thenReturn(Optional.of(event));

          Booking savedBooking = Booking.builder()
                  .user(user)
                  .status(BookingStatus.COMPLETED)
                  .totalAmount(100.0)
                  .build();
          when(bookingRepository.save(any(Booking.class))).thenReturn(savedBooking);

          BookingResponse expectedResponse = BookingResponse.builder()
                  .bookingStatus(BookingStatus.COMPLETED)
                  .totalAmount(100.0)
                  .seatNumbers(List.of("A1", "A2"))
                  .build();
          when(bookingMapper.toResponse(any(Booking.class))).thenReturn(expectedResponse);

          // when
          BookingResponse response = bookingService.createBooking(request);

          // then
          assertNotNull(response);
          assertEquals(100.0,response.getTotalAmount());
          assertEquals(SeatStatus.BOOKED, seat1.getStatus());
          assertEquals(SeatStatus.BOOKED,seat2.getStatus());

          verify(seatRepository,times(1)).saveAll(anyList());
          verify(bookingRepository,times(1)).save(any(Booking.class));
       }

       @Test
       @DisplayName("Should throw BusinessException when user not exist")
       void userNotFound(){
          // Arrange
          when(userRepository.findById(USER_ID)).thenReturn(Optional.empty());
          // act & assert
          BusinessException exception = assertThrows(BusinessException.class,()-> bookingService.createBooking(request));

          assertEquals("User not found with id: " + request.getUserId(),exception.getMessage());
          verify(bookingRepository,never()).save(any());
       }
       @Test
       @DisplayName("Should throw BusinessException when seat is not available")
       void seatNotAvailable(){

          seat1.setStatus(SeatStatus.BOOKED);

          when(userRepository.findById(USER_ID)).thenReturn(Optional.of(user));
          when(seatRepository.findAllById(request.getSeatIds())).thenReturn(List.of(seat1,seat2));
          when(eventRepository.findById(request.getEventId())).thenReturn(Optional.of(event));

          BusinessException exception = assertThrows(BusinessException.class,()-> bookingService.createBooking(request));

          assertEquals("Seat " + seat1.getSeatNumber() +" is not available at the moment",exception.getMessage());
          verify(bookingRepository,never()).save(any());
       }

       @Test
       @DisplayName("Should throw BusinessException when seat does not belong to event")
       void seatNotBelongToEvent(){

          Event otherEvent = new Event();
          ReflectionTestUtils.setField(otherEvent, "id", 999L);

          seat2.setEvent(otherEvent);

          when(userRepository.findById(USER_ID)).thenReturn(Optional.of(user));
          when(seatRepository.findAllById(request.getSeatIds())).thenReturn(List.of(seat1,seat2));
          when(eventRepository.findById(request.getEventId())).thenReturn(Optional.of(event));

          BusinessException exception = assertThrows(BusinessException.class,()-> bookingService.createBooking(request));

          assertEquals("Seat " + seat2.getSeatNumber() + " does not belong to the event ", exception.getMessage());
       }
    }
    // Helper method
    private Seat createSeat(Long id, String seatNumber, Event event){
       Seat seat = new Seat();
       ReflectionTestUtils.setField(seat, "id", id);
       seat.setSeatNumber(seatNumber);
       seat.setStatus(SeatStatus.AVAILABLE);
       seat.setEvent(event);
       return seat;
    }
}
