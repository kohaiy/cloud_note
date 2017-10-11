package cn.gqxqd.controller;

import cn.gqxqd.service.NoteBookService;
import cn.gqxqd.service.NoteService;
import cn.gqxqd.service.UserService;
import cn.gqxqd.util.NoteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class NoteBookController {

    @Autowired
    private NoteBookService noteBookService;

    @Autowired
    private UserService userService;

    @RequestMapping("/book/findAll")
    @ResponseBody
    public NoteResult findAllNoteBook(String id, String token) {
        NoteResult noteResult = userService.validator(id, token);
        if (noteResult.getStatus() == 0) {
            noteResult = noteBookService.findAllNoteBook(id);
        }
        return noteResult;
    }

    @RequestMapping("/book/create")
    @ResponseBody
    public NoteResult createNoteBook(String id, String token, String bookName, String bookDesc) {
        NoteResult noteResult = userService.validator(id, token);
        if (noteResult.getStatus() == 0) {
            noteResult = noteBookService.createNoteBook(id, bookName, bookDesc);
        }
        return noteResult;
    }

    @RequestMapping("/book/update")
    @ResponseBody
    public NoteResult updateNoteBook(String id, String token, String bookId, String bookName, String bookDesc) {
        NoteResult noteResult = userService.validator(id, token);
        if (noteResult.getStatus() == 0) {
            noteResult = noteBookService.updateNoteBook(id, bookId, bookName, bookDesc);
        }
        return noteResult;
    }

    @RequestMapping("/book/delete")
    @ResponseBody
    public NoteResult deleteNoteBook(String id, String token, String bookId) {
        NoteResult noteResult = userService.validator(id, token);
        if (noteResult.getStatus() == 0) {
            noteResult = noteBookService.deleteNoteBook(id, bookId);
        }
        return noteResult;
    }
}
