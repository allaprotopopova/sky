package protopopova.alla.service;

import protopopova.alla.model.Collocation;

import java.util.List;

public interface CollocationService {

    Collocation save(Collocation collocation);

    boolean delete(int id);

    Collocation get(int id);

    List<Collocation> getAll();
}
