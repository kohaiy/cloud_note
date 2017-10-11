package cn.gqxqd.util.validator;

import java.util.regex.Pattern;

public class VRegex implements IValidator {

    private String regex;
    private String message;
    private String defaultMessage;

    public VRegex(String regex) {
        this.regex = regex;
        this.defaultMessage = "格式不正确！";
    }

    public VRegex(String regex, String message) {
        this.regex = regex;
        this.message = message;
    }

    @Override
    public boolean validate(String src) {
        if (src == null) {
            return false;
        }
        if (!Pattern.matches(this.regex, src)) {
            return false;
        }
        return true;
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
