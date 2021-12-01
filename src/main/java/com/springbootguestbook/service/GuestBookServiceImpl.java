package com.springbootguestbook.service;

import com.springbootguestbook.dto.GuestBookDTO;
import com.springbootguestbook.entity.GuestBook;
import com.springbootguestbook.repository.GuestBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service

// 동작을 확인하기 위해 적용하는 annotation
@Log4j2

// 의존성 자동주입.
@RequiredArgsConstructor
public class GuestBookServiceImpl implements GuestBookService {

    private final GuestBookRepository repository;

    @Override
    public Long register(GuestBookDTO dto) {

        log.info("DTO...");
        log.info(dto);

        GuestBook entity = dtoToEntity(dto);

        log.info(entity);

        repository.save(entity);

        return entity.getGno();

    }
}
