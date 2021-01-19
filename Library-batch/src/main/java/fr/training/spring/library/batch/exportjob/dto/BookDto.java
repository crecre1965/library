package fr.training.spring.library.batch.exportjob.dto;


import fr.training.spring.library.domain.book.Genre;

public class BookDto {

    private long id;

    private String isbn;

    private String title;

    private String author;

    private int numberOfPages;

    private Genre genre;

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

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("BookDto [id=").append(id) //
                .append(", isbn =").append(isbn) //
                .append(",title =").append(title) //
                .append(",author=").append(author) //
                .append(",numberOfPages =").append(numberOfPages) //
                .append(",genre =").append(genre) //
                .append("]");
        return builder.toString();
    }
}
