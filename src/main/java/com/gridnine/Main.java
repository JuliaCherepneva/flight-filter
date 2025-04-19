package com.gridnine;

import com.gridnine.factory.FlightBuilder;
import com.gridnine.model.Flight;
import com.gridnine.strategy.filter.FilterBeforeNow;
import com.gridnine.strategy.filter.FilterExcessiveGroundTime;
import com.gridnine.strategy.filter.FilterInvalidSegments;
import com.gridnine.strategy.FlightFilterContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();

        FlightFilterContext context = new FlightFilterContext();

        // Фильтрация по времени вылета
        context.addFilterStrategy(new FilterBeforeNow());
        List<Flight> flightsAfterNow = context.applyFilters(flights);
        System.out.println("\nПерелеты, вылет которых после текущего времени:");
        flightsAfterNow.forEach(System.out::println);

        // Фильтрация по ошибкам времени (прилет раньше вылета)
        context = new FlightFilterContext(); // Сбрасываем контекст
        context.addFilterStrategy(new FilterInvalidSegments());
        List<Flight> flightsWithInvalidSegments = context.applyFilters(flights);
        System.out.println("\nПерелеты, где сегменты имеют ошибку времени (прилет раньше вылета):");
        flightsWithInvalidSegments.forEach(System.out::println);

        // Фильтрация по времени на земле
        context = new FlightFilterContext(); // Сбрасываем контекст
        context.addFilterStrategy(new FilterExcessiveGroundTime());
        List<Flight> flightsWithExcessiveGroundTime = context.applyFilters(flights);
        System.out.println("\nПерелеты, где время на земле более двух часов:");
        flightsWithExcessiveGroundTime.forEach(System.out::println);

        //Применение нескольких фильтров
        context = new FlightFilterContext();
        context.addFilterStrategy(new FilterBeforeNow());
        context.addFilterStrategy(new FilterInvalidSegments());
        context.addFilterStrategy(new FilterExcessiveGroundTime());
        List<Flight> filteredFlights = context.applyFilters(flights);
        System.out.println("\nПерелеты после применения нескольких фильтров:");
        filteredFlights.forEach(System.out::println);
    }
}
