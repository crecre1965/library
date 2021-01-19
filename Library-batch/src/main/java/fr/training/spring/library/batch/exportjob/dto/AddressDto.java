package fr.training.spring.library.batch.exportjob.dto;


public class AddressDto {
    private int number;

    private String street;

    private int zipcode;

    private String city;

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

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("AddressDto [number=").append(number)  //
                .append(",street=").append(street) //
                .append(",zipcode=").append(zipcode) //
                .append(",city =").append(city) //
                .append("]");
        return builder.toString();

    }
}
