package fr.training.spring.library.application;

import fr.training.spring.library.domain.book.Book;
import fr.training.spring.library.domain.book.BookRepository;
import fr.training.spring.library.domain.common.ddd.DDD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@DDD.ApplicationService
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book searchBookByISBN(final String isbn){
        return bookRepository.search(isbn);
    }
}
