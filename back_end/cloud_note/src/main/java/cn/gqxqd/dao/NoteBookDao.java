package cn.gqxqd.dao;

import cn.gqxqd.model.NoteBook;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteBookDao {

    @Select("select * from cn_notebook where cn_user_id = #{cn_user_id}")
    List<NoteBook> findAll(Integer cn_user_id);

    void save(NoteBook noteBook);

    int update(NoteBook noteBook);

    int delete(NoteBook noteBook);
}
