package com.example.ticketrush.service;

import com.example.ticketrush.dto.response.SeatDto;
import com.example.ticketrush.entity.Seat;
import com.example.ticketrush.exception.BusinessException;
import com.example.ticketrush.mapper.SeatMapper;
import com.example.ticketrush.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventService {

    private final SeatRepository seatRepository;
    private final SeatMapper seatMapper;

    @Cacheable(value = "event_seats", key = "#eventId")
    @Transactional(readOnly = true)
    public List<SeatDto> getSeatsByEventId(Long eventId) {

        List<Seat> seats = seatRepository.findAllByEventId(eventId);

        if (seats.isEmpty()) {
            log.warn("No seats found for event id {}", eventId);
            throw new BusinessException("No seats found for this event.");
        }

        return seatMapper.toDtoList(seats);
    }
}
