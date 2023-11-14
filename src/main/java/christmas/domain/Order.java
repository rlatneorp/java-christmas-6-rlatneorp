package christmas.domain;

public class Order {
    private final Menu menu;
    private final int amount;

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

    public int calculateDiscountAmount(int discount) {
        return this.amount * discount;
    }

    public int calculatePrice() {
        return this.menu.calculateTotalPrice(this.amount);
    }

}
