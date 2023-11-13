package christmas.exception;

public class DayRangeException extends IllegalArgumentException{
    private static final String ERROR_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    public DayRangeException() {
        super(ERROR_MESSAGE);
    }
}