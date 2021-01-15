package fr.training.spring.library.domain.library;

import fr.training.spring.library.domain.book.Book;



import java.util.List;

public class Library {
    private long id;

    private Type type;

    private Address address;

    private Director director;

    private List<Book> books;

    public Library() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }


    public void update(Library libraryToUpdate) {
        address = libraryToUpdate.getAddress();
        director = libraryToUpdate.getDirector();
        type = libraryToUpdate.getType();
    }

    public Library(long id, Type type, Address address, Director director) {
        this.id = id;
        this.type = type;
        this.address = address;
        this.director = director;
    }

    public Library(long id, Type type, Address address, Director director, List<Book> books) {
        this.id = id;
        this.type = type;
        this.address = address;
        this.director = director;
        this.books = books;
    }

    public Library(Type type, Address address, Director director, List<Book> books) {
        this.type = type;
        this.address = address;
        this.director = director;
        this.books = books;
    }
}
