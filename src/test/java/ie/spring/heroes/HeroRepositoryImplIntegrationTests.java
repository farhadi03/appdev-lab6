package ie.spring.heroes;

import ie.spring.heroes.configuration.Config;
import ie.spring.heroes.daos.HeroRepository;
import ie.spring.heroes.daos.entities.Hero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

// These integration tests were written by hand.
// They are integration tests because they use real Spring beans,
// including JdbcClient and the actual HeroRepository implementation,
// and operate against a real database (typically H2 in-memory for tests).

// Load Spring context using the specified configuration class

// Tests written by Cliona McGuane.

@SpringJUnitConfig(classes = Config.class)
public class HeroRepositoryImplIntegrationTests {

    // Inject JdbcClient to directly manipulate the test database
    @Autowired
    JdbcClient jdbcClient;

    // Inject the HeroRepository implementation being tested
    @Autowired
    HeroRepository heroRepository;

    // Prepare the database before each test
    @BeforeEach
    void setup() {
        // Clear the table to ensure a clean state
        jdbcClient.sql("DELETE FROM heroes").update();

        // Insert two heroes for testing
        String sql = "INSERT INTO heroes (hero_id, first_name, last_name, alias, origin, year_created, universe) VALUES"
                + "(1, 'Peter', 'Parker', 'Spider-Man', 'Human', 1962, 'Marvel')";
        jdbcClient.sql(sql).update();

        sql = "INSERT INTO heroes (hero_id, first_name, last_name, alias, origin, year_created, universe) VALUES" +
                "(2, 'Bruce', 'Wayne', 'Batman', 'Human', 1939, 'DC')";
        jdbcClient.sql(sql).update();
    }

    // Verify that the HeroRepository bean has been loaded by Spring
    @Test
    void beanExist() {
        assertNotNull(heroRepository);
    }

    @Test
    void count_shouldReturnTotalHeroCount() {
        int count = heroRepository.count();
        assertEquals(2, count);
    }

    @Test
    void countByUniverse_shouldReturnUniverseHeroCount() {
        int count = heroRepository.countByUniverse("Marvel");
        assertEquals(1, count);
    }

    @Test
    void findById_idExists_shouldReturnHero() {
        Optional<Hero> hero = heroRepository.findById(1);
        assertTrue(hero.isPresent());
        assertEquals("Peter", hero.get().getFirstName());
    }
    @Test
    void findById_idNotExist_shouldReturnEmptyOptional() {
        Optional<Hero> hero = heroRepository.findById(111);
        assertTrue(hero.isEmpty());
    }

    // Save a new hero and verify it is stored correctly
    @Test
    void save_shouldReturnNewHero() {
        Hero newHero = new Hero(0, "First", "Last", "HeroName", "Earth", 2025, "DC");
        Hero savedHero = heroRepository.save(newHero);

        assertNotNull(savedHero);
        assertTrue(savedHero.getHeroId() > 0); // Should be assigned a new auto-generated ID

        Optional<Hero> found = heroRepository.findById(savedHero.getHeroId());
        assertTrue(found.isPresent());
        assertEquals("HeroName", found.get().getAlias());
    }

    // Update an existing hero's universe and verify the change
    @Test
    void changeUniverse_shouldUpdateHero() {
        int updated = heroRepository.changeUniverse(1, "MTU");
        assertEquals(1, updated);

        Optional<Hero> found = heroRepository.findById(1);
        assertTrue(found.isPresent());
        assertEquals("MTU", found.get().getUniverse());
    }

    @Test
    void deleteById_idExists_shouldReturnOne() {
        int numberDeleted = heroRepository.deleteById(1);
        assertEquals(1, numberDeleted);
    }

    @Test
    void deleteById_idNotExist_shouldReturnZero() {
        int numberDeleted = heroRepository.deleteById(11);
        assertEquals(0, numberDeleted);
    }
}

