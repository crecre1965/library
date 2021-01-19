package fr.training.spring.library.batch.exportjob;

import fr.training.spring.library.application.LibraryService;
import fr.training.spring.library.batch.exportjob.dto.AddressDto;
import fr.training.spring.library.batch.exportjob.dto.BookDto;
import fr.training.spring.library.batch.exportjob.dto.DirectorDto;
import fr.training.spring.library.batch.exportjob.dto.LibraryDto;
import fr.training.spring.library.domain.book.Book;
import fr.training.spring.library.domain.library.Library;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@StepScope
public class LibraryProcessor implements ItemProcessor<Long, LibraryDto> {
    private static final Logger logger = LoggerFactory.getLogger(LibraryProcessor.class);

    @Autowired
    private LibraryService libraryService;

    @Override
    public LibraryDto process(Long id) throws Exception {

        final LibraryDto libraryDto = new LibraryDto();
        final DirectorDto directorDto = new DirectorDto();
        final AddressDto addressDto = new AddressDto();


        final Library library = libraryService.find(id);
        logger.info("Processing library {}", library);

        addressDto.setCity(library.getAddress().getCity());
        addressDto.setNumber(library.getAddress().getNumber());
        addressDto.setZipcode(library.getAddress().getZipcode());
        addressDto.setStreet(library.getAddress().getStreet());

        directorDto.setLastname(library.getDirector().getLastname());
        directorDto.setFirstname(library.getDirector().getFirstname());

        List<BookDto> bookDtos = new ArrayList<>();
        List<Book> books = new ArrayList<>();
        books = library.getBooks();

        for (Book book : books) {
            final BookDto bookDto = new BookDto();
            bookDto.setAuthor(book.getAuthor());
            bookDto.setGenre(book.getGenre());
            bookDto.setId(book.getId());
            bookDto.setIsbn(book.getIsbn());
            bookDto.setTitle(book.getTitle());
            bookDto.setNumberOfPages(book.getNumberOfPages());
            bookDtos.add(bookDto);
        }

        libraryDto.setDirector(directorDto);
        libraryDto.setAddress(addressDto);
        libraryDto.setId((library.getId()));
        libraryDto.setType(library.getType());
        libraryDto.setBooks(bookDtos);

        return libraryDto;
    }


}

