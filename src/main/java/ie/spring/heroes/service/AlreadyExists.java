package ie.spring.heroes.service;

public class AlreadyExists extends RuntimeException {
    public AlreadyExists(String s) {
        super(s);
    }
}

