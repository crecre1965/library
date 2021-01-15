package fr.training.spring.library.exposition.director;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;

public class DirectorDto {
    @JsonProperty ("prénom / firstname")
    private String firstname;
    @JsonProperty ("nom / lastname")
    private String lastname;

    public DirectorDto() {
    }

    public DirectorDto(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
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

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
