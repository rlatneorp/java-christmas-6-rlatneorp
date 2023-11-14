package christmas;

import christmas.controller.EventController;
import christmas.domain.Badge;
import christmas.domain.DiscountCalculation;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.exception.DayRangeException;
import christmas.view.InputView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static christmas.domain.Constant.*;
import static org.junit.jupiter.api.Assertions.*;

class EventTest {
    private List<Order> orders;
    private InputView inputView;

    @BeforeEach
    void setUp() {
        orders = new ArrayList<>();
        inputView = new InputView();
    }

    @Test
    @DisplayName("배지가 올바르게 반환되는지 테스트")
    void testGiveBadge() {
        assertEquals("산타", Badge.giveBadge(21000));
        assertEquals("트리", Badge.giveBadge(11000));
        assertEquals("별", Badge.giveBadge(5000));
        assertEquals("없음", Badge.giveBadge(1000));
    }

    @Test
    @DisplayName("특정 날짜가 주말인지 확인")
    void testWeekdayCheck() {
        assertTrue(EventController.weekdayCheck(30));
        assertFalse(EventController.weekdayCheck(31));
    }

    @Test
    @DisplayName("특정 날짜가 일요일인지 확인")
    void testIsSunday() {
        assertTrue(EventController.isSunday(24));
        assertFalse(EventController.isSunday(25));
    }

    @Test
    @DisplayName("주말 메인메뉴 할인 금액을 계산.")
    void testWeekendMainMenuDiscount() {
        boolean isWeekend = true;

        orders.add(new Order(Menu.초코케이크, 1));
        orders.add(new Order(Menu.티본스테이크, 3));

        int discount = DiscountCalculation.weekendMainMenuDiscount(orders, isWeekend);

        int expectedDiscount = WEEKEND_MAIN_DISCOUNT * 3;
        assertEquals(expectedDiscount, discount);
    }

    @Test
    @DisplayName("평일 디저트 할인 금액을 계산")
    void testWeekdayDessertDiscount() {
        boolean isWeekend = false;

        orders.add(new Order(Menu.초코케이크, 1));
        orders.add(new Order(Menu.티본스테이크, 2));

        int discount = DiscountCalculation.weekdayDessertDiscount(orders, isWeekend);

        int expectedDiscount = WEEKDAY_DESSERT_DISCOUNT;
        assertEquals(expectedDiscount, discount);
    }


    @Test
    @DisplayName("총 할인 금액을 계산")
    void testTotalDiscount() {
        int day = CHRISTMAS_DAY;

        orders.add(new Order(Menu.초코케이크, 2));
        orders.add(new Order(Menu.티본스테이크, 3));

        int totalDiscount = DiscountCalculation.totalDiscount(orders, day) - CHAMPAGNE_PRICE;

        int expectedDiscount = WEEKDAY_DESSERT_DISCOUNT * 2 + SPECIAL_DISCOUNT;
        assertEquals(expectedDiscount, totalDiscount);
    }

    @Test
    @DisplayName("샴페인 선물 할인")
    void testChampagnePresent() {
        orders.add(new Order(Menu.바비큐립, 3));

        int champagneMoney = DiscountCalculation.totalDiscount(orders, CHRISTMAS_DAY)
                - SPECIAL_DISCOUNT;

        assertEquals(25000, champagneMoney);
    }

    @Test
    @DisplayName("높은 총 가격에서 샴페인 선물 할인을 테스트")
    void testChampagnePresentWithHighTotalPrice() {
        orders.add(new Order(Menu.티본스테이크, 5));

        int totalDiscount = DiscountCalculation.totalDiscount(orders, CHRISTMAS_DAY)
                - SPECIAL_DISCOUNT;

        assertEquals(CHAMPAGNE_PRICE, totalDiscount);
    }

    @Test
    @DisplayName("디저트가 맞는지 아닌지 확인")
    public void testIsDessert() {
        Order dessertOrder = new Order(Menu.아이스크림, 2);
        assertTrue(dessertOrder.isDessert());

        Order nonDessertOrder = new Order(Menu.바비큐립, 1);
        assertFalse(nonDessertOrder.isDessert());
    }

    @Test
    @DisplayName("메인메뉴가 맞는지 아닌지 확인")
    public void testIsMainMenu() {
        Order mainMenuOrder = new Order(Menu.바비큐립, 1);
        assertTrue(mainMenuOrder.isMainMenu());

        Order nonMainMenuOrder = new Order(Menu.아이스크림, 2);
        assertFalse(nonMainMenuOrder.isMainMenu());
    }

    @Test
    @DisplayName("주문 금액이 맞는지 확인")
    public void testCalculatePrice() {
        Order order = new Order(Menu.아이스크림, 2);
        int totalPrice = order.calculatePrice();
        assertEquals(10000, totalPrice);
    }

    @Test
    @DisplayName("주말에 바비큐립 주문금액에 대한 할인 금액")
    public void weekendRibDiscount() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(Menu.바비큐립, 1));
        int discount = DiscountCalculation.weekendMainMenuDiscount(orders, true);
        assertEquals(2023, discount);
    }

    @Test
    @DisplayName("입력한 단어가 숫자가 맞는지 확인")
    public void testValidateInputIsNumberFormat() {
        assertDoesNotThrow(() -> inputView.validateInputIsNumberFormat("25"));
        assertThrows(DayRangeException.class, () -> inputView.validateInputIsNumberFormat("abcde"));
        assertThrows(DayRangeException.class, () -> inputView.validateInputIsNumberFormat(""));
    }

    @Test
    @DisplayName("숫자를 입력한 게 맞는지 아닌지 확인")
    public void testIsNumberWord() {
        assertTrue(inputView.isNumberWord("12345"));
        assertFalse(inputView.isNumberWord("abcde"));
        assertFalse(inputView.isNumberWord(""));
    }

    @Test
    @DisplayName("총 메뉴 종류의 갯수가 맞는지 확인")
    public void testMenuNames() {
        assertEquals(2, inputView.menuNames("아이스크림-2,샴페인-1").size());
        assertEquals(1, inputView.menuNames("바비큐립-1").size());
    }


}

