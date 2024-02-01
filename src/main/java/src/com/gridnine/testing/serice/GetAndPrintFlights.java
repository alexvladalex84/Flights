package src.com.gridnine.testing.serice;


import src.com.gridnine.testing.model.Flight;

import java.util.List;

/**
 * класс для печати полётов
 * содержит методы для вывода полётов с разными правилами(каждое правило имеет свой вывод)
 */
public class GetAndPrintFlights {

    public static void printSingleSegment(List<Flight> flights) {
        System.out.println("------------------------");
        List<Flight> flights1 = FilterFlight.singleSegmentFlight(flights);
        System.out.println(flights1);
    }

    public static void printMultiSegment(List<Flight> flights) {
        System.out.println("------------------------");
        List<Flight> flights2 = FilterFlight.multiSegmentFlight(flights);
        System.out.println(flights2);
    }

    public static void printFlightWithTwoHourDuration(List<Flight> flights) {
        System.out.println("------------------------");
        List<Flight> flights3 = FilterFlight.flightWithTwoHourDuration(flights);
        System.out.println(flights3);
    }

    public static void printBreakOfTwoHours(List<Flight> flights) {
        System.out.println("------------------------");
        List<Flight> flights3 = FilterFlight.breakOfTwoHours(flights);
        System.out.println(flights3);
    }
    public static void printBreakMoreTwoHours(List<Flight> flights) {
        System.out.println("------------------------");
        List<Flight> flights3 = FilterFlight.breakMoreTwoHours(flights);
        System.out.println(flights3);
    }
}
