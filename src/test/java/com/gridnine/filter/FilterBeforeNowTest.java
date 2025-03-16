package com.gridnine.filter;


import com.gridnine.factory.FlightBuilder;
import com.gridnine.model.Flight;
import com.gridnine.strategy.filter.FilterBeforeNow;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FilterBeforeNowTest {

    @Test
    void testFilterBeforeNow() {
        LocalDateTime now = LocalDateTime.now();
        Flight flightSuccess = FlightBuilder.createFlight(
                now.plusDays(1),
                now.plusDays(1).plusHours(2));
        Flight flightFail = FlightBuilder.createFlight(
                now.minusDays(1),
                now.plusDays(1).plusHours(2));
        List<Flight> flights = List.of(flightSuccess, flightFail);

        FilterBeforeNow filter = new FilterBeforeNow();
        List<Flight> filteredFlights = filter.filter(flights);

        assertThat(filteredFlights).hasSize(1);
        assertThat(filteredFlights.get(0)).isEqualTo(flightSuccess);
    }
}