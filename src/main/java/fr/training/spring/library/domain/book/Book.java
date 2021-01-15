package fr.training.spring.library.domain.book;



public class Book {

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

    public Book() {
    }

    public Book(String isbn, String title, String author, int numberOfPages, Genre genre) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.numberOfPages = numberOfPages;
        this.genre = genre;
    }

    public Book(long id, String isbn, String title, String author, int numberOfPages, Genre genre) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.numberOfPages = numberOfPages;
        this.genre = genre;
    }
}
