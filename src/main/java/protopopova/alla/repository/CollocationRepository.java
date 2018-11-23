package protopopova.alla.repository;

import protopopova.alla.model.Collocation;

import java.util.List;

public interface CollocationRepository {
    Collocation save(Collocation collocation);

    boolean delete(int id);

    Collocation get(int id);

    List<Collocation> getAll();

}