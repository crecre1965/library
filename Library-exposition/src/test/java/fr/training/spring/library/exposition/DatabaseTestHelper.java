package fr.training.spring.library.exposition;

import fr.training.spring.library.domain.book.Book;
import fr.training.spring.library.domain.book.Genre;
import fr.training.spring.library.domain.library.*;

import fr.training.spring.library.infrastructure.library.LibraryDAO;
import fr.training.spring.library.infrastructure.library.LibraryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Component
@Transactional
public class DatabaseTestHelper {

    @Autowired
    LibraryDAO libraryDAO;

    public static final Book DONQUIXOTE = new Book("843762214X", "Don Quixote", "Miguel de Cervantes", 200,
            Genre.HISTORIQUE);
    public static final Book ATALEOFTWOCITIES = new Book("1657599086", "A Tale of Two Cities", "Charles Dickens", 300,
            Genre.POLICIER);
    public static final Book LORDOFTHERINGS = new Book("9780261102385", "The Lord of the Rings", "J.R.R. Tolkien", 500,
            Genre.AVENTURE);
    public static final Book HARRYPOTTER1 = new Book("9781408855652", "Harry Potter and the Sorcerer’s Stone", "J.K. Rowling", 200,
            Genre.HISTORIQUE);
    public static final Book DAVINCICODE = new Book("052556585X", "The Da Vinci Code", "Dan Brown", 300, Genre.HISTORIQUE);
    public static final Book ILIUM = new Book("9780380817924", "Ilium", "Dan Simmons", 600, Genre.AVENTURE);

    public static final Book SPRING_IN_ACTION = new Book("1617294942", "Spring in Action", "Craig Walls", 310,
            Genre.POLICIER);

    public static final Library NATIONAL_LIBRARY_MONTREUIL = new Library(Type.ASSOCIATIVE,
            new Address(1, "Rue de Montreuil1", 93101, "Montreuil"),
            new Director("Romain", "NOEL"),
            Arrays.asList());
    public static final Library NATIONAL_LIBRARY_MONTREUIL2 = new Library(Type.NATIONALE,
            new Address(2, "Rue de Montreuil2", 93102, "Montreuil2"), new Director("Garfield", "LECHAT1"),
            Arrays.asList(DONQUIXOTE, ATALEOFTWOCITIES));
    public static final Library SCHOOL_LIBRARY_PARIS = new Library(Type.ASSOCIATIVE,
            new Address(3, "Rue de Paris1", 75001, "Paris1"), new Director("Romain", "NOEL"), Arrays.asList());
    public static final Library SCHOOL_LIBRARY_PARIS2 = new Library(Type.ASSOCIATIVE,
            new Address(4, "Rue de Paris2", 75002, "Paris2"), new Director("Garfield", "LECHAT2"),
            Arrays.asList(LORDOFTHERINGS, HARRYPOTTER1));
    public static final Library PUBLIC_LIBRARY_VINCENNES = new Library(Type.NATIONALE,
            new Address(5, "Rue de Vincennes", 94200, "Vincennes"), new Director("Garfield", "LECHAT3"),
            Arrays.asList(DAVINCICODE, ILIUM, LORDOFTHERINGS));

    public static final Library DUMMY_LIBRARY = new Library(null, new Address(0, "DUMMY_STREET", 0, "DUMMY_CITY"),
            new Director("DUMMY_NAME", "DUMMY_SURNAME"), Arrays.asList(SPRING_IN_ACTION));

    public static final LibraryJpa NATIONAL_LIBRARY_MONTREUIL_JPA = new LibraryJpa(NATIONAL_LIBRARY_MONTREUIL);
    public static final LibraryJpa NATIONAL_LIBRARY_MONTREUIL2_JPA = new LibraryJpa(NATIONAL_LIBRARY_MONTREUIL2);
    public static final LibraryJpa SCHOOL_LIBRARY_PARIS_JPA= new LibraryJpa(SCHOOL_LIBRARY_PARIS);
    public static final LibraryJpa SCHOOL_LIBRARY_PARIS2_JPA = new LibraryJpa(SCHOOL_LIBRARY_PARIS2);
    public static final LibraryJpa PUBLIC_LIBRARY_VINCENNES_JPA = new LibraryJpa(PUBLIC_LIBRARY_VINCENNES);
    public static final LibraryJpa DUMMY_LIBRARY_JPA = new LibraryJpa(DUMMY_LIBRARY);


    public void setup() {


        libraryDAO.deleteAll();
        libraryDAO.save(NATIONAL_LIBRARY_MONTREUIL_JPA);
        libraryDAO.save(NATIONAL_LIBRARY_MONTREUIL2_JPA);
        libraryDAO.save(SCHOOL_LIBRARY_PARIS_JPA);
        libraryDAO.save(SCHOOL_LIBRARY_PARIS2_JPA);
        libraryDAO.save(PUBLIC_LIBRARY_VINCENNES_JPA);
        libraryDAO.flush();
    }

    public void tearDown() {
        libraryDAO.deleteAll();
        libraryDAO.flush();
    }
    public void delelteAll(){
        libraryDAO.deleteAll();
    }

    public LibraryJpa createDummyLibrary() {
        return libraryDAO.saveAndFlush(DUMMY_LIBRARY_JPA);
    }

}
