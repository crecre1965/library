package fr.training.spring.library.infrastructure.library;

import fr.training.spring.library.domain.library.Library;
import fr.training.spring.library.domain.library.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibraryDAO extends JpaRepository<LibraryJpa,Long> {
    public List<Library> findAllByType(Type type);
    public List<Library> findAllByDirectorFirstname(String firstname);

//    // on peut remplacer la methode findAllByDirector_Firstname par la methode cu-dessous
//    @Query("SELECT l from Library l WHERE l.director.firstname=?1")
//    public List<Library> searchByFirstname(String firstname);
//
//    // on peut remplacer la methode findAllByDirector_Firstname par la methode cu-dessous  en SQL
//    @Query(value="SELECT * FROM LIBRARY WHERE DIRECTOR_FIRSTNAME = :firstname",nativeQuery = true)
//    public List<Library> searchByFirstnameBySql(String firstname);

}
