package christmas.domain;

import java.util.List;

import static christmas.domain.Constant.*;

public class OrderCalculation {

    public void processOrders(List<Order> orders, int day) {
        validateOrder(orders);
        int totalOrderAmount = orders.stream().mapToInt(Order::totalOrderPrice).sum();
            if (totalOrderAmount < MIN_ORDER_AMOUNT) {
                throw new IllegalArgumentException("Minimum order amount is 10,000원.");
            }
        int totalDiscount = DiscountCalculation.calculateTotalDiscount(orders, day, month, year);
        int finalPaymentAmount = totalOrderAmount - totalDiscount;
        String awardedBadge = Badge.awardBadge(finalPaymentAmount);

        displayOrder(orders, totalOrderAmount, totalDiscount, finalPaymentAmount, awardedBadge);
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

    private void validateOrder(List<Order> orders) {
        if (orders.stream().anyMatch(order -> order.exceedsMaximumItems(MAX_ORDER_ITEMS))) {
            throw new IllegalArgumentException("Cannot order more than 20 items at once.");
        }

        if (orders.stream().allMatch(Order::isDrinkOnly)) {
            throw new IllegalArgumentException("Cannot order only drinks.");
        }
    }


}
