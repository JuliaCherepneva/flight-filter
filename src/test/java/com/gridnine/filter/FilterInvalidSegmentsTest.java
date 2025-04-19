package com.gridnine.filter;


import com.gridnine.factory.FlightBuilder;
import com.gridnine.model.Flight;
import com.gridnine.strategy.filter.FilterInvalidSegments;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FilterInvalidSegmentsTest {
    @Test
    void testFilterInvalidSegments() {
        LocalDateTime now = LocalDateTime.now();
        Flight flightSuccess  = FlightBuilder.createFlight(
                now.plusHours(1),
                now.plusHours(3));

        Flight flightFail = FlightBuilder.createFlight(
                now.plusHours(3),
                now.minusHours(1));

        List<Flight> flights = List.of(flightSuccess, flightFail);
        FilterInvalidSegments filter = new FilterInvalidSegments();
        List<Flight> filteredFlights = filter.filter(flights);

        assertThat(filteredFlights).hasSize(1);
        assertThat(filteredFlights.get(0)).isEqualTo(flightSuccess);
    }

}