package ie.spring.heroes.daos.entities;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
public class Hero {
    private int heroId;          // Corresponds to hero_id
    private String firstName;    // Corresponds to first_name
    private String lastName;     // Corresponds to last_name
    private String alias;        // Corresponds to alias
    private String origin;         // Corresponds to is_alien
    private int yearCreated;     // Corresponds to year_created
    private String universe;      // Corresponds to universe
}
