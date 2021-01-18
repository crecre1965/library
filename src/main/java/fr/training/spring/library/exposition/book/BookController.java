package fr.training.spring.library.exposition.book;


import fr.training.spring.library.application.BookService;
import fr.training.spring.library.domain.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    @ResponseStatus(HttpStatus.OK)
    public BookDto searchBookByISBN(String isbn) {
        final Book book = bookService.searchBookByISBN(isbn);
        return new BookDto(isbn, book.getTitle(), book.getAuthor(), book.getNumberOfPages(), book.getGenre());
    }


}
