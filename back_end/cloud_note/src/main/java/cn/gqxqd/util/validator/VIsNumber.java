package cn.gqxqd.util.validator;

public class VIsNumber implements IValidator {

    private String message;
    private String defaultMessage;

    public VIsNumber() {
        this.defaultMessage = "必须为数字类型！";
    }

    public VIsNumber(String message) {
        this.message = message;
    }

    @Override
    public boolean validate(String src) {
        boolean result = true;
        try {
            Integer.parseInt(src);
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String getDefaultMessage() {
        return defaultMessage;
    }
}
