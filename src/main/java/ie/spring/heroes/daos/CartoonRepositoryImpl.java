package ie.spring.heroes.daos;

import ie.spring.heroes.daos.entities.Cartoon;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CartoonRepositoryImpl implements CartoonRepository {

    private JdbcClient jdbcClient;

    @Override
    public Cartoon save(Cartoon cartoon) {
        String sql = "INSERT INTO cartoons (title, creator, year_appeared, genre) " +
                "VALUES(:title, :creator, :yearAppeared, :genre)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql(sql)
                .param("title", cartoon.getTitle())
                .param("creator", cartoon.getCreator())
                .param("yearAppeared", cartoon.getYearAppeared())
                .param("genre", cartoon.getGenre())
                .update(keyHolder);
        cartoon.setCartoonId(keyHolder.getKey().intValue());
        return cartoon;
    }

    @Override
    public int deleteById(int id) {
        String sql = "DELETE FROM cartoons WHERE cartoon_id = ?";
        return jdbcClient.sql(sql).param(id).update();
    }

    @Override
    public int deleteByYear(int year) {
        String sql = "DELETE FROM cartoons WHERE year_appeared = ?";
        return jdbcClient.sql(sql).param(year).update();
    }

    @Override
    public int count() {
        String sql = "SELECT COUNT(*) FROM cartoons";
        return jdbcClient.sql(sql).query(Integer.class).single();
    }

    @Override
    public List<Cartoon> findAll() {
        String sql = "SELECT * FROM cartoons";
        return jdbcClient.sql(sql)
                .query(Cartoon.class)
                .list();
    }

    @Override
    public List<Cartoon> findByYear(int year) {
        String sql = "SELECT * FROM cartoons WHERE year_appeared = :year";
        return jdbcClient.sql(sql)
                .param("year", year)
                .query(Cartoon.class)
                .list();
    }

    @Override
    public Optional<Cartoon> findById(int id) {
        String sql = "SELECT * FROM cartoons WHERE cartoon_id = :id";
        return jdbcClient.sql(sql)
                .param("id", id)
                .query(Cartoon.class)
                .optional();
    }
}

