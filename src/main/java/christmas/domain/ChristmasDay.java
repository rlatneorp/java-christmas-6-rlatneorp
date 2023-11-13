package christmas.domain;

import christmas.exception.DayRangeException;

import static christmas.domain.Constant.*;

public class ChristmasDay {

    public boolean dayCheck(int date) {
        validateDay(date);
        if (date <= CHRISTMAS_DAY) {
            return true;
        }
        return false;
    }

    public void validateDay(int date) {
        if (!(MIN_DAY <= date && date <= MAX_DAY)) {
            throw new DayRangeException();
        }
    }


}
