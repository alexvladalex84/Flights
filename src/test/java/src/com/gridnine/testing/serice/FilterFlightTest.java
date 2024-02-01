package src.com.gridnine.testing.serice;

import org.junit.jupiter.api.Test;
import src.com.gridnine.testing.model.Flight;
import src.com.gridnine.testing.model.Segment;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FilterFlightTest {
    //тестовые данные
    private  LocalDateTime departureDate1 = LocalDateTime.now(); //Дата отбытия
    private LocalDateTime arrivalDate1=LocalDateTime.now().plusHours(2); //Дата прибытия

    private  LocalDateTime departureDate2 = LocalDateTime.now().plusHours(4); //Дата отбытия
    private LocalDateTime arrivalDate2=LocalDateTime.now().plusHours(6); //Дата прибытия

    private  LocalDateTime departureDate3 = LocalDateTime.now().plusHours(5); //Дата отбытия
    private LocalDateTime arrivalDate3=LocalDateTime.now().plusHours(7); //Дата прибытия

    Segment segment1 = new Segment(departureDate1,arrivalDate1);
    Segment segment2 = new Segment(departureDate2,arrivalDate2);
    Segment segment3 = new Segment(departureDate3,arrivalDate3);


    List<Segment> segmentsTest1 = List.of(segment1);
    Flight flight1 = new Flight(segmentsTest1);

    List<Segment> segmentsTest2 = List.of(segment1,segment2);
    Flight flight2 = new Flight(segmentsTest2);

    List<Segment> segmentsTest3 = List.of(segment1,segment3);
    Flight flight3 = new Flight(segmentsTest3);

    //общий список разных правил
    List<Flight> testListFlight = List.of(flight1,flight2,flight3,flight1,flight2);
    //полёты с одним сегментом
    List<Flight> testListFlight1 = List.of(flight1,flight1);
    //полёты с более чем одним сегментом
    List<Flight> testListFlight2 = List.of(flight2,flight3,flight2);
    //полёты с перерывом менее 2 часов
    List<Flight> testListFlight3 = List.of(flight2,flight2);
    //полёты с перерывом более 2 чачов
    List<Flight> testListFlight4 = List.of(flight3);





    @Test
    void singleSegmentFlight() {
       int expected = FilterFlight.singleSegmentFlight(testListFlight).get(0).getSegments().size();
        int actual = 1;
        assertEquals(expected, actual);

        List<Flight> expected2 = FilterFlight.singleSegmentFlight(testListFlight);
        List<Flight> actual2 = testListFlight1 ;

        assertEquals(expected2,actual2);

    }

    @Test
    void multiSegmentFlight() {
        int expected1 = FilterFlight.multiSegmentFlight(testListFlight).get(1).getSegments().size();
        int actual1 = 2;
        assertEquals(expected1, actual1);

        List<Flight> expected2 = FilterFlight.multiSegmentFlight(testListFlight);
        List<Flight> actual2 = testListFlight2 ;

        assertEquals(expected2,actual2);

    }

    @Test
    void flightWithTwoHourDuration() {

        List<Flight> expected = FilterFlight.flightWithTwoHourDuration(testListFlight);

        List<Flight> actual = testListFlight1 ;

        assertEquals(expected,actual);
    }

    @Test
    void breakOfTwoHours() {
        List<Flight> expected = FilterFlight.breakOfTwoHours(testListFlight);

        List<Flight> actual = testListFlight3 ;

        assertEquals(expected,actual);
    }
    @Test
    void breakMoreTwoHours() {
        List<Flight> expected = FilterFlight.breakMoreTwoHours(testListFlight);

        List<Flight> actual = testListFlight4 ;

        assertEquals(expected,actual);
    }
}