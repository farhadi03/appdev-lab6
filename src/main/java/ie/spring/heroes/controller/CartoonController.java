package ie.spring.heroes.controller;

import ie.spring.heroes.dtos.CartoonDTO;
import ie.spring.heroes.service.CartoonService;
import ie.spring.heroes.service.CartoonNotFoundException;
import ie.spring.heroes.daos.entities.Cartoon;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cartoons")
@AllArgsConstructor
public class CartoonController {

    private CartoonService cartoonService;

    private CartoonDTO convertToDTO(Cartoon cartoon) {
        return new CartoonDTO(
                cartoon.getCartoonId(),
                cartoon.getTitle(),
                cartoon.getCreator(),
                cartoon.getYearAppeared(),
                cartoon.getGenre()
        );
    }

    @GetMapping
    public ResponseEntity<List<CartoonDTO>> findAll() {
        List<CartoonDTO> cartoons = cartoonService.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(cartoons);
    }

    @GetMapping("/appeared/{year}")
    public ResponseEntity<List<CartoonDTO>> findByYear(@PathVariable int year) {
        List<CartoonDTO> cartoons = cartoonService.findByYear(year).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(cartoons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartoonDTO> findById(@PathVariable int id) {
        Cartoon cartoon = cartoonService.findById(id);
        return ResponseEntity.ok(convertToDTO(cartoon));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        cartoonService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/appeared/{year}")
    public ResponseEntity<Void> deleteByYear(@PathVariable int year) {
        cartoonService.deleteByYear(year);
        return ResponseEntity.noContent().build();
    }
}

