package cn.gqxqd.model;

public class NoteType {

    private Integer cn_note_type_id;
    private String cn_note_type_name;
    private String cn_note_type_desc;

    public Integer getCn_note_type_id() {
        return cn_note_type_id;
    }

    public void setCn_note_type_id(Integer cn_note_type_id) {
        this.cn_note_type_id = cn_note_type_id;
    }

    public String getCn_note_type_name() {
        return cn_note_type_name;
    }

    public void setCn_note_type_name(String cn_note_type_name) {
        this.cn_note_type_name = cn_note_type_name;
    }

    public String getCn_note_type_desc() {
        return cn_note_type_desc;
    }

    public void setCn_note_type_desc(String cn_note_type_desc) {
        this.cn_note_type_desc = cn_note_type_desc;
    }

    @Override
    public String toString() {
        return "NoteType{" +
                "cn_note_type_id=" + cn_note_type_id +
                ", cn_note_type_name='" + cn_note_type_name + '\'' +
                ", cn_note_type_desc='" + cn_note_type_desc + '\'' +
                '}';
    }
}
