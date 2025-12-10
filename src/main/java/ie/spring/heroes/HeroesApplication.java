package ie.spring.heroes;


import ie.spring.heroes.configuration.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

public class HeroesApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Config.class, args);
    }
}


