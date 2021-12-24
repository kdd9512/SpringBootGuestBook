package com.springbootguestbook.service;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.springbootguestbook.dto.GuestBookDTO;
import com.springbootguestbook.dto.PageRequestDTO;
import com.springbootguestbook.dto.PageResultDTO;
import com.springbootguestbook.entity.GuestBook;
import com.springbootguestbook.entity.QGuestBook;
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

        BooleanBuilder booleanBuilder = getSearch(requestDTO); // 검색조건 처리.

        Page<GuestBook> result = repository.findAll(pageable);

        Function<GuestBook, GuestBookDTO> fn = (entity -> entityToDTO(entity));

        return new PageResultDTO<>(result,fn);
    }


    @Override
    public GuestBookDTO read(Long gno) {

        Optional<GuestBook> result = repository.findById(gno);
        return result.isPresent() ? entityToDTO(result.get()) : null;
    }

    @Override
    public void remove(Long gno) {
        repository.deleteById(gno);
    }

    @Override
    public void modify(GuestBookDTO dto) {
        Optional<GuestBook> result = repository.findById(dto.getGno());

        if (result.isPresent()) {
            GuestBook entity = result.get();

            entity.changeTitle(dto.getTitle());
            entity.changeContent(dto.getContent());

            repository.save(entity);
        }
    }

    private BooleanBuilder getSearch(PageRequestDTO dto) {
        // Querydsl 처리
        String type = dto.getType();
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        QGuestBook qGuestBook = QGuestBook.guestBook;

        String keyword = dto.getKeyword();

        // gno 가 0 보다 큰 조건만 생성한다.
        BooleanExpression expression = qGuestBook.gno.gt(0L);

        booleanBuilder.and(expression);

        // null 이거나 0이 들어왔을 때(= 검색 조건이 없다) 예외처리.
        if (type == null || type.trim().length() == 0) {
            return booleanBuilder;
        }

        // 이하 검색조건.
        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if (type.contains("t")) {
            conditionBuilder.or(qGuestBook.title.contains(keyword));
        }
        if (type.contains("c")) {
            conditionBuilder.or(qGuestBook.content.contains(keyword));
        }
        if (type.contains("w")) {
            conditionBuilder.or(qGuestBook.writer.contains(keyword));
        }


        // 조건 통합
        booleanBuilder.and(conditionBuilder);

        return booleanBuilder;
    }

}