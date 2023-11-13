package christmas.exception;

public class NoValidateException extends IllegalArgumentException{
    private static final String ERROR_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    public NoValidateException() {
        super(ERROR_MESSAGE);
    }
}
