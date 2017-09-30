package cn.gqxqd.model;

import java.sql.Timestamp;

public class User {
    private Integer cn_user_id;
    private String cn_user_name;
    private String cn_user_password;
    private String cn_user_desc;
    private String cn_user_token;
    private Timestamp cn_user_update_time;

    public Integer getCn_user_id() {
        return cn_user_id;
    }

    public void setCn_user_id(Integer cn_user_id) {
        this.cn_user_id = cn_user_id;
    }

    public String getCn_user_name() {
        return cn_user_name;
    }

    public void setCn_user_name(String cn_user_name) {
        this.cn_user_name = cn_user_name;
    }

    public String getCn_user_password() {
        return cn_user_password;
    }

    public void setCn_user_password(String cn_user_password) {
        this.cn_user_password = cn_user_password;
    }

    public String getCn_user_desc() {
        return cn_user_desc;
    }

    public void setCn_user_desc(String cn_user_desc) {
        this.cn_user_desc = cn_user_desc;
    }

    public String getCn_user_token() {
        return cn_user_token;
    }

    public void setCn_user_token(String cn_user_token) {
        this.cn_user_token = cn_user_token;
    }

    public Timestamp getCn_user_update_time() {
        return cn_user_update_time;
    }

    public void setCn_user_update_time(Timestamp cn_user_update_time) {
        this.cn_user_update_time = cn_user_update_time;
    }

    @Override
    public String toString() {
        return "User{" +
                "cn_user_id=" + cn_user_id +
                ", cn_user_name='" + cn_user_name + '\'' +
                ", cn_user_password='" + cn_user_password + '\'' +
                ", cn_user_desc='" + cn_user_desc + '\'' +
                ", cn_user_token='" + cn_user_token + '\'' +
                ", cn_user_update_time=" + cn_user_update_time +
                '}';
    }
}
