package christmas.controller;

import christmas.domain.Constant;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.OrderCalculation;
import christmas.view.InputView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static christmas.domain.Constant.YEAR;


public class EventController {
    private final InputView inputView = new InputView();

    public void startEvent() {
        inputView.printHello();
        int day = inputView.dayQuestion();
        String menuInput = inputView.menuInput();

        List<Menu> orderMenuNames = inputView.menuNames(menuInput);
        List<Integer> amounts = inputView.menuAmounts(menuInput);

        List<Order> orders = createOrders(orderMenuNames, amounts);
        processOrders(orders, day);
    }

    private void processOrders(List<Order> orders, int day) {
        OrderCalculation orderCalculation = new OrderCalculation();
        orderCalculation.processOrders(orders, day);
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

    public static boolean isSunday(int day) {
        Calendar calendar = new GregorianCalendar(YEAR, Calendar.DECEMBER, day);
        return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }

    public static boolean weekdayCheck(int day) {
        Calendar calendar = new GregorianCalendar(Constant.YEAR, Calendar.DECEMBER, day);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        return dayOfWeek == Calendar.FRIDAY || dayOfWeek == Calendar.SATURDAY;
    }

}
