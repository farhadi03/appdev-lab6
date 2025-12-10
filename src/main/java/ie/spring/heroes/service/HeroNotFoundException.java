package ie.spring.heroes.service;

public class HeroNotFoundException extends RuntimeException {
    public HeroNotFoundException(String s) {
        super(s);
    }
}

