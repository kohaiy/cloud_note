package cn.gqxqd.controller;

import cn.gqxqd.service.UserService;
import cn.gqxqd.util.NoteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/get")
    @ResponseBody
    public NoteResult get(int id) {
        return userService.get(id);
    }

    @RequestMapping("/find")
    @ResponseBody
    public NoteResult findAllNoteType() {
        return userService.findAllNoteType();
    }

    @RequestMapping("/register")
    @ResponseBody
    public NoteResult register(String username, String password) {
        return userService.register(username, password);
    }

    @RequestMapping("/user/checkUsername")
    @ResponseBody
    public NoteResult checkUsername(String username) {
        return userService.checkUsername(username);
    }

    @RequestMapping("/login")
    @ResponseBody
    public NoteResult login(String username, String password) {
        return userService.login(username, password);
    }

    @RequestMapping("/user/validator")
    @ResponseBody
    public NoteResult validator(String id, String token) {
        return userService.validator(id, token);
    }

    @RequestMapping("/user/updatePassword")
    @ResponseBody
    public NoteResult updatePassword(String id, String token, String oldPassword, String newPassword) {
        return userService.updatePassword(id, token, oldPassword, newPassword);
    }
}
