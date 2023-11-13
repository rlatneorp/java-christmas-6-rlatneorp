package christmas.controller;

import christmas.domain.ChristmasDayEvent;
import christmas.view.InputView;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static christmas.domain.Constant.YEAR;

public class Event {
    private InputView inputView;

    public void christmasEventCheck() {
        int day = inputView.dayQuestion();
        ChristmasDayEvent christmasDayEvent = new ChristmasDayEvent();
        boolean dayChecked = christmasDayEvent.dayCheck(day);
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
