package com.example.readinglist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Misaki
 * @date 2019/2/19/019 11:18
 **/
public interface ReadingListRepository extends JpaRepository<Book,Long> {

    List<Book> findByReader(String reader);

}
