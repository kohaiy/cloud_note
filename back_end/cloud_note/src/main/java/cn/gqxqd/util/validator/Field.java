package cn.gqxqd.util.validator;

import java.util.ArrayList;
import java.util.List;

public class Field {
    private String src;
    private String name;
    private List<IValidator> validators;
    private List<String> messages;

    public Field(String src) {
        this.src = src;
        this.name = "该字段";
    }

    public Field(String src, String name) {
        this.src = src;
        this.name = name;
    }

    public Field add(IValidator validator) {
        if (this.validators == null) this.validators = new ArrayList<>();
        this.validators.add(validator);
        return this;
    }

    public boolean validate() {
        if (this.validators == null) return true;
        boolean result = true;
        for (int i = 0; i < this.validators.size(); i++) {
            if (!validators.get(i).validate(this.src)) {
                result = false;
                if (this.messages == null) this.messages = new ArrayList<>();
                if (validators.get(i).getMessage() == null)
                    this.messages.add(this.name + validators.get(i).getDefaultMessage());
                else
                    this.messages.add(validators.get(i).getMessage());
            }
        }
        return result;
    }

    public List<String> getMessages() {
        return messages;
    }
}
