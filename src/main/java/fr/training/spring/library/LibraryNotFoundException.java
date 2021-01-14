package fr.training.spring.library;

public class LibraryNotFoundException extends RuntimeException{
    public LibraryNotFoundException(String s) {
        super(s);
    }
}
