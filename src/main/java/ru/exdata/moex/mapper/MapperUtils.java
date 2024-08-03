package ru.exdata.moex.mapper;

import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class MapperUtils {

    private final static String FORMAT_LOCAL_DATE = "yyyy-MM-dd";
    private final static String FORMAT_DATE = "yyyy-MM-dd HH':'mm:ss";
    private final static String FORMAT_DATE_T = "yyyy-MM-dd'T'HH':'mm:ss";

    public static LocalDate mapFromObjectToLocalDate(Object date) {
        return LocalDate.from(
                new DateTimeFormatterBuilder()
                        .appendPattern(FORMAT_LOCAL_DATE)
//                        .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
//                        .parseDefaulting(ChronoField.NANO_OF_SECOND, 0)
                        .toFormatter()
//                        .withZone(ZoneId.of("UTC"))
                        .parse((String) date)
        );
    }

    public static LocalDateTime mapFromObjectToLocalDateTime(Object date) {
        try {
            return LocalDateTime.from(
                    new DateTimeFormatterBuilder()
                            .appendPattern(FORMAT_DATE)
                            .toFormatter(Locale.getDefault())
                            .parse((String) date));
        } catch (DateTimeException d) {
            try {
                return LocalDateTime.from(
                        new DateTimeFormatterBuilder()
                                .appendPattern(FORMAT_DATE_T)
                                .toFormatter()
                                .parse((String) date));
            } catch (DateTimeException d1) {
            }
        }
        throw new RuntimeException("Date format not found: " + date);
    }

    public static double mapFromObjectToDouble(Object o) {
        if (o == null) {
            return 0;
        } else if (o instanceof Integer) {
            return (double) (int) o;
        } else if (o instanceof Double) {
            return (double) o;
        } else if (o instanceof Long) {
            return (double) (long) o;
        } else {
            throw new RuntimeException("value type not defined: " + o.getClass());
        }
    }

    public static int mapFromObjectToInt(Object o) {
        if (o == null) {
            return 0;
        } else if (o instanceof Integer) {
            return (int) o;
        } else {
            throw new RuntimeException("value type not defined: " + o.getClass());
        }
    }

    public static long mapFromObjectToLong(Object o) {
        if (o == null) {
            return 0;
        } else if (o instanceof Integer) {
            return (long) (int) o;
        } else {
            throw new RuntimeException("value type not defined: " + o.getClass());
        }
    }

    public static String mapFromObjectToString(Object o) {
        if (o == null) {
            return "";
        } else if (o instanceof String) {
            return String.valueOf(o);
        } else {
            throw new RuntimeException("value type not defined: " + o.getClass());
        }
    }

    public static String mapFromInstantToString(Instant date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE);
        formatter.format(date);
        return date.toString();
    }

}
