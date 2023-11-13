package christmas.domain;

import christmas.exception.DayRangeException;

import static christmas.domain.Constant.*;

public class ChristmasDayEvent {

    public boolean validateChristmasDays(int date) {
        if (!(MIN_DAY <= date && date <= CHRISTMAS_DAY)) {
            return false;
        }
        return true;
    }


}
