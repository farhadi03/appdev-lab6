package ie.spring.heroes.daos;

import ie.spring.heroes.daos.entities.Hero;
import ie.spring.heroes.daos.entities.Name;

import java.util.List;
import java.util.Optional;

public interface HeroRepository {
    Hero save(Hero hero);
    int changeUniverse(int id, String universe);
    int deleteById(int id);
    int count();
    int countByUniverse(String universe);
    List<Hero> findAll();
    List<Name> findAllNames();
    Optional<Hero> findById(int id);
    boolean existsByAlias(String alias);
}

