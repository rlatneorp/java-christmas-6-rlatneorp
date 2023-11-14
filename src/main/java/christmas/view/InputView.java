package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Menu;
import christmas.exception.NoValidateException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputView {
    private static final String HELLO = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String DAY_QUESTION = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String MENU_QUESTION = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String DELIMITER = ",";
    private static final String BAR = "-";

    public void printHello() {
        System.out.println(HELLO);
    }

    public int dayQuestion() {
        printHello();
        System.out.println(DAY_QUESTION);
        String dayAnswer = Console.readLine();
        return Integer.parseInt(dayAnswer);
    }

    public String menuInput() {
        System.out.println(MENU_QUESTION);
        return Console.readLine();
    }

    public List<Menu> menuNames(String rawInput) {
        return Stream.of(rawInput.split(DELIMITER))
                .map(String::trim)
                .map(this::menuName)
                .collect(Collectors.toList());
    }

    public List<Integer> menuAmounts(String rawInput) {
        return Stream.of(rawInput.split(DELIMITER))
                .map(String::trim)
                .map(this::menuAmount)
                .collect(Collectors.toList());
    }

    private Menu menuName(String menuString) {
        String menuName = menuString.substring(0, menuString.lastIndexOf(BAR)).trim();
        return Menu.menuName(menuName);
    }

    private int menuAmount(String menuString) {
        String amountStr = menuString.substring(menuString.lastIndexOf(BAR) + 1).trim();
        if (!isNumeric(amountStr)) {
            throw new NoValidateException();
        }
        return Integer.parseInt(amountStr);
    }

    private boolean isNumeric(String orderNumber) {
        return orderNumber.matches("-?\\d+");
    }

}
