package com.example.demo.schedule;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
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
