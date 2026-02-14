package com.example.ticketrush.mapper;

import com.example.ticketrush.dto.response.SeatDto;
import com.example.ticketrush.entity.Seat;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class SeatMapper implements Serializable {

    public SeatDto toDto(Seat seat) {
        if(seat == null) {
            return null;
        }
        return SeatDto.builder()
                .id(seat.getId())
                .seatNumber(seat.getSeatNumber())
                .status(seat.getStatus())
                .build();
    }
    public List<SeatDto> toDtoList(List<Seat> seats) {
        if (seats == null) {
            return List.of();
        }
        return seats.stream()
                .map(this::toDto)
                .toList();
    }
}
