package com.example.demo.service;

import com.example.demo.domain.vo.NoteVO;

import java.util.List;

public interface NoteService {

    List<NoteVO> listAllNotes();

    NoteVO createNote(NoteVO noteVO);

    NoteVO getNoteById(Long noteId);

    NoteVO updateNote(Long noteId, NoteVO noteVO);

    boolean deleteNote(Long noteId);
}
