package org.factoriaf5.app.repositories;

import org.factoriaf5.app.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {}
