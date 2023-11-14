package christmas.domain;

/*
숫자별로 메뉴 카테고리를 정함
에피타이져 = 1, 메인메뉴 = 2, 디저트 = 3, 음료 = 4
 */

import java.util.Arrays;

import static christmas.domain.Constant.*;

public enum Menu {
    MUSHROOM_SOUP(1, 6000),
    TAPAS(1, 5500),
    SALAD(1, 8000),
    T_STEAK(2, 60000),
    RIB(2, 54000),
    SEAFOOD_PASTA(2, 35000),
    CHRISTMAS_PASTA(2, 25000),
    CHOCO_CAKE(3, 15000),
    ICE_CREAM(3, 5000),
    ZERO_COKE(4, 3000),
    RED_WINE(4, 60000),
    CHAMPAGNE(4, 25000);

    private final int menuKind;
    private final int menuPrice;

    Menu(int menuKind, int menuPrice){
        this.menuKind = menuKind;
        this.menuPrice = menuPrice;
    }

    public boolean isDessert() {
        return this.menuKind == DESERT;
    }

    public boolean isMainMenu() {
        return this.menuKind == MAIN_MENU;
    }

    public boolean isDrink() {
        return this.menuKind == DRINK;
    }

    public int calculateTotalPrice(int amount) {
        return this.menuPrice * amount;
    }

    public static Menu menuName(String name) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.name().equalsIgnoreCase(name.replace(" ", "_")))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
