package ie.spring.heroes.service;

import ie.spring.heroes.daos.CartoonRepository;
import ie.spring.heroes.daos.entities.Cartoon;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartoonServiceImpl implements CartoonService {
    private CartoonRepository cartoonRepository;

    @Override
    @Transactional
    public Cartoon save(Cartoon cartoon) {
        return cartoonRepository.save(cartoon);
    }

    @Override
    public int count() {
        return cartoonRepository.count();
    }

    @Override
    public List<Cartoon> findAll() {
        return cartoonRepository.findAll();
    }

    @Override
    public List<Cartoon> findByYear(int year) {
        return cartoonRepository.findByYear(year);
    }

    @Override
    public Cartoon findById(int id) throws CartoonNotFoundException {
        Optional<Cartoon> optionalCartoon = cartoonRepository.findById(id);
        if (optionalCartoon.isPresent()) {
            return optionalCartoon.get();
        }
        throw new CartoonNotFoundException("Cartoon with id " + id + " was not found.");
    }

    @Override
    @Transactional
    public void deleteById(int id) throws CartoonNotFoundException {
        int rowsAffected = cartoonRepository.deleteById(id);
        if (rowsAffected == 0) {
            throw new CartoonNotFoundException("Cartoon with id " + id + " was not found.");
        }
    }

    @Override
    @Transactional
    public void deleteByYear(int year) {
        cartoonRepository.deleteByYear(year);
    }
}

