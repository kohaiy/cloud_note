package cn.gqxqd.service;

import cn.gqxqd.dao.NoteDao;
import cn.gqxqd.model.Note;
import cn.gqxqd.util.NoteResult;
import cn.gqxqd.util.validator.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteDao noteDao;

    @Override
    public NoteResult findAllByUserId(String id) {
        NoteResult noteResult = new NoteResult();
        try {
            Validator validator = new Validator();
            validator.add(new Field(id, "用户id")
                    .add(new VRequire())
                    .add(new VIsNumber())
            );
            if (!validator.validate()) {
                noteResult.setMessage(validator.getMessages().get(0));
            } else {
                List<Note> noteList = noteDao.findAll(Integer.parseInt(id));
                noteResult.setStatus(0);
                noteResult.setMessage("笔记列表查询成功！");
                noteResult.setData(noteList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            noteResult.setStatus(500);
            noteResult.setMessage("服务器发生错误，请重试！");
        }
        return noteResult;
    }

    @Override
    public NoteResult createNote(String id, String bookId, String title, String content) {
        NoteResult noteResult = new NoteResult();
        try {
            Validator validator = new Validator();
            validator.add(new Field(id, "用户id")
                    .add(new VRequire())
                    .add(new VIsNumber())
            ).add(new Field(bookId, "笔记本id")
                    .add(new VRequire())
                    .add(new VIsNumber())
            ).add(new Field(title, "笔记标题")
                    .add(new VRequire())
                    .add(new VLength(50))
            ).add(new Field(content, "笔记内容")
                    .add(new VRequire())
                    .add(new VLength(1000))
            );
            if (!validator.validate()) {
                noteResult.setMessage(validator.getMessages().get(0));
            } else {
                Note note = new Note();
                note.setCn_user_id(Integer.parseInt(id));
                note.setCn_notebook_id(Integer.parseInt(bookId));
                note.setCn_note_title(title);
                note.setCn_note_content(content);
                note.setCn_note_createtime(new Timestamp(new Date().getTime()));
                note.setCn_note_updatetime(note.getCn_note_createtime());
                noteDao.save(note);
                if (note.getCn_note_id() > 0) {
                    noteResult.setStatus(0);
                    noteResult.setMessage("笔记添加成功！");
                    noteResult.setData(note);
                } else {
                    noteResult.setMessage("笔记添加失败，请重试！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            noteResult.setStatus(500);
            noteResult.setMessage("服务器发生错误，请重试！");
        }
        return noteResult;
    }
}
