package src;

import src.com.gridnine.testing.model.Flight;
import src.com.gridnine.testing.serice.FlightBuilder;
import src.com.gridnine.testing.serice.GetAndPrintFlights;

import java.util.List;


public class Main {
    public static void main(String[] args) {

        List<Flight> flights = FlightBuilder.createFlights();
        System.out.println(flights);

        GetAndPrintFlights.printSingleSegment(flights);

        GetAndPrintFlights.printMultiSegment(flights);

        GetAndPrintFlights.printFlightWithTwoHourDuration(flights);

        GetAndPrintFlights.printBreakOfTwoHours(flights);

        GetAndPrintFlights.printBreakMoreTwoHours(flights);


    }


}