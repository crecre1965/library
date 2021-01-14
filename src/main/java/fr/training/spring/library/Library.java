package fr.training.spring.library;

import javax.persistence.*;

@Entity
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LIBRARY_ID")
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "LIBRARY_TYPE")
    private Type type;

    @Embedded
    private Address address;

    @Embedded
    private Director director;

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

    public void update(Library libraryToUpdate) {
        address= libraryToUpdate.getAddress();
        director= libraryToUpdate.getDirector();
        type=libraryToUpdate.getType();
    }

    public Library(long id, Type type, Address address, Director director) {
        this.id = id;
        this.type = type;
        this.address = address;
        this.director = director;
    }
}
