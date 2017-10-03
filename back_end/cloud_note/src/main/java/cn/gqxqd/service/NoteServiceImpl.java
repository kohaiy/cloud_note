package cn.gqxqd.service;

import cn.gqxqd.dao.NoteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteDao noteDao;
}
