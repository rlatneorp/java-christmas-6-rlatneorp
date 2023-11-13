package christmas.domain;

import christmas.controller.EventController;

import java.util.List;

import static christmas.domain.Constant.WEEKDAY_DESSERT_DISCOUNT;
import static christmas.domain.Constant.WEEKEND_MAIN_DISCOUNT;

public class Discount {

    public static int calculateTotalDiscount(List<Menu> orders, int day) {
        int totalDiscount = 0;

        for (Menu order : orders) {
            if (EventController.isWeekday(day) && order.isMainMenu()) {
                totalDiscount += WEEKEND_MAIN_DISCOUNT;
            }
            if (EventController.isWeekday(day) && order.isDessert()) {
                totalDiscount += WEEKDAY_DESSERT_DISCOUNT;
            }

        }



        return totalDiscount;
    }
}
