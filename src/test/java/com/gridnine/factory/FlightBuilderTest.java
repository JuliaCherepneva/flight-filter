package com.gridnine.factory;

import com.gridnine.model.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class FlightBuilderTest {
    private List<Flight> flights;
    private final LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);

    @BeforeEach
    void setUp() {
        flights = FlightBuilder.createFlights();
    }

    @Test
    void testCreateFlights() {
        assertThat(flights).isNotNull().hasSize(6);
    }

    @Test
    void testFirstFlight() {
        Flight firstFlight = flights.get(0);
        assertThat(firstFlight.segments()).hasSize(1);
        assertThat(firstFlight.segments().get(0).getDepartureTime())
                .isEqualToIgnoringNanos(threeDaysFromNow);
        assertThat(firstFlight.segments().get(0).getArrivalTime())
                .isEqualToIgnoringNanos(threeDaysFromNow.plusHours(2));
    }

    @Test
    void testSecondFlight() {
        Flight secondFlight = flights.get(1);
        assertThat(secondFlight.segments()).hasSize(2);
        assertThat(secondFlight.segments().get(0).getDepartureTime())
                .isEqualToIgnoringNanos(threeDaysFromNow);
        assertThat(secondFlight.segments().get(0).getArrivalTime())
                .isEqualToIgnoringNanos(threeDaysFromNow.plusHours(2));
        assertThat(secondFlight.segments().get(1).getDepartureTime())
                .isEqualToIgnoringNanos(threeDaysFromNow.plusHours(3));
        assertThat(secondFlight.segments().get(1).getArrivalTime())
                .isEqualToIgnoringNanos(threeDaysFromNow.plusHours(5));
    }

    @Test
    void testThirdFlight() {
        Flight thirdFlight = flights.get(2);
        assertThat(thirdFlight.segments()).hasSize(1);
        assertThat(thirdFlight.segments().get(0).getDepartureTime())
                .isEqualToIgnoringNanos(threeDaysFromNow.minusDays(6));
        assertThat(thirdFlight.segments().get(0).getArrivalTime())
                .isEqualToIgnoringNanos(threeDaysFromNow);
    }

    @Test
    void testFourthFlight() {
        Flight fourthFlight = flights.get(3);

        assertThat(fourthFlight.segments()).hasSize(1);
        assertThat(fourthFlight.segments().get(0).getDepartureTime())
                .isEqualToIgnoringNanos(threeDaysFromNow);
        assertThat(fourthFlight.segments().get(0).getArrivalTime())
                .isEqualToIgnoringNanos(threeDaysFromNow.minusHours(6));
    }

    @Test
    void testFifthFlight() {
        Flight fifthFlight = flights.get(4);

        assertThat(fifthFlight.segments()).hasSize(2);

        assertThat(fifthFlight.segments().get(0).getDepartureTime())
                .isEqualToIgnoringNanos(threeDaysFromNow);

        assertThat(fifthFlight.segments().get(0).getArrivalTime())
                .isEqualToIgnoringNanos(threeDaysFromNow.plusHours(2));

        assertThat(fifthFlight.segments().get(1).getDepartureTime())
                .isEqualToIgnoringNanos(threeDaysFromNow.plusHours(5));

        assertThat(fifthFlight.segments().get(1).getArrivalTime())
                .isEqualToIgnoringNanos(threeDaysFromNow.plusHours(6));
    }

    @Test
    void testSixthFlight() {
        Flight sixthFlight = flights.get(5);

        assertThat(sixthFlight.segments()).hasSize(3);

        assertThat(sixthFlight.segments().get(0).getDepartureTime())
                .isEqualToIgnoringNanos(threeDaysFromNow);

        assertThat(sixthFlight.segments().get(0).getArrivalTime())
                .isEqualToIgnoringNanos(threeDaysFromNow.plusHours(2));

        assertThat(sixthFlight.segments().get(1).getDepartureTime())
                .isEqualToIgnoringNanos(threeDaysFromNow.plusHours(3));

        assertThat(sixthFlight.segments().get(1).getArrivalTime())
                .isEqualToIgnoringNanos(threeDaysFromNow.plusHours(4));

        assertThat(sixthFlight.segments().get(2).getDepartureTime())
                .isEqualToIgnoringNanos(threeDaysFromNow.plusHours(6));

        assertThat(sixthFlight.segments().get(2).getArrivalTime())
                .isEqualToIgnoringNanos(threeDaysFromNow.plusHours(7));
    }

    @Test
    void testFlightWithOddDates() {
        assertThatThrownBy(() -> FlightBuilder.createFlight(
                    LocalDateTime.now(),
                    LocalDateTime.now().plusHours(2),
                    LocalDateTime.now().plusHours(3)
        ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("you must pass an even number of dates");
    }
}