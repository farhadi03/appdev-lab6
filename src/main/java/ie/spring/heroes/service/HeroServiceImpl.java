package ie.spring.heroes.service;

import ie.spring.heroes.daos.HeroRepository;
import ie.spring.heroes.daos.entities.Hero;
import ie.spring.heroes.daos.entities.Name;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HeroServiceImpl implements HeroService {
    private HeroRepository heroRepository;

    @Override
    @Transactional
    public Hero save(Hero hero) {
        if (heroRepository.existsByAlias(hero.getAlias())) {
            throw new AlreadyExists(hero.getAlias() + " already exists in the database.");
        }
        return heroRepository.save(hero);
    }

    @Override
    @Transactional
    public void changeUniverse(int id, String universe) {
        int rowsAffected = heroRepository.changeUniverse(id, universe);
        if (rowsAffected == 0) {
            throw new HeroNotFoundException("Hero with id " + id + " not found");
        }
    }

    @Override
    public int count() {
        return heroRepository.count();
    }

    @Override
    public int countByUniverse(String universe) {
        return heroRepository.countByUniverse(universe);
    }

    @Override
    public List<Hero> findAll() {
        return heroRepository.findAll();
    }

    @Override
    public List<Name> findAllNames() {
        return heroRepository.findAllNames();
    }

    @Override
    public Hero findById(int id) throws HeroNotFoundException {
        Optional<Hero> optionalHero = heroRepository.findById(id);
        if (optionalHero.isPresent()) {
            return optionalHero.get();
        }
        throw new HeroNotFoundException("Hero with id "
                + id + " was not found.");
    }

    @Override
    @Transactional
    public void deleteById(int id) throws HeroNotFoundException {
        int rowsAffected = heroRepository.deleteById(id);
        if (rowsAffected == 0) {
            throw new HeroNotFoundException("Hero with id "
                    + id + " was not found.");
        }
    }
}

