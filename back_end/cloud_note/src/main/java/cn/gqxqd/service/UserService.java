package cn.gqxqd.service;

import cn.gqxqd.util.NoteResult;

public interface UserService {
    NoteResult get(int id);

    NoteResult findAllNoteType();

    NoteResult register(String name, String password);

    NoteResult checkUsername(String username);

    NoteResult login(String name, String password);

    NoteResult validator(String id, String token);

    NoteResult updatePassword(String id, String oldPassword, String newPassword);
}
