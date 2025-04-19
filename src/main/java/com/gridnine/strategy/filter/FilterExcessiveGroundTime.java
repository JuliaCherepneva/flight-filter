package com.gridnine.strategy.filter;

import com.gridnine.model.Flight;
import com.gridnine.model.Segment;

import java.time.Duration;
import java.util.List;


public class FilterExcessiveGroundTime implements FlightFilterStrategy{
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> {
                    List<Segment> segments = flight.segments();
                    long totalGroundTime = 0;
                    for (int i = 0; i < segments.size() - 1; i++) {
                        long groundTime = Duration.between(segments.get(i).getArrivalTime(), segments.get(i + 1).getDepartureTime()).toMinutes();
                        totalGroundTime += groundTime;
                    }
                    return totalGroundTime <= 120;  // менее 2 часов на земле
                })
                .toList();
    }
}
