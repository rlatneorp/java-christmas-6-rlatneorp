package christmas.domain;

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

    public boolean isMainCourse() {
        return menu.isMainCourse();
    }

    public int calculateDiscount(boolean isWeekend, boolean isSpecialDay) {
        int discount = 0;
        if (isWeekend && isMainCourse()) {
            discount += WEEKEND_MAIN_COURSE_DISCOUNT * amount;
        } else if (!isWeekend && isDessert()) {
            discount += WEEKDAY_DESSERT_DISCOUNT * amount;
        }

        if (isSpecialDay) {
            discount += SPECIAL_DISCOUNT;
        }
        return discount;
    }

    public int calculatePrice() {
        return menu.getPrice() * amount;
    }
}
