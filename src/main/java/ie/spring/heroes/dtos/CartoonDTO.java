package ie.spring.heroes.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartoonDTO {
    private int id;
    private String title;
    private String creator;
    private int yearAppeared;
    private String genre;
}

