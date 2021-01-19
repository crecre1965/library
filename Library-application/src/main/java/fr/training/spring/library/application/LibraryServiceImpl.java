package fr.training.spring.library.application;


import fr.training.spring.library.domain.book.Book;
import fr.training.spring.library.domain.book.BookRepository;
import fr.training.spring.library.domain.book.Genre;
import fr.training.spring.library.domain.common.ddd.DDD;
import fr.training.spring.library.domain.library.LibraryRepository;
import fr.training.spring.library.domain.library.Type;
import fr.training.spring.library.domain.library.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@DDD.ApplicationService
@Service
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    LibraryRepository libraryRepository;
    @Autowired
    BookRepository bookRepository;

    @Transactional
    @Override
    public Library create(Library library) {

        return libraryRepository.save(library);
    }

    @Override
    public Library find(Long id) {
        return libraryRepository.findById(id);

    }

    @Transactional
    @Override
    public Library update(long id, Library libraryToUpdate) {
        Library library = find(id);
        library.update(libraryToUpdate);
        return libraryRepository.save(library);
    }

    @Transactional
    @Override
    public Library addBook(long id, String isbn, Genre genre) {
        Book book = bookRepository.search(isbn);
        book.assignGenre(genre);
        Library library = find(id);
        List<Book> bookList = library.getBooks();
        bookList.add(book);
        library.setBooks(bookList);
        return libraryRepository.save(library);
    }

    @Transactional
    @Override
    public List<Library> searchAllLibraries() {
        List<Library> libraries = new ArrayList<>();
        libraries = (List<Library>) libraryRepository.findAll();
        return libraries;
    }

    @Override
    public List<Library> searchAllLibraries(String firstname) {
        List<Library> libraries = new ArrayList<>();
        libraries = (List<Library>) libraryRepository.findAllByDirector_Firstname(firstname);
        return libraries;
    }

    @Override
    public List<Library> searchAllLibraries(Type type) {
        List<Library> libraries = new ArrayList<>();
        libraries = (List<Library>) libraryRepository.findAllByType(type);
        return libraries;
    }

    @Transactional
    @Override
    public Library delete(long id) {
        Library library = find(id);
        libraryRepository.delete(library);
        return library;
    }
}
