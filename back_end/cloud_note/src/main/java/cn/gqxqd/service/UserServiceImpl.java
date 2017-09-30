package cn.gqxqd.service;

import cn.gqxqd.dao.UserDao;
import cn.gqxqd.model.User;
import cn.gqxqd.util.NoteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public NoteResult get(int id) {
        NoteResult noteResult = new NoteResult();
        User user = userDao.get(id);
        noteResult.setStatus(0);
        noteResult.setMessage("获取用户成功！");
        noteResult.setData(user);
        return noteResult;
    }
}
