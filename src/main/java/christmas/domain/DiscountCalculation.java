package christmas.domain;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static christmas.domain.Constant.YEAR;
import static java.util.Calendar.MONTH;

public class DiscountCalculation {

    public static int calculateTotalDiscount(List<Order> orders, int day) {
        int totalDiscount = 0;
        Calendar calendar = new GregorianCalendar(YEAR, MONTH, day);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        boolean isWeekend = (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY);

        totalDiscount += calculateWeekdayDessertDiscount(orders, isWeekend);
        totalDiscount += calculateWeekendMainMenuDiscount(orders, isWeekend);
        if (day == Constant.CHRISTMAS_DAY || dayOfWeek == Calendar.SUNDAY) {
            totalDiscount += Constant.SPECIAL_DISCOUNT;
        }
        totalDiscount += calculateChampagnePresent(orders);

        return totalDiscount;
    }

    private static int calculateWeekdayDessertDiscount(List<Order> orders, boolean isWeekend) {
        if (isWeekend) {
            return 0;
        }
        return orders.stream()
                .filter(Order::isDessert)
                .mapToInt(order -> order.calculateDiscountAmount(Constant.WEEKDAY_DESSERT_DISCOUNT))
                .sum();
    }

    private static int calculateWeekendMainMenuDiscount(List<Order> orders, boolean isWeekend) {
        if (!isWeekend) {
            return 0;
        }
        return orders.stream()
                .filter(Order::isMainMenu)
                .mapToInt(order -> order.calculateDiscountAmount(Constant.WEEKEND_MAIN_DISCOUNT))
                .sum();
    }

    private static int calculateChampagnePresent(List<Order> orders) {
        int totalPrice = orders.stream()
                .mapToInt(Order::calculatePrice)
                .sum();

        if (totalPrice >= Constant.CHAMPAGNE_PRESENT_PRICE) {
            return Constant.CHAMPAGNE_PRICE;
        }
        return 0;
    }

}
