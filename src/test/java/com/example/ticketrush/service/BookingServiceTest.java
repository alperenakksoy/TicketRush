package com.example.ticketrush.service;


import com.example.ticketrush.entity.Event;
import com.example.ticketrush.entity.Seat;
import com.example.ticketrush.entity.User;
import com.example.ticketrush.enums.SeatStatus;
import com.example.ticketrush.repository.BookingRepository;
import com.example.ticketrush.repository.EventRepository;
import com.example.ticketrush.repository.SeatRepository;
import com.example.ticketrush.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
 class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private SeatRepository seatRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
     void setUp() {

        User user = new User();
        ReflectionTestUtils.setField(user, "id", 1L);
        user.setEmail("test@test.com");

        Event event = new Event();
        ReflectionTestUtils.setField(event, "id", 100L);

        Seat seat1 = new Seat();
        ReflectionTestUtils.setField(seat1, "id", 1L);
        seat1.setSeatNumber("A1");
        seat1.setStatus(SeatStatus.AVAILABLE);
        seat1.setEvent(event);

        Seat seat2 = new Seat();
        ReflectionTestUtils.setField(seat1, "id", 2L);
        seat2.setSeatNumber("A2");
        seat2.setStatus(SeatStatus.AVAILABLE);
        seat2.setEvent(event);





    }
}
