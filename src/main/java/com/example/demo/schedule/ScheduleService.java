package com.example.demo.schedule;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class ScheduleService {

    private static final Schedule FIRST_SCHEDULE = new Schedule("1", LocalDate.of(2024, 11, 1),
            LocalDate.of(2024, 11, 30), 8, 16);

    private static final List<Slot> reserved = List.of(new Slot(LocalDateTime.of(2024, 11, 1, 9, 0), "1"));

    public List<Slot> getSlots() {
        //List<Slot> slots = new ArrayList<>();
        long days = ChronoUnit.DAYS.between(FIRST_SCHEDULE.startingDate, FIRST_SCHEDULE.endingDate);
        long hoursForDay = FIRST_SCHEDULE.endingHour - FIRST_SCHEDULE.startingHour;

        List<Slot> slots = IntStream.range(0, (int) days)  // Stream through the number of days
                .mapToObj(i -> FIRST_SCHEDULE.startingDate.plusDays(i))  // Get each date in range
                .filter(date -> date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY)  // Exclude weekends
                .flatMap(date ->
                        IntStream.range(0, (int) hoursForDay)  // Stream through the working hours of each day
                                .boxed()
                                .flatMap(hour -> Stream.of(0, 30)  // Generate 0 and 30 minute slots for each hour
                                        .map(minute -> new Slot(LocalDateTime.of(
                                                date.getYear(),
                                                date.getMonth(),
                                                date.getDayOfMonth(),
                                                FIRST_SCHEDULE.startingHour + hour,
                                                minute), "1"))
                                )
                )
                .collect(Collectors.toList());

        slots.removeAll(reserved);

        return slots;
    }
}
