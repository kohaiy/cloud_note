package cn.gqxqd.dao;

import cn.gqxqd.model.NoteType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteTypeDao {

    @Select("select * from cn_note_type")
    List<NoteType> findAll();
}
