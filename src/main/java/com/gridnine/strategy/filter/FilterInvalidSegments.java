package com.gridnine.strategy.filter;

import com.gridnine.model.Flight;

import java.util.List;

public class FilterInvalidSegments implements FlightFilterStrategy {

    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.segments().stream()
                        .noneMatch(segment -> segment.getArrivalTime().isBefore(segment.getDepartureTime())))
                .toList();
    }
}
