package fr.training.spring.library.domain.library;



import fr.training.spring.library.domain.common.ddd.DDD;
import fr.training.spring.library.domain.common.exception.ErrorCodes;
import fr.training.spring.library.domain.common.exception.ValidationException;
import org.springframework.util.StringUtils;

import java.util.Objects;


@DDD.ValueObject
public class Director {

    private String firstname;
    private String lastname;

    public Director() {

    }



    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String nom) {
        this.lastname = nom;
    }

    public Director(String firstname, String lastname) {

        this.firstname = firstname;
        this.lastname = lastname;
    }


    public void validate()  {
         if (this==null || StringUtils.isEmpty(firstname)|| StringUtils.isEmpty(lastname)){
            throw(new ValidationException("le directeur (nom et prenom) doit être renseigné", ErrorCodes.DIRECTOR_IS_MISSING));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Director director = (Director) o;
        return firstname.equals(director.firstname) && lastname.equals(director.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname);
    }
}
