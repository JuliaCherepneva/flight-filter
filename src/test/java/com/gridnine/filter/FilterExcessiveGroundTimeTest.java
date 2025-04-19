package com.gridnine.filter;

import com.gridnine.factory.FlightBuilder;
import com.gridnine.model.Flight;
import com.gridnine.strategy.filter.FilterExcessiveGroundTime;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FilterExcessiveGroundTimeTest {
    @Test
    void testFilterExcessiveGroundTime() {
        LocalDateTime now = LocalDateTime.now();
        Flight flightSuccess  = FlightBuilder.createFlight(
                now.plusHours(1),
                now.plusHours(3),
                now.plusHours(4),
                now.plusHours(6));
        Flight flightFail = FlightBuilder.createFlight(
                now.plusHours(1),
                now.plusHours(3),
                now.plusHours(7),
                now.plusHours(9));

        List<Flight> flights = List.of(flightSuccess, flightFail);
        FilterExcessiveGroundTime filter = new FilterExcessiveGroundTime();
        List<Flight> filteredFlights = filter.filter(flights);

        assertThat(filteredFlights).hasSize(1);
        assertThat(filteredFlights.get(0)).isEqualTo(flightSuccess);
    }
}