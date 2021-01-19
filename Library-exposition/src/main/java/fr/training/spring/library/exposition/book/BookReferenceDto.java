package fr.training.spring.library.exposition.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.training.spring.library.domain.book.Genre;


public class BookReferenceDto {
    @JsonProperty
    public  String isbn;
    @JsonProperty
    public Genre genre;

    public BookReferenceDto(String isbn, Genre genre) {
        this.isbn = isbn;
        this.genre = genre;
    }

    public String getIsbn() {
        return isbn;
    }

    public Genre getGenre() {
        return genre;
    }

    public BookReferenceDto() {

    }
}
