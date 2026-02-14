package com.example.ticketrush.mapper;

import com.example.ticketrush.dto.response.SeatDto;
import com.example.ticketrush.entity.Seat;

public class SeatMapper {

    public static SeatDto toResponse(Seat seat) {
        if(seat == null) {
            return null;
        }
        return SeatDto.builder()
                .id(seat.getId())
                .seatNumber(seat.getSeatNumber())
                .status(seat.getStatus())
                .build();
    }
}
