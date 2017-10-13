package cn.gqxqd.service;

import cn.gqxqd.util.NoteResult;

public interface NoteService {

    NoteResult findAllByUserId(String id);
}
