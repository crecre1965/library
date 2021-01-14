package fr.training.spring.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    LibraryDao libraryDao;

    @Transactional
    @Override
    public Library create(Library library) {
//        Address address=library.getAddress();
//        Director director= library.getDirector();
        return libraryDao.save(library);
    }

    @Override
    public Library find(Long id) {
    return libraryDao.findById(id).orElseThrow(() -> new LibraryNotFoundException("librairie non trouvée !!! id : " + id));

    }

    @Transactional
    @Override
    public Library update(long id,Library libraryToUpdate) {
        Library library=find(id);
        library.update(libraryToUpdate);
        return libraryDao.save(library);
    }
    @Transactional
    @Override
    public List<Library> searchAllLibraries() {
        List<Library> libraries=new ArrayList<>();
        libraries= (List<Library>) libraryDao.findAll();
        return libraries;
    }

    @Override
    public List<Library> searchAllLibraries(String firstname) {
        List<Library> libraries=new ArrayList<>();
        libraries=(List<Library>) libraryDao.findAllByDirector_Firstname(firstname);
        return libraries;
    }

    @Override
    public List<Library> searchAllLibraries(Type type) {
        List<Library> libraries=new ArrayList<>();
        libraries= (List<Library>) libraryDao.findAllByType(type);
        return libraries;
    }

    @Transactional
    @Override
    public Library delete(long id) {
        Library library=find(id);
        libraryDao.delete(library);
        return library;
    }
}
