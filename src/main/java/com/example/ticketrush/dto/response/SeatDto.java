package com.example.ticketrush.dto.response;

import com.example.ticketrush.enums.SeatStatus;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class SeatDto implements Serializable {

    private Long id;

    private String seatNumber;

    private SeatStatus status;
}
