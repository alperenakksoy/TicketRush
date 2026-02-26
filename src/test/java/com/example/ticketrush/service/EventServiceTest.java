package com.example.ticketrush.service;

import com.example.ticketrush.dto.response.SeatDto;
import com.example.ticketrush.entity.Seat;
import com.example.ticketrush.enums.SeatStatus;
import com.example.ticketrush.exception.BusinessException;
import com.example.ticketrush.mapper.SeatMapper;
import com.example.ticketrush.repository.SeatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
@DisplayName("Booking Service Unit Test")
class EventServiceTest {
    @Mock
    private SeatRepository seatRepository;
    @Mock
    private SeatMapper seatMapper;
    @InjectMocks
    private EventService eventService;
    private static final Long EVENT_ID = 100L;
    private Seat seat1;
    private SeatDto seatDto1;

    @BeforeEach
    void setUp() {
        seat1 = new Seat();
        seat1.setSeatNumber("A1");
        seat1.setStatus(SeatStatus.AVAILABLE);

        seatDto1 = SeatDto.builder()
                .id(1L)
                .seatNumber("A1")
                .status(SeatStatus.AVAILABLE)
                .build();
    }
    @Nested
    @DisplayName("getSeatsByEventId() Tests")
    class GetSeatsByEventIdTests {

        @Test
        @DisplayName("Should return list of SeatDto when seats are found for the event")
        void getSeats_Success() {
            List<Seat> seats = List.of(seat1);
            List<SeatDto> seatDtos = List.of(seatDto1);

            when(seatRepository.findAllByEventId(EVENT_ID)).thenReturn(seats);
            when(seatMapper.toDtoList(seats)).thenReturn(seatDtos);

            List<SeatDto> result = eventService.getSeatsByEventId(EVENT_ID);

            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals("A1", result.get(0).getSeatNumber());

            verify(seatRepository, times(1)).findAllByEventId(EVENT_ID);
            verify(seatMapper, times(1)).toDtoList(seats);
        }

        @Test
        @DisplayName("Should throw BusinessException when no seats are found for the event")
        void getSeats_NoSeatsFound_ThrowsException() {
            when(seatRepository.findAllByEventId(EVENT_ID)).thenReturn(List.of());

            BusinessException exception = assertThrows(BusinessException.class, () -> eventService.getSeatsByEventId(EVENT_ID));

            assertEquals("No seats found for this event.", exception.getMessage());
            verify(seatMapper, never()).toDtoList(any());
        }
    }
}