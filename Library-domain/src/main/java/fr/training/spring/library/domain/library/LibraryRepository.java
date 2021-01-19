package fr.training.spring.library.domain.library;

import fr.training.spring.library.domain.common.ddd.DDD;


import java.util.List;
@DDD.Repository
public interface LibraryRepository {
    public Library save(Library library);
    public Library delete(Library library);
    public Library findById(Long id);
    public List<Library> findAll();
    public List<Library> findAllByDirector_Firstname(String firstname);
    public List<Library> findAllByType(Type type);

}
