package christmas;

import christmas.controller.EventController;

public class Application {
    public static void main(String[] args) {
        EventController controller = new EventController();
        controller.startEvent();
    }
}
