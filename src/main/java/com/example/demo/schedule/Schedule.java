package com.example.demo.schedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Schedule {

    String vetId;
    LocalDate startingDate;
    LocalDate endingDate;
    Integer startingHour;
    Integer endingHour;
}
