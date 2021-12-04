package com.springbootguestbook.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public class PageResultDTO<DTO, EN> {

    // DTO 객체들을 보관하기 위한 List<DTO>
    private List<DTO> dtoList;

    // Page<EN> 타입을 생성자로 지정하고 Function<EN, DTO> 은 엔티티 객체들을 DTO 로 변환시키는 기능
    public PageResultDTO(Page<EN> result, Function<EN, DTO> fn) {

        dtoList = result.stream().map(fn).collect(Collectors.toList());

    }
}
