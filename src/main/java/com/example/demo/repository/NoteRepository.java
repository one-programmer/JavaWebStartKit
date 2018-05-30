package com.example.demo.repository;

import com.example.demo.domain.po.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    public List<Note> findAllByOrderByCreateAtDesc();

}
