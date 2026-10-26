package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Workshop;
import mk.ukim.finki.wp.lab.repository.WorkshopRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryWorkshopRepository implements WorkshopRepository {

    public List<Workshop> findAll() {
        return DataHolder.workshops;
    }

    @Override
    public Optional<Workshop> findByTitle(String title) {
        return Optional.empty();
    }

    public List<Workshop> search(String text, Double maxPrice) {
        return DataHolder.workshops.stream()
                .filter(w -> text == null
                        || text.isBlank()
                        || w.getTitle().toLowerCase().contains(text.toLowerCase())
                        || w.getCategory().toLowerCase().contains(text.toLowerCase()))
                .filter(w -> maxPrice == null || w.getPrice() <= maxPrice)
                .toList();
    }
}
