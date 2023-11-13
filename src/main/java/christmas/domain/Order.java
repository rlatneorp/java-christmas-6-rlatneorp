package christmas.domain;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static christmas.domain.Constant.*;

public class Order {
    private Menu menu;
    private int amount;

    public Order(Menu menu, int amount) {
        this.menu = menu;
        this.amount = amount;
    }

    public boolean isDessert() {
        return menu.isDessert();
    }

    public boolean isMainMenu() {
        return menu.isMainMenu();
    }

    public int calculateDiscount(boolean isWeekend, boolean isSpecialDay) {
        int discount = 0;
        if (isWeekend && menu.isMainMenu()) {
            discount += WEEKEND_MAIN_DISCOUNT * amount;
        }
        if (!isWeekend && menu.isDessert()) {
            discount += WEEKDAY_DESSERT_DISCOUNT * amount;
        }
        if (isSpecialDay) {
            discount += SPECIAL_DISCOUNT;
        }
        return discount;
    }

    public int calculatePrice() {
        return menu.price() * amount;
    }

    public boolean presentChampagne(List<Order> orders) {
        int totalPrice = orders.stream()
                .mapToInt(order -> order.menu.price() * order.amount)
                .sum();

        return totalPrice >= CHAMPAGNE_PRESENT_PRICE;
    }

    public int calculateDiscountSundayChristmas(int day, List<Order> orders) {
        Calendar calendar = new GregorianCalendar(YEAR, Calendar.DECEMBER, day);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int totalDiscount = 0;
        if (dayOfWeek == Calendar.SUNDAY || day == CHRISTMAS_DAY) {
            int discountPerDay = CHRISTMAS_START_DISCOUNT + (day - 1) * DAILY_INCREMENT;
            for (Order order : orders) {
                totalDiscount += discountPerDay * order.amount;
            }
        }
        if (dayOfWeek == Calendar.SUNDAY && day != CHRISTMAS_DAY) {
            totalDiscount += SPECIAL_DISCOUNT;
        }
        return totalDiscount;
    }
}

}