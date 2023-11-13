package christmas.domain;

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
}