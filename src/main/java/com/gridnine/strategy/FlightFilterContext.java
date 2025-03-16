package com.gridnine.strategy;

import com.gridnine.strategy.filter.FlightFilterStrategy;
import com.gridnine.model.Flight;

import java.util.ArrayList;
import java.util.List;

public class FlightFilterContext {
    private final List<FlightFilterStrategy> strategies = new ArrayList<>();

    public void addFilterStrategy(FlightFilterStrategy strategy) {
        strategies.add(strategy);
    }

    public List<Flight> applyFilters(List<Flight> flights) {
        for (FlightFilterStrategy strategy : strategies) {
            flights = strategy.filter(flights);
        }
        return flights;
    }
}
