package com.springbootguestbook.service;


import com.springbootguestbook.dto.GuestBookDTO;
import com.springbootguestbook.dto.PageRequestDTO;
import com.springbootguestbook.dto.PageResultDTO;
import com.springbootguestbook.entity.GuestBook;
import com.springbootguestbook.repository.GuestBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2 // 동작을 확인하기 위해 적용하는 annotation (log.info();)
@RequiredArgsConstructor // 의존성 자동주입.
public class GuestBookServiceImpl implements GuestBookService {

    private final GuestBookRepository repository; // 반드시 final 로 선언.

    @Override
    public Long register(GuestBookDTO dto) {
        log.info("DTO......");
        log.info(dto);

        GuestBook entity = dtoToEntity(dto);

        log.info(entity);

        repository.save(entity);

        return entity.getGno();
    }

    @Override
    public PageResultDTO<GuestBookDTO, GuestBook> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());

        Page<GuestBook> result = repository.findAll(pageable);

        Function<GuestBook, GuestBookDTO> fn = (entity -> entityToDTO(entity));

        return new PageResultDTO<>(result,fn);
    }


    @Override
    public GuestBookDTO read(Long gno) {

        Optional<GuestBook> result = repository.findById(gno);
        return result.isPresent() ? entityToDTO(result.get()) : null;
    }

}