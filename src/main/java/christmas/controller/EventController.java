package christmas.controller;

import christmas.domain.ChristmasDay;
import christmas.view.InputView;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static christmas.domain.Constant.YEAR;

public class EventController {
    private InputView inputView;

    public void christmasDayCheck() {
        int day = inputView.dayQuestion();
        ChristmasDay christmasDay = new ChristmasDay();
        boolean dayChecked = christmasDay.dayCheck(day);
        dayChecked(dayChecked, day);
    }

    public static boolean isWeekday(int day) {
        Calendar calendar = new GregorianCalendar(YEAR, Calendar.DECEMBER, day);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        return dayOfWeek >= Calendar.SUNDAY && dayOfWeek <= Calendar.THURSDAY;
    }

    public void dayChecked(boolean dayChecked, int day) {
        boolean result = isWeekday(day);
        if (dayChecked) {
            christmasEvent();
        }
        if (!dayChecked && result) {
            weekEvent();
        }    
        if (!dayChecked && !result){
            weekendEvent();
        }
    }

    private void weekendEvent() {

    }

    private void weekEvent() {
    }

    private void christmasEvent() {
    }


}
