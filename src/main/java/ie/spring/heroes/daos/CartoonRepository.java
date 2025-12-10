package ie.spring.heroes.daos;

import ie.spring.heroes.daos.entities.Cartoon;

import java.util.List;
import java.util.Optional;

public interface CartoonRepository {
    Cartoon save(Cartoon cartoon);
    int deleteById(int id);
    int deleteByYear(int year);
    int count();
    List<Cartoon> findAll();
    List<Cartoon> findByYear(int year);
    Optional<Cartoon> findById(int id);
}

