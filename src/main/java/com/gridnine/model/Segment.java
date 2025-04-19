package com.gridnine.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Segment {
    private final LocalDateTime departureDate;
    private final LocalDateTime arrivalDate;

    public Segment(final LocalDateTime departureDate, final LocalDateTime arrivalDate) {
        this.departureDate = Objects.requireNonNull(departureDate);
        this.arrivalDate = Objects.requireNonNull(arrivalDate);
    }

    public LocalDateTime getDepartureTime() {
        return departureDate;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return '[' + departureDate.format(fmt) + '|' + arrivalDate.format(fmt) + ']';
    }
}
