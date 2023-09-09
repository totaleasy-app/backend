package app.totaleasy.backend.core.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import lombok.NonNull;

public class DataTypeConverter {

    private DataTypeConverter() {

    }

    public static Integer toInteger(@NonNull String string) {
        return Integer.parseInt(string);
    }

    public static LocalDate toLocalDate(String date, String format) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
    }

    public static LocalTime toLocalTime(String time, String format) {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern(format));
    }
}
