package cn.gqxqd.util.validator;

public class VRequire implements IValidator {

    private String message;
    private String defaultMessage;

    public VRequire() {
        this.defaultMessage = "不能为空！";
    }

    public VRequire(String message) {
        this.message = message;
    }

    @Override
    public boolean validate(String src) {
        if (src == null || src.trim().equals(""))
            return false;
        else
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
