package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Menu;
import christmas.exception.DayRangeException;
import christmas.exception.NoValidateException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static christmas.domain.Constant.MAX_DAY;
import static christmas.domain.Constant.MIN_DAY;

public class InputView {
    private static final String HELLO = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String DAY_QUESTION = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String MENU_QUESTION = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String DELIMITER = ",";
    private static final String BAR = "-";
    private static final int MIN_AMOUNT = 1;

    public void printHello() {
        System.out.println(HELLO);
    }

    public int dayQuestion() {
        while (true) {
            try {
                System.out.println(DAY_QUESTION);
                String dayAnswer = Console.readLine().trim();
                validateInputIsNumberFormat(dayAnswer);
                int day = Integer.parseInt(dayAnswer);
                validateDay(day);
                return day;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public boolean isNumberWord(String checkedInput) {
        if(checkedInput.isBlank()) {
            return false;
        }
        for (int i = 0; i < checkedInput.length(); i++) {
            char uncheckedCharacter = checkedInput.charAt(i);
            if(!Character.isDigit(uncheckedCharacter)) {
                return false;
            }
        }
        return true;
    }

    public void validateInputIsNumberFormat(String checkedInput) {
        boolean isNumber = isNumberWord(checkedInput);
        if(!isNumber) {
            throw new DayRangeException();
        }
    }

    public String menuInput() {
        while (true) {
            try {
                System.out.println(MENU_QUESTION);
                String menu = Console.readLine();
                validateMenuVarious(menu);
                return menu;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public List<Menu> menuNames(String menuInput) {
        List<Menu> menus = Stream.of(menuInput.split(DELIMITER))
                .map(String::trim)
                .map(this::menuName)
                .collect(Collectors.toList());
        validateDuplicateMenu(menus);
        return menus;
    }

    public List<Integer> menuAmounts(String menuInput) {
        return Stream.of(menuInput.split(DELIMITER))
                .map(String::trim)
                .map(this::menuAmount)
                .collect(Collectors.toList());
    }

    private Menu menuName(String menu) {
        String menuName = menu.substring(0, menu.lastIndexOf(BAR)).trim();
        return Menu.menuName(menuName);
    }

    private int menuAmount(String menu) {
        String amount = menu.substring(menu.lastIndexOf(BAR) + 1).trim();
        if (numberCheck(amount)) {
            throw new NoValidateException();
        }
        return Integer.parseInt(amount);
    }

    private boolean numberCheck(String orderNumber) {
        return !orderNumber.matches("-?\\d+");
    }

    private void validateMenuVarious(String menu) {
        validateMenuAmount(menu);
        validateMenuName(menu);
    }

    private void validateDuplicateMenu(List<Menu> menus) {
        Set<Menu> oneMenus = new HashSet<>();
        for (Menu menu : menus) {
            if (!oneMenus.add(menu)) {
                throw new NoValidateException();
            }
        }
    }

    private void validateMenuAmount(String menu) {
        if (!menu.contains(BAR)) {
            throw new NoValidateException();
        }
        String amount = String.valueOf(menuAmount(menu));
        if (numberCheck(amount) || Integer.parseInt(amount) < MIN_AMOUNT) {
            throw new NoValidateException();
        }
    }

    private void validateMenuName(String menu) {
        if (!menu.contains(BAR)) {
            throw new NoValidateException();
        }
        String menuName = menu.substring(0, menu.lastIndexOf(BAR)).trim();
        if (menuName.isEmpty()) {
            throw new NoValidateException();
        }
    }

    private void validateDay(int day) {
        if (day < MIN_DAY || day > MAX_DAY) {
            throw new DayRangeException();
        }
    }
}
