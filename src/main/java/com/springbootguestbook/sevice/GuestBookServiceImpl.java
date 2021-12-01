package com.springbootguestbook.sevice;

import com.springbootguestbook.dto.GuestBookDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
// 동작을 확인하기 위해 적용하는 annotation
@Log4j2
public class GuestBookServiceImpl  implements GuestBookService {

    @Override
    public Long register(GuestBookDTO dto) {
        return null;
    }
}
