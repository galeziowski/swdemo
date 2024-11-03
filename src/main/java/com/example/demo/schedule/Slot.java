package com.example.demo.schedule;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Slot {

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    LocalDateTime startDateTime;
    String vetId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Slot slot = (Slot) o;
        return Objects.equals(startDateTime, slot.startDateTime) && Objects.equals(vetId, slot.vetId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDateTime, vetId);
    }
}
