package ie.spring.heroes.configuration;

import org.h2.tools.Server;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.SQLException;


@Configuration
@ComponentScan("ie.spring.heroes")
public class Config {

    @Bean
    DataSource dataSource() throws SQLException {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        builder.setType(EmbeddedDatabaseType.H2);
        builder.addScript("schema.sql");
        builder.addScript("data.sql");
        return builder.build();
    }

    @Profile("web")
    @Bean(initMethod = "start")
    Server embeddedH2Server() throws SQLException {
        return Server.createWebServer( "-web", "-webPort", "8082");
    }

    @Bean
    JdbcClient jdbcClient(DataSource dataSource) {
        return JdbcClient.create(dataSource);
    }
}

