package christmas.controller;

import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.OrderCalculation;
import christmas.view.InputView;

import java.util.ArrayList;
import java.util.List;

import static christmas.domain.Constant.YEAR;
import static java.util.Calendar.MONTH;

public class EventController {
    private final InputView inputView = new InputView();

    public void startEvent() {
        int day = inputView.dayQuestion();
        List<Menu> orderMenuNames = inputView.orderMenuNames();
        List<Integer> amounts = inputView.orderNumberValues(orderMenuNames.toString());

        List<Order> orders = createOrders(orderMenuNames, amounts);

        OrderCalculation orderCalculation = new OrderCalculation();
        orderCalculation.processOrders(orders, day, MONTH, YEAR);
    }

    private List<Order> createOrders(List<Menu> menuNames, List<Integer> amounts) {
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < menuNames.size(); i++) {
            Menu menu = Menu.menuName(String.valueOf(menuNames.get(i)));
            int amount = amounts.get(i);
            orders.add(new Order(menu, amount));
        }
        return orders;
    }

}
