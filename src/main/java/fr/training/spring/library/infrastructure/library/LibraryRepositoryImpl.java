package fr.training.spring.library.infrastructure.library;

import fr.training.spring.library.domain.ddd.DDD;
import fr.training.spring.library.domain.exception.ErrorCodes;
import fr.training.spring.library.domain.exception.NotFoundException;
import fr.training.spring.library.domain.library.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@DDD.RepositoryImpl
@Repository
public class LibraryRepositoryImpl implements LibraryRepository {

    @Autowired
    LibraryDAO libraryDAO;

    @Override
    public Library save(Library library) {
        LibraryJpa libraryJpa=libraryDAO.save(new LibraryJpa(library));
        return libraryJpa.toLibrary();
    }

    @Override
    public Library delete(Library library) {
        libraryDAO.delete(new LibraryJpa(library));
        return library;
  //      return mapToDomain(libraryJpaRepository.delete(libraryJpa));

    }

    @Override
    public Library findById(Long id) {
        return libraryDAO.findById(id).map(LibraryJpa::toLibrary).orElseThrow(() -> new NotFoundException("librairie non trouvée !!! id : " + id + " / ", ErrorCodes.LIBRARY_NOT_FOUND));

    }

    @Override
    public List<Library> findAll() {
        return libraryDAO.findAll().stream().map(LibraryJpa::toLibrary).collect(Collectors.toList());
    }

    @Override
    public List<Library> findAllByDirector_Firstname(String firstname) {
        return libraryDAO.findAllByDirectorFirstname(firstname);
    }

    @Override
    public List<Library> findAllByType(Type type) {
        return libraryDAO.findAllByType(type);


    }

//    private Library mapToDomain(LibraryJpa libraryJpa) {
//        final Library library = new Library();
//
//        Address address = new Address();
//        address.setZipcode(libraryJpa.getAddressZipcode());
//        address.setStreet(libraryJpa.getAddressStreet());
//        address.setNumber(libraryJpa.getAddressNumber());
//        address.setCity(libraryJpa.getAddressCity());
//
//        Director director = new Director();
//        director.setLastname(libraryJpa.getDirectorLastname());
//        director.setFirstname(libraryJpa.getDirectorFirstname());
//
//        List<Book> books = new ArrayList<>();
//        for (BookJpa bookJpa : libraryJpa.getBooks()) {
//            Book book = new Book();
//            book.setTitle(bookJpa.getTitle());
//            book.setIsbn(bookJpa.getIsbn());
//            book.setGenre(bookJpa.getGenre());
//            book.setAuthor(bookJpa.getAuthor());
//            book.setNumberOfPages(bookJpa.getNumberOfPages());
//            book.setId(bookJpa.getId());
//            books.add(book);
//        }
//
//        library.setType(libraryJpa.getType());
//        library.setId(libraryJpa.getId());
//        library.setAddress(address);
//        library.setDirector(director);
//        library.setBooks(books);
//
//        return library;
//
//    }
//
//    private LibraryJpa mapToInfra(Library library) {
//        final LibraryJpa libraryJpa = new LibraryJpa();
//
//        List<BookJpa> bookJpas = new ArrayList<>();
//        for (Book book : library.getBooks()) {
//            BookJpa bookJpa = new BookJpa();
//            bookJpa.setTitle(book.getTitle());
//            bookJpa.setIsbn(book.getIsbn());
//            bookJpa.setGenre(book.getGenre());
//            bookJpa.setAuthor(book.getAuthor());
//            bookJpa.setNumberOfPages(book.getNumberOfPages());
//            bookJpa.setId(book.getId());
//            bookJpas.add(bookJpa);
//        }
//        libraryJpa.setBooks(bookJpas);
//        libraryJpa.setAddressCity(library.getAddress().getCity());
//        libraryJpa.setAddressNumber(library.getAddress().getNumber());
//        libraryJpa.setAddressStreet(library.getAddress().getStreet());
//        libraryJpa.setAddressZipcode(library.getAddress().getZipcode());
//        libraryJpa.setDirectorFirstname(library.getDirector().getFirstname());
//        libraryJpa.setDirectorLastname(libraryJpa.getDirectorLastname());
//        libraryJpa.setType(libraryJpa.getType());
//        libraryJpa.setId(library.getId());
//
//        return libraryJpa;
//
//    }
}
