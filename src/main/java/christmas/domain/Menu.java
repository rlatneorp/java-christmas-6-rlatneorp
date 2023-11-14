package christmas.domain;

/*
숫자별로 메뉴 카테고리를 정함
에피타이져 = 1, 메인메뉴 = 2, 디저트 = 3, 음료 = 4
 */

import java.util.Arrays;

import static christmas.domain.Constant.*;

public enum Menu {
    양송이수프(1, 6000),
    타파스(1, 5500),
    시저샐러드(1, 8000),
    티본스테이크(2, 60000),
    바비큐립(2, 54000),
    해산물파스타(2, 35000),
    크리스마스파스타(2, 25000),
    초코케이크(3, 15000),
    아이스크림(3, 5000),
    제로콜라(4, 3000),
    레드와인(4, 60000),
    샴페인(4, 25000);

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

    public String menuInfo() {
        return this.name();
    }

}
