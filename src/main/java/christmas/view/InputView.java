package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String HELLO = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String DAY_QUESTION = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";

    public void printHello() {
        System.out.println(HELLO);
    }

    public int dayQuestion() {
        System.out.println(DAY_QUESTION);
        String dayAnswer = Console.readLine();
        return Integer.parseInt(dayAnswer);
    }




}
