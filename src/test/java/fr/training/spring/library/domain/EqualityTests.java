package fr.training.spring.library.domain;

import static org.assertj.core.api.Assertions.assertThat;

import fr.training.spring.library.domain.library.Address;
import fr.training.spring.library.domain.library.Director;
import fr.training.spring.library.domain.library.Library;
import fr.training.spring.library.domain.library.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Equality tests")
public class EqualityTests {

    @Test
    @DisplayName("should return equals even one atrribut was different (the id is the same")
    public void library_equality01() {
        //given
        Address address = new Address(26, "rue", 91620, "NOZAY");
        Director director = new Director("toto", "tata");

        // when
        Library libraryOne = new Library(12, Type.ASSOCIATIVE, address, director, null);
        Library libraryTwo = new Library(12, Type.NATIONALE, address, director, null);
        //then
        assertThat(libraryOne.equals(libraryTwo)).isTrue();
    }


    @Test
    @DisplayName("should return different is the id is different  even if all attributs are  equals")
    public void library_equality02() {
        //given
        Address address = new Address(26, "rue", 91620, "NOZAY");
        Director director = new Director("toto", "tata");

        // when
        Library libraryOne = new Library(12, Type.ASSOCIATIVE, address, director, null);
        Library libraryTwo = new Library(13, Type.ASSOCIATIVE, address, director, null);
        //then
        assertThat(libraryOne.equals(libraryTwo)).isFalse();
    }

    @Test
    @DisplayName("value objects are equals if all attributes are the same")
    public void value_objets_should_be_equal_if_same_properties() {
        final Director d1 = new Director("surname1", "name1");
        final Director d2 = new Director("surname1", "name1");

        assertThat(d1).isEqualTo(d2);
    }
}