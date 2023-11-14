package christmas.domain;

import java.util.List;

public class OrderCalculation {
    public void processOrders(List<Order> orders, int day, int month, int year) {
        int totalOrderAmount = calculateTotalOrderAmount(orders);
        int totalDiscount = DiscountCalculation.calculateTotalDiscount(orders, day, month, year);
        int finalPaymentAmount = totalOrderAmount - totalDiscount;
        String awardedBadge = calculateBadge(finalPaymentAmount);

        displayOrderSummary(orders, totalOrderAmount, totalDiscount, finalPaymentAmount, awardedBadge);
    }

    private int calculateTotalOrderAmount(List<Order> orders) {
        return orders.stream()
                .mapToInt(Order::calculatePrice)
                .sum();
    }

    private String calculateBadge(int finalPaymentAmount) {
        if (finalPaymentAmount >= Constant.SANTA_BADGE) {
            return "Santa";
        } else if (finalPaymentAmount >= Constant.TREE_BADGE) {
            return "Tree";
        } else if (finalPaymentAmount >= Constant.STAR_BADGE ) {
            return "Star";
        }
        return "None";
    }


}
