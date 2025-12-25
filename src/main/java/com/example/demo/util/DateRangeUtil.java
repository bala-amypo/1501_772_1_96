package com.example.demo.util;

import com.example.demo.exception.BadRequestException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DateRangeUtil {

    public static List<LocalDate> daysBetween(LocalDate start, LocalDate end) {

        if (start.isAfter(end)) {
            throw new BadRequestException("Start date after end date");
        }

        List<LocalDate> dates = new ArrayList<>();

        while (!start.isAfter(end)) {
            dates.add(start);
            start = start.plusDays(1);
        }

        return dates;
    }
}
