package cn.gqxqd.controller;

import cn.gqxqd.service.NoteService;
import cn.gqxqd.service.UserService;
import cn.gqxqd.util.NoteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class NoteController {

    @Autowired
    private NoteService noteService;
    @Autowired
    private UserService userService;

    @RequestMapping("/note/findAll")
    @ResponseBody
    public NoteResult findAll(String id, String token) {
        NoteResult noteResult = userService.validator(id, token);
        if (noteResult.getStatus() == 0) {
            noteResult = noteService.findAllByUserId(id);
        }
        return noteResult;
    }

    @RequestMapping("/note/add")
    @ResponseBody
    public NoteResult add(String id, String token, String bookId, String title, String content) {
        NoteResult noteResult = userService.validator(id, token);
        if (noteResult.getStatus() == 0) {
            noteResult = noteService.createNote(id, bookId, title, content);
        }
        return noteResult;
    }
}
