package com.springbootguestbook.sevice;

import com.springbootguestbook.dto.GuestBookDTO;

public interface GuestBookService {
    Long register(GuestBookDTO dto);
}
