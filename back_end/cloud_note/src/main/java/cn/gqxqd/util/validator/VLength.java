package cn.gqxqd.util.validator;

public class VLength implements IValidator {

    private int min;
    private int max;
    private String message;
    private String defaultMessage;

    public VLength(int min, int max) {
        this.min = min;
        this.max = max;
        this.defaultMessage = "长度必须在" + min + "-" + max + "字符之间！";
    }

    public VLength(int min, int max, String message) {
        this.min = min;
        this.max = max;
        this.message = message;
    }

    public VLength(int max) {
        this.min = 0;
        this.max = max;
        this.defaultMessage = "长度必须小于" + max + "字符！";
    }

    public VLength(int max, String message) {
        this.min = 0;
        this.max = max;
        this.message = message;
    }

    @Override
    public boolean validate(String src) {
        boolean result = true;
        if (src == null) {
            result = false;
        } else {
            src = src.trim();
            if (src.length() < this.min || src.length() > this.max) {
                result = false;
            }
        }
        return result;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getDefaultMessage() {
        return defaultMessage;
    }
}
