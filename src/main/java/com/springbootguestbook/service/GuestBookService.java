package com.springbootguestbook.service;

import com.springbootguestbook.dto.GuestBookDTO;
import com.springbootguestbook.entity.GuestBook;

public interface GuestBookService {

    Long register(GuestBookDTO dto);

    default GuestBook dtoToEntity (GuestBookDTO dto) {
        GuestBook entity = GuestBook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();

        return entity;
    }
}
