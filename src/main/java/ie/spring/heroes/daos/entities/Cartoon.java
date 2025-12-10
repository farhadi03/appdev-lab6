package ie.spring.heroes.daos.entities;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
public class Cartoon {
    private int cartoonId;
    private String title;
    private String creator;
    private int yearAppeared;
    private String genre;
}

