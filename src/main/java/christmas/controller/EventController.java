package christmas.controller;

import christmas.domain.ChristmasDay;
import christmas.domain.Discount;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.view.InputView;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static christmas.domain.Constant.*;

public class EventController {
    private InputView inputView;
    private List<Order> order;

    public void startEvent() {
        int day = inputView.dayQuestion();
        List<Menu> orders = inputView.orderMenuNames();

        int totalDiscount = Discount.calculateTotalDiscount(orders, day);
        int totalOrderPrice = orders.stream().mapToInt(Menu::price).sum();
        int finalPrice = totalOrderPrice - totalDiscount;

    }

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
            christmasEvent(day);
        }
        if (!dayChecked && result) {
            weekEvent(day);
        }
        if (!dayChecked && !result){
            weekendEvent(day);
        }
    }

    private long weekendEvent(int day) {
        return order.stream()
                .filter(Order::isMainMenu)
                .count() * WEEKEND_MAIN_DISCOUNT;
    }

    private long weekEvent(int day) {
        return order.stream()
                .filter(Order::isDessert)
                .count() * WEEKDAY_DESSERT_DISCOUNT;
    }

    private void christmasEvent(int day) {
        calculateChristmasDDayDiscount(day);
    }

    private int calculateChristmasDDayDiscount(int day) {
        return CHRISTMAS_START_DISCOUNT + (day - 1) * DAILY_INCREMENT;
    }


}
