package cn.gqxqd.model;

import java.sql.Timestamp;

public class Share {

    private Integer cn_share_id;
    private String cn_share_code;
    private Integer cn_note_id;
    private String cn_share_desc;
    private Timestamp cn_share_time;

    public Integer getCn_share_id() {
        return cn_share_id;
    }

    public void setCn_share_id(Integer cn_share_id) {
        this.cn_share_id = cn_share_id;
    }

    public String getCn_share_code() {
        return cn_share_code;
    }

    public void setCn_share_code(String cn_share_code) {
        this.cn_share_code = cn_share_code;
    }

    public Integer getCn_note_id() {
        return cn_note_id;
    }

    public void setCn_note_id(Integer cn_note_id) {
        this.cn_note_id = cn_note_id;
    }

    public String getCn_share_desc() {
        return cn_share_desc;
    }

    public void setCn_share_desc(String cn_share_desc) {
        this.cn_share_desc = cn_share_desc;
    }

    public Timestamp getCn_share_time() {
        return cn_share_time;
    }

    public void setCn_share_time(Timestamp cn_share_time) {
        this.cn_share_time = cn_share_time;
    }

    @Override
    public String toString() {
        return "Share{" +
                "cn_share_id=" + cn_share_id +
                ", cn_share_code='" + cn_share_code + '\'' +
                ", cn_note_id=" + cn_note_id +
                ", cn_share_desc='" + cn_share_desc + '\'' +
                ", cn_share_time=" + cn_share_time +
                '}';
    }
}
