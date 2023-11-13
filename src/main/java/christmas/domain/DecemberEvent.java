package christmas.domain;

import christmas.exception.DayRangeException;

import static christmas.domain.Constant.MAX_DAY;
import static christmas.domain.Constant.MIN_DAY;

public class DecemberEvent {

    public boolean validateDecemberDays(int date) {
        if (!(MIN_DAY <= date && date <= MAX_DAY)) {
            throw new DayRangeException();
        }
        return true;
    }




}
