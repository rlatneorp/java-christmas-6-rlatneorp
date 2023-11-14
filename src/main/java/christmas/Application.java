package christmas;

import christmas.controller.EventController;

public class Application {
    public static void main(String[] args) {
        while (true) {
            try {
                EventController controller = new EventController();
                controller.startEvent();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
