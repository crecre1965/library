package fr.training.spring.library.infrastructure.http;

import fr.training.spring.library.domain.book.Book;
import fr.training.spring.library.domain.book.BookRepository;
import fr.training.spring.library.domain.ddd.DDD;
import fr.training.spring.library.domain.exception.ErrorCodes;
import fr.training.spring.library.domain.exception.NotFoundException;
import fr.training.spring.library.domain.exception.OpenLibraryTechnicalException;
import fr.training.spring.library.infrastructure.http.dto.AuthorInfo;
import fr.training.spring.library.infrastructure.http.dto.BookInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@DDD.RepositoryImpl
@Component
public class BookExternalRepositoryImpl implements BookRepository {

    private static final Logger logger = LoggerFactory.getLogger(BookExternalRepositoryImpl.class);

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public Book search(String isbn) {
        List<String> authorkeys = new ArrayList<>();
        try {
            final ResponseEntity<BookInfo> response = restTemplate.getForEntity("/isbn/" + isbn + ".json", BookInfo.class);

            BookInfo bookInfo = response.getBody();
            logger.debug((bookInfo.toString()));
            final String authorName = searchAuthor(bookInfo.getAuthors().get(0).getKey());
            return new Book(0, isbn, bookInfo.getTitle(), authorName, bookInfo.getNumber_of_pages(), null);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            if (HttpStatus.NOT_FOUND.equals(e.getStatusCode())) {
                throw new NotFoundException("Book " + isbn + "Not found", ErrorCodes.BOOK_NOT_FOUND);
            }
            throw new OpenLibraryTechnicalException(e);
        }
    }

    //        //---> recherche auteur
    private String searchAuthor(String authorKey) {
        String authorName = "unknown";
        if (!authorKey.isEmpty()) {
            try {
                ResponseEntity<AuthorInfo> response = restTemplate.getForEntity(authorKey + ".json", AuthorInfo.class);
                AuthorInfo authorInfo = response.getBody();
                authorName = authorInfo.getName();
            } catch (final RestClientException e) {
                logger.error("error on author call for " + authorKey);
            }

        }
        return authorName;
    }
}
