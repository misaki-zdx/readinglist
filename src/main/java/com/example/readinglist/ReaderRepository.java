package com.example.readinglist;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Misaki
 * @date 2019/2/19/019 17:02
 **/
public interface ReaderRepository extends JpaRepository<Reader,String> {
}
