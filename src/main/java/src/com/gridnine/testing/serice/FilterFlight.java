package src.com.gridnine.testing.serice;


import src.com.gridnine.testing.model.Flight;
import src.com.gridnine.testing.model.Segment;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * класс для филтрации полётов
 */
public class FilterFlight {
    /**
     * метод возвращает полёты тоько с одним сегментом
     */
    public static List<Flight> singleSegmentFlight(List<Flight> flights) {

        List<Flight> flightsFilter = new ArrayList<>();

        for (int i = 0; i < flights.size(); i++) {
            if (flights.get(i).getSegments().size() == 1) {

                Flight flight = new Flight(flights.get(i).getSegments());

                flightsFilter.add(flight);

            }
        }

        return flightsFilter;
    }

    /**
     * метод возвращает полёты у которых более одного сегмента
     */
    public static List<Flight> multiSegmentFlight(List<Flight> flights) {
        List<Flight> flightFilter = new ArrayList<>();

        for (int i = 0; i < flights.size(); i++) {
            if (flights.get(i).getSegments().size() >= 2) {

                Flight flight = new Flight(flights.get(i).getSegments());

                flightFilter.add(flight);

            }
        }
        return flightFilter;

    }

    /**
     * метод возвращает только двух часовые полёты
     */
    public static List<Flight> flightWithTwoHourDuration(List<Flight> flights) {
        List<Flight> flightFilter = new ArrayList<>();
        List<Segment> segments = new ArrayList<>();

        for (int i = 0; i < flights.size(); i++) {

            segments = flights.get(i).getSegments();
            for (int j = 0; j < segments.size(); j++) {
                LocalDateTime departureDate = segments.get(j).getDepartureDate();
                LocalDateTime arrivalDate = segments.get(j).getArrivalDate();

                int transferTime = transferTime(departureDate, arrivalDate);

                if (transferTime == 2 && flights.get(i).getSegments().size() == 1) {
                    Segment segment = new Segment(departureDate, arrivalDate);

                    List<Segment> segmentsNew = List.of(segment);

                    Flight flight = new Flight(segmentsNew);

                    flightFilter.add(flight);


                }

            }

        }
        return flightFilter;
    }

    /**
     * метод возвращает полёты с перерывом до двух часов
     */
    public static List<Flight> breakOfTwoHours(List<Flight> flights) {
        List<Flight> flightFilter = new ArrayList<>();
        List<Segment> segments = new ArrayList<>();
        for (int i = 0; i < flights.size(); i++) {

            if (flights.get(i).getSegments().size() > 1) {
                segments = flights.get(i).getSegments();

                for (int j = 1; j < segments.size(); j++) {

                    LocalDateTime arrivalDate = segments.get(j - 1).getArrivalDate();

                    LocalDateTime departureDate = segments.get(j).getDepartureDate();

                    int breakTimeBetWeen = transferTime(arrivalDate, departureDate);
                    if (breakTimeBetWeen <= 2) {

                        Flight flight = new Flight(segments);

                        flightFilter.add(flight);

                    }
                }
            }

        }
        return flightFilter;
    }

    /**
     * метод возвращает полёты с перерывом более двух часов
     */
    public static List<Flight> breakMoreTwoHours(List<Flight> flights) {
        List<Flight> flightFilter = new ArrayList<>();
        List<Segment> segments = new ArrayList<>();
        for (int i = 0; i < flights.size(); i++) {

            if (flights.get(i).getSegments().size() > 1) {
                segments = flights.get(i).getSegments();

                for (int j = 1; j < segments.size(); j++) {

                    LocalDateTime arrivalDate = segments.get(j - 1).getArrivalDate();

                    LocalDateTime departureDate = segments.get(j).getDepartureDate();

                    int breakTimeBetWeen = transferTime(arrivalDate, departureDate);
                    if (breakTimeBetWeen  > 2) {

                        Flight flight = new Flight(segments);

                        flightFilter.add(flight);

                    }
                }
            }

        }
        return flightFilter;
    }

    /**
     * Вспомогательный метод для получения разницы во времени
     */
    private static int transferTime(LocalDateTime arrival, LocalDateTime departure) {
        return (int) ChronoUnit.HOURS.between(arrival, departure);
    }
}
