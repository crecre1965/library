package fr.training.spring.library.exposition.library;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.training.spring.library.domain.library.Type;
import fr.training.spring.library.exposition.address.AddressDto;
import fr.training.spring.library.exposition.book.BookDto;
import fr.training.spring.library.exposition.director.DirectorDto;

import java.util.List;

public class LibraryDto {
   @JsonProperty("identifiant de la bibliothèque / library id ")
    private long id;

    @JsonProperty("type de bibliothèque / library type ")
    private Type type;

    @JsonProperty("adresse de la bibliothèque / library address")
    private AddressDto address;

    @JsonProperty("directeur de la bibliothèque / library director ")
    private DirectorDto director;

    @JsonProperty("livres de la bliothèque / library books ")
    private List<BookDto> books;

    public LibraryDto() {
        }

    public LibraryDto(long id, Type type, AddressDto address, DirectorDto director, List<BookDto> books) {
        this.id = id;
        this.type = type;
        this.address = address;
        this.director = director;
        this.books = books;
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

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public DirectorDto getDirector() {
        return director;
    }

    public void setDirector(DirectorDto director) {
        this.director = director;
    }

    public List<BookDto> getBooks() {
        return books;
    }

    public void setBooks(List<BookDto> books) {
        this.books = books;
    }
}
