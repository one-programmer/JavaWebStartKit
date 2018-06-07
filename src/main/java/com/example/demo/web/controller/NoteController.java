package com.example.demo.web.controller;

import com.example.demo.common.exception.DemoErrorEnum;
import com.example.demo.common.exception.DemoException;
import com.example.demo.domain.vo.NoteVO;
import com.example.demo.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping("/hello")
    public String hello() {
        return "hello world!";
    }

    @GetMapping("/notes")
    @PreAuthorize("hasRole('USER') or hasRole('TEST')")
    public List<NoteVO> getAllNotes() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return noteService.listAllNotes();
    }

    @PostMapping("/notes")
    public NoteVO createNote(@Valid @RequestBody NoteVO noteVO) {
        return noteService.createNote(noteVO);
    }

    @GetMapping("/notes/{id}")
    public NoteVO getNoteById(@PathVariable(value = "id") Long noteId) {
        return noteService.getNoteById(noteId);
    }

    @PutMapping("/notes/{id}")
    public NoteVO updateNote(@PathVariable(value = "id") Long noteId,
                             @Valid @RequestBody NoteVO noteVO) {
        return noteService.updateNote(noteId, noteVO);
    }

    @DeleteMapping("/notes/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public boolean deleteNote(@PathVariable(value = "id") Long noteId) {
        return noteService.deleteNote(noteId);
    }

    @GetMapping("/test_error")
    public void testError() {
        throw new DemoException("自定义错误消息", DemoErrorEnum.UNKOWN);
    }

}
