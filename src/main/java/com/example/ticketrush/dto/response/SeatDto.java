package com.example.ticketrush.dto.response;

import com.example.ticketrush.enums.SeatStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatDto implements Serializable {

    private Long id;

    private String seatNumber;

    private SeatStatus status;
}
