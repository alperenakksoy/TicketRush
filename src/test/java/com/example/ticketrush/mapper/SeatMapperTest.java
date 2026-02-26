package com.example.ticketrush.mapper;

import com.example.ticketrush.dto.response.SeatDto;
import com.example.ticketrush.entity.Seat;
import com.example.ticketrush.enums.SeatStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Seat Mapper Unit Test")
public class SeatMapperTest {
    private SeatMapper seatMapper;
    private Seat seat1;
    private Seat seat2;
    private static final Long SEAT1_ID = 10L;
    private static final Long SEAT2_ID = 11L;
    @BeforeEach()
    void setUp() {
        seatMapper = new SeatMapper();

        seat1 = new Seat();
        ReflectionTestUtils.setField(seat1, "id", SEAT1_ID);
        seat1.setSeatNumber("A1");
        seat1.setStatus(SeatStatus.AVAILABLE);

        seat2 = new Seat();
        ReflectionTestUtils.setField(seat2, "id", SEAT2_ID);
        seat2.setSeatNumber("A2");
        seat2.setStatus(SeatStatus.AVAILABLE);
    }
    @Test
    @DisplayName("Should map seat entity to SeatDto")
    void toDto_Success(){
        SeatDto dto = seatMapper.toDto(seat1);

        assertNotNull(dto);
        assertEquals(seat1.getId(),dto.getId());
        assertEquals(seat1.getSeatNumber(),dto.getSeatNumber());
        assertEquals(seat1.getStatus(),dto.getStatus());
    }
    @Test
    @DisplayName("Should return null when seat is null")
    void toDto_NullSeat_ReturnsNull() {
        SeatDto dto = seatMapper.toDto(null);
        assertNull(dto);
    }

    @Test
    @DisplayName("Should map ListSeat entities to List SeatDto")
    void ListSeatDto_Success(){
        List<SeatDto> listDto = seatMapper.toDtoList(List.of(seat1,seat2));

        assertNotNull(listDto);
        assertEquals(2,listDto.size());

        assertEquals(SEAT1_ID,listDto.get(0).getId());
        assertEquals("A1",listDto.get(0).getSeatNumber());
        assertEquals(SeatStatus.AVAILABLE,listDto.get(0).getStatus());

        assertEquals(SEAT2_ID,listDto.get(1).getId());
        assertEquals("A2",listDto.get(1).getSeatNumber());
        assertEquals(SeatStatus.AVAILABLE,listDto.get(1).getStatus());
    }
    @Test
    @DisplayName("Should return empty list when input list is null")
    void toResponse_NullListSeats() {
        List<SeatDto> listDto = seatMapper.toDtoList(null);
        assertNotNull(listDto);
        assertTrue(listDto.isEmpty());
        assertEquals(0,listDto.size());
    }
    @Test
    @DisplayName("Should return empty list when empty list is given")
    void toDtoList_EmptyList_ReturnsEmptyList() {
        List<SeatDto> listDto = seatMapper.toDtoList(List.of());

        assertNotNull(listDto);
        assertTrue(listDto.isEmpty());
    }
    }
