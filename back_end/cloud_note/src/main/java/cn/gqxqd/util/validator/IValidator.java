package cn.gqxqd.util.validator;

public interface IValidator {
    boolean validate(String src);

    String getMessage();

    String getDefaultMessage();
}
