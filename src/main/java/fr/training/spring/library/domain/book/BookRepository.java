package fr.training.spring.library.domain.book;

import fr.training.spring.library.domain.book.Book;
import fr.training.spring.library.domain.ddd.DDD;

@DDD.Repository
public interface BookRepository {
    public Book search(final String isbn);

}
