package christmas.domain;

import christmas.exception.DayRangeException;

import static christmas.domain.Constant.MAX_DAY;
import static christmas.domain.Constant.MIN_DAY;

public class DecemberEvent {

    public void validateDecemberDays(int date) {
        if (!(MIN_DAY <= date <= MAX_DAY)) {
            throw new DayRangeException();
        }
    }




}