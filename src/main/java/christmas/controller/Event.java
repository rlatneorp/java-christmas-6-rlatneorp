package christmas.controller;

import christmas.domain.ChristmasDayEvent;
import christmas.domain.DecemberEvent;
import christmas.view.InputView;

public class Event {
    private InputView inputView;

    public void decemberEventCheck() {
        int day = inputView.dayQuestion();
        DecemberEvent decemberEvent = new DecemberEvent();
        boolean dayChecked = decemberEvent.validateDecemberDays(day);
        if (dayChecked) {

        }
    }

    public void christmasEventCheck() {
        int day = inputView.dayQuestion();
        ChristmasDayEvent christmasDayEvent = new ChristmasDayEvent();
        boolean dayChecked = christmasDayEvent.validateChristmasDays(day);
        if (dayChecked) {

        }
    }

}
