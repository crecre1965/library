package fr.training.spring.library.infrastructure.book;

import fr.training.spring.library.domain.book.Book;
import fr.training.spring.library.domain.book.Genre;

import javax.persistence.*;
@Entity
@Table(name="BOOK")
public class BookJpa {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="ID")
        private long id;

        @Column(name="ISBN")
        private String isbn;

        @Column(name="TITLE")
        private String title;

        @Column(name="AUTHOR")
        private String author;

        @Column(name="NUMBER_OF_PAGES")
        private int numberOfPages;

        @Enumerated(EnumType.STRING)
        @Column(name="GENRE")
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

        public BookJpa() {
        }

        public BookJpa(Book book) {
                this.id = book.getId();
                this.isbn = book.getIsbn();
                this.title = book.getTitle();
                this.author = book.getAuthor();
                this.numberOfPages = book.getNumberOfPages();
                this.genre = book.getGenre();
        }
}
