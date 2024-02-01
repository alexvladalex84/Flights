package src.com.gridnine.testing.model;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Bean that represents a flight.
 * Боб, олицетворяющий полет.
 */
public class Flight {

    private final List<Segment> segments;

    public Flight(final List<Segment> segments) {
        this.segments = segments;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    @Override
    public String toString() {
        return segments.stream().map(Object::toString)
                .collect(Collectors.joining(" "));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(segments, flight.segments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(segments);
    }
}

