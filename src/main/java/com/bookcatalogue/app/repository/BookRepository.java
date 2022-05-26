package com.bookcatalogue.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookcatalogue.app.dto.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{

	

}
