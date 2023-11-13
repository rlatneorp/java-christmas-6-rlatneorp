package christmas.domain;

import static christmas.domain.Constant.*;

public class Discount {
    private Menu menu;
    private int amount;

    public Discount(Menu menu, int amount) {
        this.menu = menu;
        this.amount = amount;
    }

    public boolean isDessert() {
        return menu.isType(3);
    }

    public boolean isMainMenu() {
        return menu.isType(2);
    }

    public int calculateDiscount(boolean isWeekend, boolean isSpecialDay) {
        int discount = 0;
        if (isWeekend && isMainMenu()) {
            discount += WEEKEND_MAIN_DISCOUNT * amount;
        } else if (!isWeekend && isDessert()) {
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