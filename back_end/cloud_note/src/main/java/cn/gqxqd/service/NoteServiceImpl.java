package cn.gqxqd.service;

import cn.gqxqd.dao.NoteDao;
import cn.gqxqd.model.Note;
import cn.gqxqd.util.NoteResult;
import cn.gqxqd.util.validator.Field;
import cn.gqxqd.util.validator.VIsNumber;
import cn.gqxqd.util.validator.VRequire;
import cn.gqxqd.util.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
