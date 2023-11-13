package christmas.domain;

import static christmas.domain.Constant.*;

public enum Menu {
    양송이수프(APPETIZER, 6000),
    타파스(APPETIZER, 5500),
    시저샐러드(APPETIZER, 8000),
    티본스테이크(MAIN_MENU, 60000),
    바비큐립(MAIN_MENU, 54000),
    해산물파스타(MAIN_MENU, 35000),
    크리스마스파스타(MAIN_MENU, 25000),
    초코케이크(DISERT, 15000),
    아이스크림(DISERT, 5000),
    제로콜라(DRINK, 3000),
    레드와인(DRINK, 60000),
    샴페인(DRINK, 25000);

    private final int menuKind;
    private final int menuPrice;

    Menu(int menuKind, int menuPrice){
        this.menuKind = menuKind;
        this.menuPrice = menuPrice;
    }



}
