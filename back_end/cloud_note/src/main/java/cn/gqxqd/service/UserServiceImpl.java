package cn.gqxqd.service;

import cn.gqxqd.dao.NoteTypeDao;
import cn.gqxqd.dao.UserDao;
import cn.gqxqd.model.User;
import cn.gqxqd.util.validator.*;
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
        try {
            Validator validator = new Validator();
            validator.add(new Field(name, "用户名")
                    .add(new VRequire())
                    .add(new VLength(3, 10))
            ).add(new Field(password, "密码")
                    .add(new VRequire())
                    .add(new VLength(6, 20))
            );
            if (!validator.validate()) {
                noteResult.setMessage(validator.getMessages().get(0));
            } else if (userDao.findUserByName(name) != null) {
                noteResult.setMessage("该用户名已经存在了！");
            } else {
                User user = new User();
                user.setCn_user_name(name.trim());
                user.setCn_user_password(password.trim());
                userDao.save(user);
                if (user.getCn_user_id() > 0) {
                    noteResult.setStatus(0);
                    noteResult.setMessage("注册成功！用户id为：" + user.getCn_user_id());
                } else {
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
            Validator validator = new Validator();
            validator.add(new Field(username, "用户名")
                    .add(new VRequire())
                    .add(new VLength(3, 10))
            );
            if (!validator.validate()) {
                noteResult.setMessage(validator.getMessages().get(0));
            } else if (userDao.findUserByName(username.trim()) == null) {
                noteResult.setStatus(0);
                noteResult.setMessage("用户名不存在！");
            } else {
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
        try {
            Validator validator = new Validator();
            validator.add(new Field(name, "用户名")
                    .add(new VRequire())
                    .add(new VLength(3, 10))
            ).add(new Field(password, "密码")
                    .add(new VRequire())
                    .add(new VLength(6, 20))
            );
            if (!validator.validate()) {
                noteResult.setMessage(validator.getMessages().get(0));
            } else {
                User user = userDao.findUserByName(name);
                if (user == null) {
                    noteResult.setMessage("用户不存在！");
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
        try {
            Validator validator = new Validator();
            validator.add(new Field(id, "用户id")
                    .add(new VRequire())
                    .add(new VIsNumber())
            ).add(new Field(token, "令牌")
                    .add(new VRequire())
            );
            if (!validator.validate()) {
                noteResult.setMessage(validator.getMessages().get(0));
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

    public NoteResult updatePassword(String id, String oldPassword, String newPassword) {
        NoteResult noteResult = new NoteResult();
        try {
            Validator validator = new Validator();
            validator.add(new Field(oldPassword, "旧密码")
                    .add(new VRequire())
                    .add(new VLength(6, 20))
            ).add(new Field(newPassword, "新密码")
                    .add(new VRequire())
                    .add(new VLength(6, 20))
            );
            if (!validator.validate()) {
                noteResult.setMessage(validator.getMessages().get(0));
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
