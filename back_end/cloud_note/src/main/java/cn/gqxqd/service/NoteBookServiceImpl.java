package cn.gqxqd.service;

import cn.gqxqd.dao.NoteBookDao;
import cn.gqxqd.model.NoteBook;
import cn.gqxqd.util.NoteResult;
import cn.gqxqd.util.validator.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class NoteBookServiceImpl implements NoteBookService {

    @Autowired
    private NoteBookDao noteBookDao;

    public NoteResult findAllNoteBook(String id) {
        NoteResult noteResult = new NoteResult();
        try {
            List<NoteBook> bookList = noteBookDao.findAll(Integer.parseInt(id));
            if (bookList == null) {
                noteResult.setMessage("查询笔记本列表失败！");
            } else {
                noteResult.setStatus(0);
                noteResult.setMessage("查询笔记本列表成功！");
                noteResult.setData(bookList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            noteResult.setStatus(500);
            noteResult.setMessage("服务器发生错误，请重试！");
        }
        return noteResult;
    }

    public NoteResult createNoteBook(String id, String bookName, String bookDesc) {
        NoteResult noteResult = new NoteResult();
        try {
            Validator validator = new Validator();
            validator.add(new Field(id)
                    .add(new VRequire())
                    .add(new VIsNumber())
            ).add(new Field(bookName, "笔记本名称")
                    .add(new VRequire())
                    .add(new VLength(3, 10))
            ).add(new Field(bookDesc, "笔记本描述")
                    .add(new VLength(100))
            );
            if (!validator.validate()) {
                noteResult.setMessage(validator.getMessages().get(0));
            } else {
                NoteBook noteBook = new NoteBook();
                noteBook.setCn_user_id(Integer.parseInt(id));
                noteBook.setCn_notebook_name(bookName);
                noteBook.setCn_notebook_desc(bookDesc);
                noteBook.setCn_notebook_createtime(new Timestamp(new Date().getTime()));
                noteBookDao.save(noteBook);
                if (noteBook.getCn_notebook_id() > 0) {
                    noteResult.setStatus(0);
                    noteResult.setMessage("新建笔记本成功！");
                    noteResult.setData(noteBook);
                } else {
                    noteResult.setMessage("新建笔记本失败，请重试！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            noteResult.setStatus(500);
            noteResult.setMessage("服务器发生错误，请重试！");
        }
        return noteResult;
    }

    @Override
    public NoteResult updateNoteBook(String id, String bookId, String bookName, String bookDesc) {
        NoteResult noteResult = new NoteResult();
        try {
            Validator validator = new Validator();
            validator.add(new Field(bookId, "笔记本id")
                    .add(new VRequire())
                    .add(new VIsNumber())
            ).add(new Field(bookName, "笔记本名称")
                    .add(new VRequire())
                    .add(new VLength(3, 10))
            ).add(new Field(bookDesc, "笔记本描述")
                    .add(new VLength(100))
            );
            if (!validator.validate()) {
                noteResult.setMessage(validator.getMessages().get(0));
            } else {
                NoteBook noteBook = new NoteBook();
                noteBook.setCn_user_id(Integer.parseInt(id));
                noteBook.setCn_notebook_id(Integer.parseInt(bookId));
                noteBook.setCn_notebook_name(bookName);
                noteBook.setCn_notebook_desc(bookDesc);
                int rows = noteBookDao.update(noteBook);
                if (rows > 0) {
                    noteResult.setStatus(0);
                    noteResult.setMessage("更新笔记本信息成功！");
                    noteResult.setData(noteBook);
                } else {
                    noteResult.setMessage("更新笔记本信息失败！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            noteResult.setStatus(500);
            noteResult.setMessage("服务器发生错误，请重试！");
        }
        return noteResult;
    }

    @Override
    public NoteResult deleteNoteBook(String id, String bookId) {
        NoteResult noteResult = new NoteResult();
        try {
            Validator validator = new Validator();
            validator.add(new Field(bookId, "笔记本id")
                    .add(new VRequire())
                    .add(new VIsNumber())
            );
            if (!validator.validate()) {
                noteResult.setMessage(validator.getMessages().get(0));
            } else {
                NoteBook noteBook = new NoteBook();
                noteBook.setCn_user_id(Integer.parseInt(id));
                noteBook.setCn_notebook_id(Integer.parseInt(bookId));
                int rows = noteBookDao.delete(noteBook);
                if (rows > 0) {
                    noteResult.setStatus(0);
                    noteResult.setMessage("删除笔记本成功！");
                } else {
                    noteResult.setMessage("删除笔记本失败！");
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
