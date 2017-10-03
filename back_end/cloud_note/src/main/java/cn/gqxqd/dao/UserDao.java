package cn.gqxqd.dao;

import cn.gqxqd.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    User get(Integer cn_user_id);

    User findUserByName(String cn_user_name);

    int save(User user);

    void updateToken(User user);

    void updatePassword(User user);
}
