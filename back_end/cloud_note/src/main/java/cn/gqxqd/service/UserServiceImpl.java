package cn.gqxqd.service;

import cn.gqxqd.dao.NoteTypeDao;
import cn.gqxqd.dao.UserDao;
import cn.gqxqd.model.User;
import cn.gqxqd.util.NoteResult;
import cn.gqxqd.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private NoteTypeDao noteTypeDao;

    public NoteResult get(int id) {
        NoteResult noteResult = new NoteResult();
        User user = userDao.get(id);
        noteResult.setStatus(0);
        noteResult.setMessage("获取用户成功！");
        noteResult.setData(user);
        return noteResult;
    }

    public NoteResult findAllNoteType() {
        NoteResult noteResult = new NoteResult();
        noteResult.setData(noteTypeDao.findAll());
        return noteResult;
    }

    public NoteResult register(String name, String password) {
        NoteResult noteResult = new NoteResult();
        noteResult.setStatus(1);
        try {
            if (name == null || password == null) {
                noteResult.setMessage("用户名或密码不能为空！");
            } else if (name.trim().length() < 3 || name.trim().length() > 10) {
                noteResult.setMessage("用户名必须在3-10字符之间！");
            } else if (password.trim().length() < 6 || password.trim().length() > 20) {
                noteResult.setMessage("密码必须在6-20字符之间！");
            } else if (userDao.findUserByName(name) != null) {
                noteResult.setMessage("该用户名已经存在了！");
            } else {
                User user = new User();
                user.setCn_user_name(name.trim());
                user.setCn_user_password(password.trim());
                int id = userDao.save(user);
                if (id > 0) {
                    noteResult.setStatus(0);
                    noteResult.setMessage("注册成功！用户id为：" + user.getCn_user_id());
                } else {
                    noteResult.setStatus(1);
                    noteResult.setMessage("注册失败！错误码为：" + user.getCn_user_id());
                }
            }
        } catch (Exception e) {
            noteResult.setStatus(500);
            noteResult.setMessage("服务器发生错误，请重试！");
        }
        return noteResult;
    }

    public NoteResult checkUsername(String username) {
        NoteResult noteResult = new NoteResult();
        try {
            if (username == null || "".equals(username.trim())) {
                noteResult.setStatus(1);
                noteResult.setMessage("用户名不能为空！");
            } else if (userDao.findUserByName(username.trim()) == null) {
                noteResult.setStatus(0);
                noteResult.setMessage("用户名不存在！");
            } else {
                noteResult.setStatus(1);
                noteResult.setMessage("用户名存在！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            noteResult.setStatus(500);
            noteResult.setMessage("服务器发生错误，请重试！");
        }
        return noteResult;
    }

    public NoteResult login(String name, String password) {
        NoteResult noteResult = new NoteResult();
        noteResult.setStatus(1);
        try {
            if (name == null || "".equals(name.trim())) {
                noteResult.setMessage("用户名不能为空！");
            } else if (password == null || "".equals(password.trim())) {
                noteResult.setMessage("密码不能为空！");
            } else {
                User user = userDao.findUserByName(name);
                if (user == null) {
                    noteResult.setMessage("用户名不存在！");
                } else if (!password.trim().equals(user.getCn_user_password())) {
                    noteResult.setMessage("密码不正确！");
                } else {
                    user.setCn_user_token(Utils.createUUID());
                    userDao.updateToken(user);
                    noteResult.setStatus(0);
                    noteResult.setMessage("登录成功！");
                    user.setCn_user_password("******");
                    noteResult.setData(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            noteResult.setStatus(500);
            noteResult.setMessage("服务器发生错误，请重试！");
        }
        return noteResult;
    }

    public NoteResult validator(String id, String token) {
        NoteResult noteResult = new NoteResult();
        noteResult.setStatus(1);
        try {
            if (id == null || "".equals(id)) {
                noteResult.setMessage("用户id不能为空！");
            } else if (token == null || "".equals(token)) {
                noteResult.setMessage("用户token不能为空！");
            } else {
                User user = userDao.get(Integer.parseInt(id));
                if (user == null) {
                    noteResult.setMessage("用户不存在！");
                } else if (!token.equals(user.getCn_user_token())) {
                    noteResult.setMessage("用户令牌不正确！");
                } else if ((new Date()).getTime() - user.getCn_user_update_time().getTime() > 1000 * 60 * 60) {
                    noteResult.setMessage("登录超时！");
                } else {
                    noteResult.setStatus(0);
                    noteResult.setMessage("验证通过！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            noteResult.setStatus(500);
            noteResult.setMessage("服务器发生错误，请重试！");
        }
        return noteResult;
    }

    public NoteResult updatePassword(String id, String token, String oldPassword, String newPassword) {
        NoteResult noteResult = validator(id, token);
        if (noteResult.getStatus() != 0) return noteResult;
        noteResult.setStatus(1);
        try {
            if (oldPassword == null || "".equals(oldPassword)) {
                noteResult.setMessage("旧密码不能为空！");
            } else if (newPassword == null || "".equals(newPassword)) {
                noteResult.setMessage("新密码不能为空！");
            } else {
                User user = userDao.get(Integer.parseInt(id));
                if (user == null || !user.getCn_user_password().equals(oldPassword)) {
                    noteResult.setMessage("旧密码输入不正确！");
                } else {
                    user.setCn_user_password(newPassword);
                    userDao.updatePassword(user);
                    noteResult.setStatus(0);
                    noteResult.setMessage("密码修改成功！");
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
