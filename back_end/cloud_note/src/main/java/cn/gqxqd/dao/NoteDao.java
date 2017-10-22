package cn.gqxqd.dao;

import cn.gqxqd.model.Note;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteDao {

    List<Note> findAll(Integer cn_user_id);

    void save(Note note);
}
