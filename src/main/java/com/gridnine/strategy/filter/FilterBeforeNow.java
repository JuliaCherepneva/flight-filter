package com.gridnine.strategy.filter;

import com.gridnine.model.Flight;

import java.time.LocalDateTime;
import java.util.List;

public class FilterBeforeNow implements FlightFilterStrategy {

    @Override
    public List<Flight> filter(List<Flight> flights) {
        LocalDateTime now = LocalDateTime.now();
        return flights.stream()
                .filter(flight -> flight.segments().stream()
                        .noneMatch(segment -> segment.getDepartureTime().isBefore(now)))
                .toList();
    }
}

