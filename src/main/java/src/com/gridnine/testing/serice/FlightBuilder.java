package src.com.gridnine.testing.serice;


import src.com.gridnine.testing.model.Flight;
import src.com.gridnine.testing.model.Segment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Factory class to get sample list of flights.
 * Заводской класс, чтобы получить образец списка рейсов.
 */
public class FlightBuilder {

    /**
     * метод для создания полётов.
     * <p>
     * В задании нужно исключить данные правила:
     * 1.Вылет до текущего момента времени.
     * 2.Сегменты с датой прилёта раньше даты вылета.
     * 3. Перелеты, где общее время, проведённое на земле
     * , превышает два часа (время на земле — это интервал между
     * прилётом одного сегмента и вылетом следующего за ним).
     *
     * @return Исключённые правила закоментированы
     */
    public static List<Flight> createFlights() {
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        LocalDateTime fourDaysFromNow = LocalDateTime.now().plusDays(4);
        return Arrays.asList(
                //A normal flight with two hour duration Обычный односегментный полет продолжительностью два часа
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)),

                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)),

                //обычный односегментный рейс
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(1)),

                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(4)),

                //A normal multi segment flight Обычный многосегментный рейс c перерывом два часа и меньше
                createFlight(fourDaysFromNow, fourDaysFromNow.plusHours(3),
                        threeDaysFromNow.plusHours(4), threeDaysFromNow.plusHours(6)),

                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5)),

                //многосегментный рейс с перерывом более двух часов
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(7)));


//                Сегменты с датой прилёта раньше даты вылета
        //A flight departing in the past Рейс, вылетающий в прошлом
//                createFlight(threeDaysFromNow.minusDays(6), threeDaysFromNow),
//                Вылет до текущего момента времени.
        //A flight that departs before it arrives Рейс, который вылетает до прибытия
//                createFlight(threeDaysFromNow, threeDaysFromNow.minusHours(6)),

        //A flight with more than two hours ground time Рейс продолжительностью более двух часов наземного времени
//                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
//                        threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6)),


        //Another flight with more than two hours ground time Еще один рейс с наземным временем более двух часов.
//                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
//                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4),
//                        threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(7)));
    }

    /**
     * метод для создания одного полёта .
     * содаётся сам полёт из сегментов .
     *
     * @param dates
     * @return
     */
    private static Flight createFlight(final LocalDateTime... dates) {
        if ((dates.length % 2) != 0) {
            throw new IllegalArgumentException(
                    "you must pass an even number of dates");//Вы должны передать четное количество свиданий
        }
        List<Segment> segments = new ArrayList<>(dates.length / 2);
        for (int i = 0; i < (dates.length - 1); i += 2) {
            segments.add(new Segment(dates[i], dates[i + 1]));
        }
        return new Flight(segments);
    }
}

