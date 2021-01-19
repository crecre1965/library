package fr.training.spring.library.domain.library;


import fr.training.spring.library.domain.common.ddd.DDD;

import java.util.Objects;

@DDD.ValueObject
public class Address {

    private int number;

    private String street;

    private int zipcode;

    private String city;

    public Address() {

    }


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Address(int number, String street, int zipcode, String city) {
        this.number = number;
        this.street = street;
        this.zipcode = zipcode;
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return number == address.number && zipcode == address.zipcode && street.equals(address.street) && city.equals(address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, street, zipcode, city);
    }
}
