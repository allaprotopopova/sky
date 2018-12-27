package protopopova.alla.repository;

import org.springframework.stereotype.Repository;
import protopopova.alla.model.Collocation;

import java.util.List;

@Repository
public class CollocationRepositoryImpl implements CollocationRepository {
    @Override
    public Collocation save(Collocation collocation) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Collocation get(int id) {
        return null;
    }

    @Override
    public List<Collocation> getAll() {
        return null;
    }
}
