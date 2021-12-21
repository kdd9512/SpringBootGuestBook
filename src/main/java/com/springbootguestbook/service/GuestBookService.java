package com.springbootguestbook.service;

import com.springbootguestbook.dto.GuestBookDTO;
import com.springbootguestbook.dto.PageRequestDTO;
import com.springbootguestbook.dto.PageResultDTO;
import com.springbootguestbook.entity.GuestBook;

public interface GuestBookService {

    Long register(GuestBookDTO dto);

    GuestBookDTO read(Long gno);
    void remove(Long gno);
    void modify(GuestBookDTO dto);

    PageResultDTO<GuestBookDTO, GuestBook> getList(PageRequestDTO pageRequestDTO);

    default GuestBook dtoToEntity (GuestBookDTO dto) {
        GuestBook entity = GuestBook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();

        return entity;
    }

    default GuestBookDTO entityToDTO (GuestBook entity) {

        GuestBookDTO dto = GuestBookDTO.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();

        return dto;
    }

}
