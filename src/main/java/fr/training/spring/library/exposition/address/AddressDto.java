package fr.training.spring.library.exposition.address;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;

public class AddressDto {
    @JsonProperty("numéro de rue / street number")
    private int number;

    @JsonProperty("rue / street ")
    private String street;

    @JsonProperty("code postal / zipcode")
    private int zipcode;

    @JsonProperty("ville / city")
   private String city;

    public AddressDto() {
    }

    public AddressDto(int number, String street, int zipcode, String city) {
        this.number = number;
        this.street = street;
        this.zipcode = zipcode;
        this.city = city;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
}
