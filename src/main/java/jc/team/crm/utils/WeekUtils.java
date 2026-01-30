package jc.team.crm.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;

public final class WeekUtils {

    private static final String[] DAY_NAMES = new String[]{"Пн", "Вт", "Ср", "Чт", "Пт"};

    public static String[] getDayNames() {
        return DAY_NAMES.clone();
    }

    public static LocalDate[] getWeekDates(LocalDate monday) {
        return new LocalDate[]{
            monday, 
            monday.plusDays(1), 
            monday.plusDays(2), 
            monday.plusDays(3), 
            monday.plusDays(4)
        };
    }

    public static LocalDate getMondayOfCurrentWeek() {
        LocalDate now = LocalDate.now();
        DayOfWeek dayOfWeek = now.getDayOfWeek();
        int daysToSubtract = dayOfWeek.getValue() - DayOfWeek.MONDAY.getValue();
        if (daysToSubtract < 0) {
            daysToSubtract += 7;
        }
        return now.minusDays(daysToSubtract);
    }
}
