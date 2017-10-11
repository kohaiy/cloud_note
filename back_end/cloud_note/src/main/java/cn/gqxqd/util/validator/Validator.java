package cn.gqxqd.util.validator;

import java.util.ArrayList;
import java.util.List;

public class Validator {

    private List<Field> fields;
    private List<String> messages;

    /**
     * public static void main(String[] args) {
     * <p>
     * Validator validator = new Validator();
     * validator.add(new Field(null)
     * .add(new VRequire())
     * .add(new VIsNumber())
     * .add(new VLength(3, 10))
     * .add(new VRegex("[a-z]*"))
     * ).add(new Field("1212412@qq.1com")
     * .add(new VRequire("邮箱不能为空！"))
     * .add(new VRegex("\\w+@\\w+\\.[a-zA-Z]+", "邮箱格式不正确！"))
     * );
     * if (validator.validate()) {
     * System.out.println("success...");
     * } else {
     * validator.getMessages().forEach(msg -> {
     * System.out.println(msg);
     * });
     * }
     * <p>
     * }
     */
    public Validator add(Field field) {
        if (this.fields == null) this.fields = new ArrayList<>();
        this.fields.add(field);
        return this;
    }

    public boolean validate() {
        if (this.fields == null) return true;
        boolean result = true;
        for (int i = 0; i < this.fields.size(); i++) {
            if (!fields.get(i).validate()) {
                result = false;
                if (this.messages == null) this.messages = new ArrayList<>();
                fields.get(i).getMessages().forEach(msg -> {
                    this.messages.add(msg);
                });
            }
        }
        return result;
    }

    public List<String> getMessages() {
        return messages;
    }
}

