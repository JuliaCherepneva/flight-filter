package com.gridnine.strategy;

import com.gridnine.factory.FlightBuilder;
import com.gridnine.strategy.filter.FilterBeforeNow;
import com.gridnine.strategy.filter.FilterExcessiveGroundTime;
import com.gridnine.strategy.filter.FilterInvalidSegments;
import com.gridnine.model.Flight;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FlightFilterContextTest {
    @Test
    void testApplyFilters() {
        LocalDateTime now = LocalDateTime.now();

        Flight flightSuccess = FlightBuilder.createFlight(
                LocalDateTime.now().plusHours(1),
                LocalDateTime.now().plusHours(3));
        Flight flightFail1 = FlightBuilder.createFlight(
                LocalDateTime.now().plusHours(3),
                LocalDateTime.now().plusHours(1));
        Flight flightFail2 = FlightBuilder.createFlight(
                now.plusHours(1),
                now.plusHours(3),
                now.plusHours(7),
                now.plusHours(9));
        Flight flightFail3 = FlightBuilder.createFlight(
                now.minusDays(1),
                now.plusDays(1).plusHours(2));


        List<Flight> flights = List.of(flightSuccess, flightFail1, flightFail2, flightFail3);

        FlightFilterContext filterContext = new FlightFilterContext();
        filterContext.addFilterStrategy(new FilterBeforeNow());
        filterContext.addFilterStrategy(new FilterExcessiveGroundTime());
        filterContext.addFilterStrategy(new FilterInvalidSegments());

        List<Flight> filteredFlights = filterContext.applyFilters(flights);

        assertThat(filteredFlights).hasSize(1);
        assertThat(filteredFlights.get(0)).isEqualTo(flightSuccess);
    }
}