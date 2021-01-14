package fr.training.spring.library;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LibraryDao extends CrudRepository<Library,Long> {
    public List<Library> findAllByType(Type type);
    public List<Library> findAllByDirector_Firstname(String firstname);

    // on peut remplacer la methode findAllByDirector_Firstname par la methode cu-dessous
    @Query("SELECT l from Library l WHERE l.director.firstname=?1")
    public List<Library> searchByFirstname(String firstname);

    // on peut remplacer la methode findAllByDirector_Firstname par la methode cu-dessous  en SQL
    @Query(value="SELECT * FROM LIBRARY WHERE DIRECTOR_FIRSTNAME = :firstname",nativeQuery = true)
    public List<Library> searchByFirstnameBySql(String firstname);

}
