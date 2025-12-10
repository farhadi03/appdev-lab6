package ie.spring.heroes.service;

public class CartoonNotFoundException extends RuntimeException {
    public CartoonNotFoundException(String message) {
        super(message);
    }
}

