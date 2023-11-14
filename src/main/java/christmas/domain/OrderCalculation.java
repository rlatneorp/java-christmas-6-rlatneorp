package christmas.domain;

import christmas.exception.NoValidateException;

import java.util.Calendar;
import java.util.List;

import static christmas.domain.Constant.*;
import static christmas.domain.DiscountCalculation.calculateTotalBenefitAmount;
import static christmas.view.OutputView.displayOrder;

public class OrderCalculation {

    public void processOrders(List<Order> orders, int day, int month, int year) {
        validateOrder(orders);
        int totalOrderAmount = orders.stream().mapToInt(Order::totalOrderPrice).sum();
            if (totalOrderAmount < MIN_ORDER_AMOUNT) {
                throw new NoValidateException();
            }
        int totalDiscount = DiscountCalculation.calculateTotalDiscount(orders, day, MONTH, YEAR);
        int finalPaymentAmount = totalOrderAmount - totalDiscount;
        String awardedBadge = Badge.awardBadge(finalPaymentAmount);
        int totalBenefitAmount = calculateTotalBenefitAmount(orders, day, month, year);

        displayOrder(orders, day, totalOrderAmount, totalDiscount, totalBenefitAmount, finalPaymentAmount, awardedBadge);
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
            throw new NoValidateException();
        }

        if (orders.stream().allMatch(Order::isDrinkOnly)) {
            throw new NoValidateException();
        }
    }


}
