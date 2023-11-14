package christmas.view;

import christmas.domain.Order;

import java.util.List;

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

    public static void displayOrder(List<Order> orders, int day, int totalOrderAmount, int totalDiscount, int totalBenefitAmount, int finalPaymentAmount, String awardedBadge) {
        System.out.println(DECEMBER_MESSAGE + day + DECEMBER_PREVIEW_MESSAGE);

        System.out.println(ORDER_MENU);
        for (Order order : orders) {
            System.out.println(order.orderInfo());
        }

        System.out.println(BEFORE_DISCOUNT_MONEY);
        System.out.println(price(totalOrderAmount) + WON);
        System.out.println(PRESENT);
        System.out.println(DISCOUNT_LIST);
        System.out.println(TOTAL_DISCOUNT);
        System.out.println(price(totalBenefitAmount) + WON);
        System.out.println(AFTER_DISCOUNT_MONEY);
        System.out.println(price(finalPaymentAmount) + WON);
        System.out.println(DECEMBER_BADGE);
        System.out.println(awardedBadge);
    }

    private static String price(int price) {
        return String.format("%,d", price);
    }

}
