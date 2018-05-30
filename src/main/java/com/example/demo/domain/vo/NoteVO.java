package com.example.demo.domain.vo;

import com.example.demo.domain.po.Note;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

@Data
public class NoteVO {

    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private Long createAt;

    public Note covertToNote() {
        NoteVOCovert noteVOCovert = new NoteVOCovert();
        return noteVOCovert.doForward(this);
    }

    public static NoteVO converFor(Note note) {
        NoteVOCovert noteVOCovert = new NoteVOCovert();
        return noteVOCovert.reverse().convert(note);
    }

    private static class NoteVOCovert extends Converter<NoteVO, Note> {

        @Override
        protected Note doForward(NoteVO noteVO) {
            Note note = new Note();
            BeanUtils.copyProperties(noteVO, note);
            return note;
        }

        @Override
        protected NoteVO doBackward(Note note) {
            NoteVO noteVO = new NoteVO();
            BeanUtils.copyProperties(note, noteVO);
            noteVO.setCreateAt(note.getCreateAt().getTime());
            return noteVO;
        }
    }

}
