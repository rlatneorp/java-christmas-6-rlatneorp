package christmas.view;

import christmas.controller.EventController;
import christmas.domain.Constant;
import christmas.domain.DiscountCalculation;
import christmas.domain.Order;

import java.util.List;

import static christmas.domain.Constant.*;

public class OutputView {
    private static final String DECEMBER_MESSAGE = "12월 ";
    private static final String DECEMBER_PREVIEW_MESSAGE = "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";
    private static final String ORDER_MENU = "<주문 메뉴>";
    private static final String BEFORE_DISCOUNT_MONEY = "\n<할인 전 총주문 금액>";
    private static final String PRESENT = "\n<증정 메뉴>";
    private static final String DISCOUNT_LIST = "\n<혜택 내역>";
    private static final String TOTAL_DISCOUNT = "\n<총혜택 금액>";
    private static final String AFTER_DISCOUNT_MONEY = "\n<할인 후 예상 결제 금액>";
    private static final String DECEMBER_BADGE = "\n<12월 이벤트 배지>";
    private static final String WON = "원";
    private static final String WEEK_SALE = "평일 할인: -";
    private static final String WEEKEND_SALE = "주말 할인: -";
    private static final String CHRISTMAS_SALE = "크리스마스 디데이 할인: -";
    private static final String GIFT_SALE = "증정 이벤트: -25,000원";
    private static final String SPECIAL_SALE = "특별 할인: -";
    private static final String ONE_CHAMPAGNE = CHAMPAGNE + " 1개";
    private static final String X = "없음";
    private static final String BAR = "-";

    public void displayOrder(List<Order> orders, int day, int totalOrderAmount, int finalPaymentAmount, String giveBadge) {
        System.out.println(DECEMBER_MESSAGE + day + DECEMBER_PREVIEW_MESSAGE);
        orderedMenu(orders);
        totalOrderAmount(totalOrderAmount);
        giftMenu(totalOrderAmount);
        int totalBenefitAmount = benefits(day, orders, totalOrderAmount);
        totalBenefitsAmount(totalBenefitAmount);
        finalPaymentAmount(finalPaymentAmount);
        givingBadge(giveBadge);
    }

    private int benefits(int day, List<Order> orders, int totalOrderAmount) {
        System.out.println(DISCOUNT_LIST);
        int totalBenefits = 0;
        totalBenefits += weekdayDiscount(day, orders);
        totalBenefits += weekendMainDiscount(day, orders);
        totalBenefits += specialDiscount(day);
        totalBenefits += christmasDDayDiscount(day);
        totalBenefits += giftEvent(totalOrderAmount);

        if (totalBenefits == 0) {
            System.out.println(X);
        }
        return totalBenefits;
    }

    private void orderedMenu(List<Order> orders) {
        System.out.println(ORDER_MENU);
        for (Order order : orders) {
            order.orderInfo();
        }
    }

    private void totalOrderAmount(int totalOrderAmount) {
        System.out.println(BEFORE_DISCOUNT_MONEY);
        System.out.println(price(totalOrderAmount) + WON);
    }

    private void giftMenu(int totalOrderAmount) {
        System.out.println(PRESENT);
        if (totalOrderAmount >= CHAMPAGNE_PRESENT_PRICE) {
            System.out.println(ONE_CHAMPAGNE);
        }
        if (totalOrderAmount < CHAMPAGNE_PRESENT_PRICE) {
            System.out.println(X);
        }
    }

    private int giftEvent(int totalOrderAmount) {
        if (totalOrderAmount >= CHAMPAGNE_PRESENT_PRICE) {
            System.out.println(GIFT_SALE);
            return Constant.CHAMPAGNE_PRICE;
        }
        return 0;
    }

    private int weekdayDiscount(int day, List<Order> orders) {
        boolean isWeekend = EventController.weekdayCheck(day);
        int discount = DiscountCalculation.weekdayDessertDiscount(orders, isWeekend);

        if (discount > 0) {
            System.out.println(WEEK_SALE + price(discount) + WON);
        }
        return discount;
    }

    private int weekendMainDiscount(int day, List<Order> orders) {
        boolean isWeekend = EventController.weekdayCheck(day);
        int discount = DiscountCalculation.weekendMainMenuDiscount(orders, isWeekend);

        if (discount > 0) {
            System.out.println(WEEKEND_SALE + price(discount) + WON);
        }
        return discount;
    }

    private int specialDiscount(int day) {
        if (day == Constant.CHRISTMAS_DAY || EventController.isSunday(day)) {
            System.out.println(SPECIAL_SALE + price(Constant.SPECIAL_DISCOUNT) + WON);
            return Constant.SPECIAL_DISCOUNT;
        }
        return 0;
    }

    private int christmasDDayDiscount(int day) {
        if (day <= CHRISTMAS_DAY) {
            int discount = Constant.CHRISTMAS_START_DISCOUNT + (day - 1) * Constant.DAILY_INCREMENT;
            System.out.println(CHRISTMAS_SALE + price(discount) + WON);
            return discount;
        }
        return 0;
    }

    private void totalBenefitsAmount(int totalBenefitAmount) {
        System.out.println(TOTAL_DISCOUNT);
        if (totalBenefitAmount == 0) {
            System.out.println(price(totalBenefitAmount) + WON);
        }
        if (totalBenefitAmount > 0) {
            System.out.println(BAR + price(totalBenefitAmount) + WON);
        }
    }

    private void finalPaymentAmount(int finalPaymentAmount) {
        System.out.println(AFTER_DISCOUNT_MONEY);
        System.out.println(price(finalPaymentAmount) + WON);
    }

    private void givingBadge(String giveBadge) {
        System.out.println(DECEMBER_BADGE);
        System.out.println(giveBadge);
    }

    private String price(int price) {
        return String.format("%,d", price);
    }

}
