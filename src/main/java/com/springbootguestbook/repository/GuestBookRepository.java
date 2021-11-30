package com.springbootguestbook.repository;

import com.springbootguestbook.entity.GuestBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestBookRepository extends JpaRepository<GuestBook, Long> {

}
