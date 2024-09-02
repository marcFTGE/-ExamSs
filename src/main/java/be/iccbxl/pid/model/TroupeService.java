package be.iccbxl.pid.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TroupeService {
    @Autowired
    private TroupeRepository repository;

    public List<Troupe> getAll() {
        List<Troupe> troupes = new ArrayList<>();

        repository.findAll().forEach(troupes::add);

        return troupes;
    }

    public Troupe get(String id) {
        Long indice = (long) Integer.parseInt(id);

        Optional<Troupe> troupe = repository.findById(indice);

        return troupe.isPresent() ? troupe.get() : null;

    }

    public void add(Troupe troupe) {
        repository.save(troupe);
    }
}
