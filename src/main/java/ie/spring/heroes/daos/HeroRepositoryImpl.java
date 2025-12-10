package ie.spring.heroes.daos;

import ie.spring.heroes.daos.entities.Hero;
import ie.spring.heroes.daos.entities.Name;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HeroRepositoryImpl implements HeroRepository {

    private JdbcClient jdbcClient;

    @Override
    public Hero save(Hero hero) {
        String sql =
                "INSERT INTO heroes (first_name, last_name, " +
                "alias, origin, year_created, universe)\n" +
                "VALUES(:fname, :lname, :alias, :origin, :yearCreated, :uni)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql(sql)
                .param("fname", hero.getFirstName())
                .param("lname", hero.getLastName())
                .param("alias", hero.getAlias())
                .param("origin", hero.getOrigin())
                .param("yearCreated", hero.getYearCreated())
                .param("uni", hero.getUniverse())
                .update(keyHolder);
        hero.setHeroId(keyHolder.getKey().intValue());
        return hero;
    }

    @Override
    public int changeUniverse(int id, String universe) {
        String sql = "UPDATE heroes SET universe = ? " +
                "WHERE hero_id = ?";
        return jdbcClient.sql(sql)
                .param(universe)
                .param(id)
                .update();
    }

    @Override
    public int deleteById(int id) {
        String sql = "DELETE FROM heroes WHERE hero_id = ?";
        return jdbcClient.sql(sql).param(id).update();
    }

    @Override
    public int count() {
        String sql = "select count(*) from heroes";
        return jdbcClient.sql(sql).query(Integer.class).single();
    }

    @Override
    public int countByUniverse(String universe) {
        String sql = "select count(*) from heroes where universe = :uni";
        return jdbcClient
                .sql(sql)
                .param("uni", universe)
                .query(Integer.class)
                .single();
    }

    @Override
    public List<Hero> findAll() {
        String sql = "select * from heroes";
        return jdbcClient
                .sql(sql)
                .query(Hero.class)
                .list();
    }

    @Override
    public List<Name> findAllNames() {
        String sql = "select first_name, last_name from heroes";
        return jdbcClient.sql(sql).query(Name.class).list();
    }

    @Override
    public Optional<Hero> findById(int id) {
        String sql = "select * from heroes where hero_id = :id";
        return jdbcClient.sql(sql)
                .param("id", id)
                .query(Hero.class)
                .optional();
    }

    @Override
    public boolean existsByAlias(String alias) {
        String sql = "select count(*) from heroes where alias = :alias";
        return jdbcClient.sql(sql).param("alias", alias).query(Integer.class).single() == 1;
    }
}

