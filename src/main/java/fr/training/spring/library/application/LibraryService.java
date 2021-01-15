package fr.training.spring.library.application;

import fr.training.spring.library.domain.library.Type;
import fr.training.spring.library.domain.library.Library;

import java.util.List;

public interface LibraryService {
    public Library create(Library library);
    public Library find(Long id);
    public Library update(long id,Library library);
    public List<Library> searchAllLibraries();
    public List<Library> searchAllLibraries(String firstname);
    public List<Library> searchAllLibraries(Type type);
    public Library delete(long id);
    }


