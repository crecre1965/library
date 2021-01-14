    package fr.training.spring.library;

    import javax.persistence.*;

    @Embeddable
    public class Director {

    //    private long id;

        @Column(name = "DIRECTOR_FIRSTNAME")
        private String firstname;
        @Column(name = "DIRECTOR_LASTNAME")
        private String lastname;

        public Director() {

        }

    //    public long getId() {
    //        return id;
    //    }
    //
    //    public void setId(long id) {
    //        this.id = id;
    //    }

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
    }
