package tickets;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.EnumSet;
import java.util.Set;

public enum Season {
    SPRING(EnumSet.of(Month.MARCH, Month.APRIL, Month.MAY)),
    SUMMER(EnumSet.of(Month.JUNE, Month.JULY, Month.AUGUST)),
    AUTUMN(EnumSet.of(Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER)),
    WINTER(EnumSet.of(Month.DECEMBER, Month.JANUARY, Month.FEBRUARY));

    private transient Set<Month> months;
    
    Season (Set<Month> months) {
        this.months = EnumSet.copyOf(months);
    }

    public boolean isDateWithin(LocalDateTime date) {
        return this.months.contains(date.getMonth());
    }
}
