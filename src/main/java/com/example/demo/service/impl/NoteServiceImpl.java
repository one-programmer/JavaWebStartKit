package com.example.demo.service.impl;

import com.example.demo.common.exception.DemoErrorEnum;
import com.example.demo.common.exception.DemoException;
import com.example.demo.domain.po.Note;
import com.example.demo.domain.vo.NoteVO;
import com.example.demo.repository.NoteRepository;
import com.example.demo.service.NoteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    NoteRepository noteRepository;

    @Override
    public List<NoteVO> listAllNotes() {
        List<Note> noteList = noteRepository.findAllByOrderByCreateAtDesc();
        List<NoteVO> noteVOList = new ArrayList<>();

        for (Note note : noteList) {
            noteVOList.add(NoteVO.converFor(note));
        }
        return noteVOList;
    }

    @Override
    public NoteVO createNote(NoteVO noteVO) {
        Note note = noteRepository.save(noteVO.covertToNote());
        BeanUtils.copyProperties(note, noteVO);
        return NoteVO.converFor(note);
    }

    @Override
    public NoteVO getNoteById(Long noteId) {
        Note note = noteRepository.findById(noteId).orElseThrow(() -> new DemoException("Note 不存在", DemoErrorEnum.TARGET_NOT_EXISTS));
        return NoteVO.converFor(note);
    }

    @Override
    public NoteVO updateNote(Long noteId, NoteVO noteVO) {
        Note note = noteRepository.findById(noteId).orElseThrow(() -> new DemoException("Note 不存在", DemoErrorEnum.TARGET_NOT_EXISTS));
        note.setTitle(noteVO.getTitle());
        note.setContent(noteVO.getContent());

        Note updatedNote = noteRepository.save(note);
        return NoteVO.converFor(updatedNote);
    }

    @Override
    public boolean deleteNote(Long noteId) {
        Note note = noteRepository.findById(noteId).orElseThrow(() -> new DemoException("Note 不存在", DemoErrorEnum.TARGET_NOT_EXISTS));

        noteRepository.delete(note);
        return true;
    }
}
