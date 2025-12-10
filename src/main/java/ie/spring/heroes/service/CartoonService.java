package ie.spring.heroes.service;

import ie.spring.heroes.daos.entities.Cartoon;

import java.util.List;

public interface CartoonService {
    Cartoon save(Cartoon cartoon);
    int count();
    List<Cartoon> findAll();
    List<Cartoon> findByYear(int year);
    Cartoon findById(int id) throws CartoonNotFoundException;
    void deleteById(int id) throws CartoonNotFoundException;
    void deleteByYear(int year);
}

