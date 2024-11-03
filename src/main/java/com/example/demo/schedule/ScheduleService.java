package com.example.demo.schedule;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {

    private static final Schedule FIRST_SCHEDULE = new Schedule("1", LocalDate.of(2024, 11, 1),
            LocalDate.of(2024, 11, 30), 8, 16);

    private static final Slot reserved = new Slot(LocalDateTime.of(2024, 11, 1, 9, 0), "1");

    public List<Slot> getSlots() {
        List<Slot> slots = new ArrayList<>();
        long days = ChronoUnit.DAYS.between(FIRST_SCHEDULE.startingDate, FIRST_SCHEDULE.endingDate);
        long hoursForDay = FIRST_SCHEDULE.endingHour - FIRST_SCHEDULE.startingHour;

        for (int i = 0; i < days; i++) {
            for (int hour = 0; hour < hoursForDay; hour++) {
                int finalI = i;
                int finalHour = hour;
                if (!FIRST_SCHEDULE.startingDate.getDayOfWeek().plus(i).equals(DayOfWeek.SATURDAY) && !FIRST_SCHEDULE.startingDate.getDayOfWeek().plus(i).equals(DayOfWeek.SUNDAY)) {
                    List.of(0, 30).forEach(countedMinute -> slots.add(new Slot(
                            LocalDateTime.of(FIRST_SCHEDULE.startingDate.getYear(), FIRST_SCHEDULE.startingDate.getMonth(),
                                    FIRST_SCHEDULE.startingDate.getDayOfMonth() + finalI, FIRST_SCHEDULE.startingHour + finalHour, countedMinute),
                            "1")));
                }
            }
        }

        slots.removeAll(List.of(reserved));

        return slots;
    }
}