package fr.training.spring.library.exposition.book;

import fr.training.spring.library.domain.book.Book;
import fr.training.spring.library.exposition.common.AbstractAdapter;
import org.springframework.stereotype.Component;

@Component
public class BookAdapter extends AbstractAdapter<BookDto, Book> {
    @Override
    public BookDto mapToDto(Book entity) {
        final BookDto bookDto = new BookDto();
        bookDto.setAuthor(entity.getAuthor());
        bookDto.setGenre(entity.getGenre());
        bookDto.setId(entity.getId());
        bookDto.setTitle(entity.getTitle());
        bookDto.setIsbn(entity.getIsbn());
        bookDto.setNumberOfPages(entity.getNumberOfPages());
        return bookDto;
    }

    @Override
    public Book mapToEntity(BookDto dto) {
        final Book book=new Book();
        book.setId(dto.getId());
        book.setAuthor(dto.getAuthor());
        book.setGenre(dto.getGenre());
        book.setIsbn(dto.getIsbn());
        book.setNumberOfPages(dto.getNumberOfPages());
        book.setTitle(dto.getTitle());

        return book;
    }
}
