package fr.training.spring.library;



import javax.persistence.*;

@Embeddable
public class Address {

//    private long id;
@Column(name = "ADDRESS_NUMBER")
    private int number;

    @Column(name = "ADDRESS_STREET")
    private String street;

    @Column(name = "ADDRESS_ZIPCODE")
    private int zipcode;

    @Column(name = "ADDRESS_CITY")
    private String city;

    public Address() {

    }

//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }

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
}
