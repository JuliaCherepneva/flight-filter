package com.gridnine.strategy.filter;

import com.gridnine.model.Flight;

import java.util.List;

public interface FlightFilterStrategy {
    List<Flight> filter(List<Flight> flights);
}
