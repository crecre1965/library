package fr.training.spring.library.batch.exportjob.dto;

public class DirectorDto {
    private String firstname;
    private String lastname;

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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DirectorDto [firstname=").append(firstname) //
                .append(", lastname=").append(lastname) //
                .append("]");
        return builder.toString();
    }
}
