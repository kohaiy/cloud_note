package cn.gqxqd.service;

import cn.gqxqd.util.NoteResult;

public interface NoteBookService {

    NoteResult findAllNoteBook(String id);

    NoteResult createNoteBook(String id, String bookName, String bookDesc);

    NoteResult updateNoteBook(String id, String bookId, String bookName, String bookDesc);

    NoteResult deleteNoteBook(String id, String bookId);
}
