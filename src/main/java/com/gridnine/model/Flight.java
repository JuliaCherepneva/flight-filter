package com.gridnine.model;

import java.util.List;
import java.util.stream.Collectors;

public record Flight(List<Segment> segments) {

    @Override
    public String toString() {
        return segments.stream().map(Object::toString)
                .collect(Collectors.joining(" "));
    }
}
