package fr.training.spring.library.exposition.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.training.spring.library.domain.book.Genre;



public class BookDto {

  @JsonProperty("identifiant du livre / book id")
    private long id;

    @JsonProperty("isbn du livre / book isbn")
    private String isbn;

    @JsonProperty("titre du livre / book tittle")
    private String title;

    @JsonProperty("auteur du livre / book author")
    private String author;

    @JsonProperty("nombre de pages / number of pages")
    private int numberOfPages;

    @JsonProperty("genre du livre / book literay genre ")
    private Genre genre;

    public BookDto() {
    }

    public BookDto(long id, String isbn, String title, String author, int numberOfPages, Genre genre) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.numberOfPages = numberOfPages;
        this.genre = genre;
    }
    public BookDto(String isbn, String title, String author, int numberOfPages, Genre genre) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.numberOfPages = numberOfPages;
        this.genre = genre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
