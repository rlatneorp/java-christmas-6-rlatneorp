package christmas.domain;

import christmas.exception.NoValidateException;

import java.util.List;

import static christmas.domain.Constant.*;
import christmas.view.OutputView;

public class OrderCalculation {

    public void processOrders(List<Order> orders, int day) {
        validateOrder(orders);
        int totalOrder = orders.stream().mapToInt(Order::totalOrderPrice).sum();
        if (totalOrder < MIN_ORDER_AMOUNT) {
            throw new NoValidateException();
        }
        onlyDrink(orders);
        int totalDiscount = DiscountCalculation.totalDiscount(orders, day);
        int finalPayment = totalOrder - totalDiscount;
        String givingBadge = Badge.giveBadge(finalPayment);
        int totalBenefit = totalBenefit(orders, totalDiscount);
        OutputView outputView = new OutputView();
        outputView.displayOrder(orders, day, totalOrder, finalPayment, givingBadge);
    }

    private void onlyDrink(List<Order> orders) {
        if (allOrdersDrinks(orders)) {
            throw new NoValidateException();
        }
    }

    private boolean allOrdersDrinks(List<Order> orders) {
        return orders.stream().allMatch(Order::drinkOnly);
    }

    private void validateOrder(List<Order> orders) {
        if (orders.stream().anyMatch(order -> order.exceedsMaximumItems(MAX_ORDER_ITEMS))) {
            throw new NoValidateException();
        }
        if (orders.stream().allMatch(Order::drinkOnly)) {
            throw new NoValidateException();
        }
    }

    private int totalBenefit(List<Order> orders, int totalDiscount) {
        int champagnePrice = 0;
        if (isChampagnePresent(orders)) {
            champagnePrice = Constant.CHAMPAGNE_PRICE;
        }
        return totalDiscount + champagnePrice;
    }

    private boolean isChampagnePresent(List<Order> orders) {
        int totalOrderAmount = orders.stream().mapToInt(Order::calculatePrice).sum();
        return totalOrderAmount >= Constant.CHAMPAGNE_PRESENT_PRICE;
    }
}
