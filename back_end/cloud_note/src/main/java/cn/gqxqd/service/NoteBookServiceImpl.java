package cn.gqxqd.service;

import cn.gqxqd.dao.NoteBookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteBookServiceImpl implements NoteBookService {

    @Autowired
    private NoteBookDao noteBookDao;
}
