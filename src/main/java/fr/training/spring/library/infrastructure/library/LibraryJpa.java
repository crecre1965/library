package fr.training.spring.library.infrastructure.library;

import fr.training.spring.library.domain.book.Book;
import fr.training.spring.library.domain.library.Address;
import fr.training.spring.library.domain.library.Director;
import fr.training.spring.library.domain.library.Library;
import fr.training.spring.library.domain.library.Type;
import fr.training.spring.library.infrastructure.book.BookJpa;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "LIBRARY")
public class LibraryJpa {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        private long id;

        @Enumerated(EnumType.STRING)
        @Column(name = "LIBRARY_TYPE")
        private Type type;


        @Column(name = "ADDRESS_NUMBER")
        private int addressNumber;

        @Column(name = "ADDRESS_STREET")
        private String addressStreet;

        @Column(name = "ADDRESS_ZIPCODE")
        private int addressZipcode;

        @Column(name = "ADDRESS_CITY")
        private String addressCity;

        @Column(name = "DIRECTOR_FIRSTNAME")
        private String directorFirstname;
        @Column(name = "DIRECTOR_LASTNAME")
        private String directorLastname;

        @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
        @JoinColumn(name = "LIBRARY_ID", referencedColumnName = "ID")
        private List<BookJpa> books;

        public long getId() {
                return id;
        }

        public void setId(long id) {
                this.id = id;
        }

        public Type getType() {
                return type;
        }

        public void setType(Type type) {
                this.type = type;
        }

        public int getAddressNumber() {
                return addressNumber;
        }

        public void setAddressNumber(int addressNumber) {
                this.addressNumber = addressNumber;
        }

        public String getAddressStreet() {
                return addressStreet;
        }

        public void setAddressStreet(String addressStreet) {
                this.addressStreet = addressStreet;
        }

        public int getAddressZipcode() {
                return addressZipcode;
        }

        public void setAddressZipcode(int addressZipcode) {
                this.addressZipcode = addressZipcode;
        }

        public String getAddressCity() {
                return addressCity;
        }

        public void setAddressCity(String addressCity) {
                this.addressCity = addressCity;
        }

        public String getDirectorFirstname() {
                return directorFirstname;
        }

        public void setDirectorFirstname(String directorFirstname) {
                this.directorFirstname = directorFirstname;
        }

        public String getDirectorLastname() {
                return directorLastname;
        }

        public void setDirectorLastname(String directorLastname) {
                this.directorLastname = directorLastname;
        }

        public List<BookJpa> getBooks() {
                return books;
        }

        public void setBooks(List<BookJpa> books) {
                this.books = books;
        }
        public LibraryJpa()   {

        }


        public LibraryJpa(final Library library) {
                id = library.getId();
                type=library.getType();
                addressNumber=library.getAddress().getNumber();
                addressCity=library.getAddress().getCity();
                addressStreet= library.getAddress().getStreet();
                addressCity=library.getAddress().getCity();
                addressZipcode=library.getAddress().getZipcode();
                directorFirstname=library.getDirector().getFirstname();
                directorLastname=library.getDirector().getLastname();
                books=library.getBooks().stream().map(BookJpa::new).collect(Collectors.toList());

        }

       public Library toLibrary(){
                final Address address=new Address(addressNumber,addressStreet,addressZipcode,addressCity);
                final Director director = new Director(directorFirstname,directorLastname);
                final List<Book> bookList=books.stream().map(b -> new Book(b.getId(),b.getIsbn(),b.getTitle(),b.getAuthor(),b.getNumberOfPages(),b.getGenre())).collect(Collectors.toList());
       return new Library(id,type,address,director,bookList);
        }
}
