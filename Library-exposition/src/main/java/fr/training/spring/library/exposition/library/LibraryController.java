package fr.training.spring.library.exposition.library;

import fr.training.spring.library.domain.library.Type;
import fr.training.spring.library.application.LibraryService;
import fr.training.spring.library.domain.library.Library;
import fr.training.spring.library.exposition.book.BookReferenceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryController {

    @Autowired
    LibraryService libraryService;

    @Autowired
    LibraryAdapter libraryAdapter;

    @PostMapping("/creer")
    @ResponseStatus(HttpStatus.CREATED)
    public LibraryDto creer(@Valid @RequestBody final LibraryDto libraryDto) {
        final Library library = libraryAdapter.mapToEntity(libraryDto);
        return libraryAdapter.mapToDto(libraryService.create(library));

    }

//    @GetMapping("/afficher")
//    @ResponseStatus(HttpStatus.OK)
//    public LibraryDto afficher(@PathVariable final Long id) {
//        return libraryAdapter.mapToDto(libraryService.find(id));
//    }

    @GetMapping("/lister-all")
    @ResponseStatus(HttpStatus.OK)
    public List<LibraryDto> lister() {
        List<Library> libraries = libraryService.searchAllLibraries();
        return libraryAdapter.mapToDtoList(libraries);

    }

    @GetMapping("/lister-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LibraryDto listerById(@PathVariable long id) {
        return libraryAdapter.mapToDto(libraryService.find(id));
    }

    @GetMapping("/lister-by-firstname/{firstname}")
    @ResponseStatus(HttpStatus.OK)
    public List<LibraryDto> listerByFirstname(@PathVariable final String firstname) {
        List<Library> libraries = libraryService.searchAllLibraries(firstname);
        return libraryAdapter.mapToDtoList(libraries);
    }

    @GetMapping("/lister-by-type/{type}")
    @ResponseStatus(HttpStatus.OK)
    public List<LibraryDto> listerByType(@PathVariable final Type type) {
        List<Library> libraries = libraryService.searchAllLibraries(type);
        return libraryAdapter.mapToDtoList(libraries);
    }

    @PutMapping("/modifier/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public LibraryDto modifier(@PathVariable long id, @RequestBody final LibraryDto libraryDto) {
        final Library library = libraryAdapter.mapToEntity(libraryDto);
        return libraryAdapter.mapToDto(libraryService.update(id, library));
    }

    @PostMapping("/ajout-livre/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addBookToLibrary(@PathVariable long id, @RequestBody BookReferenceDto bookReference) {
         libraryAdapter.mapToDto(libraryService.addBook(id, bookReference.isbn, bookReference.genre));
    }

    @DeleteMapping("/supprimer/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public LibraryDto supprimer(@PathVariable final long id) {
        return libraryAdapter.mapToDto(libraryService.delete(id));
    }

}
