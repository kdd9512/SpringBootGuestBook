package com.springbootguestbook.repository;

import com.springbootguestbook.entity.GuestBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface GuestBookRepository extends JpaRepository<GuestBook,
        Long>, QuerydslPredicateExecutor<GuestBook> {

}
