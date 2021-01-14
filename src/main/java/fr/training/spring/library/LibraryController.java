package fr.training.spring.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryController {

    @Autowired
    LibraryService libraryService;

    @PostMapping("/creer")
    @ResponseStatus(HttpStatus.CREATED)
    public Library creer(@RequestBody final Library library){
        return libraryService.create(library);

    }

    @GetMapping("/afficher")
    @ResponseStatus(HttpStatus.OK)
    public Library afficher( @PathVariable final Long id){
        return libraryService.find(id);
    }

    @GetMapping("/lister-all")
    @ResponseStatus(HttpStatus.OK)
    public List<Library> lister(){
        return libraryService.searchAllLibraries();
    }

    @GetMapping("/lister-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Library listerById(@PathVariable long id){
        return libraryService.find(id);
    }

    @GetMapping("/lister-by-firstname/{firstname}")
    @ResponseStatus(HttpStatus.OK)
    public List<Library> listerByFirstname(@PathVariable final String firstname){
        return libraryService.searchAllLibraries(firstname);
    }

    @GetMapping("/lister-by-type/{type}")
    @ResponseStatus(HttpStatus.OK)
    public List<Library> listerByType(@PathVariable final Type type){
        return libraryService.searchAllLibraries(type);
    }

    @PutMapping("/modifier/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Library modifier(@PathVariable long id,@RequestBody final Library library){
        return libraryService.update(id, library);
    }
 @DeleteMapping("/supprimer/{id}")
 @ResponseStatus(HttpStatus.OK)
    public Library supprimer(@PathVariable final long id){
        return libraryService.delete(id);
    }

}
