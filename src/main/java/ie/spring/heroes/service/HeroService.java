package ie.spring.heroes.service;

import ie.spring.heroes.daos.entities.Hero;
import ie.spring.heroes.daos.entities.Name;

import java.util.List;

public interface HeroService {
    Hero save(Hero hero);
    void changeUniverse(int id, String universe);
    int count();
    int countByUniverse(String universe);
    List<Hero> findAll();
    List<Name> findAllNames();
    Hero findById(int id) throws HeroNotFoundException;
    void deleteById(int id) throws HeroNotFoundException;
}

