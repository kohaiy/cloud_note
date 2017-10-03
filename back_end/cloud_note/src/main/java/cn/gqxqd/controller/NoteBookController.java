package cn.gqxqd.controller;

import cn.gqxqd.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class NoteBookController {

    @Autowired
    private NoteService noteService;
}
