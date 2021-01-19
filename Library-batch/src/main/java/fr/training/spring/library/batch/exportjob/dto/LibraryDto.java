package fr.training.spring.library.batch.exportjob.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import fr.training.spring.library.domain.library.Type;

import java.util.List;

public class LibraryDto {

    @JsonProperty
    private long id;

    @JsonProperty
    private Type type;

    @JsonProperty
    private AddressDto address;

    @JsonProperty
    private DirectorDto director;

    @JsonProperty("Books")
    private List<BookDto> books;


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

    @Override
    public String toString() {
        StringBuilder builder=new StringBuilder();
        builder.append("LibraryDto [id=").append(id) //
        .append(",type=").append(type) //
        .append(",address=").append(address.toString()) //
        .append("director=").append(director.toString()) //
        .append("books=").append(books.toString()) //
        .append("]");
        return builder.toString();
    }
}
