package protopopova.alla.service;

import protopopova.alla.model.Collocation;

import java.util.List;

public interface CollocationService {

    Collocation save(Collocation collocation, int groupId);

    List<Collocation> save(List<Collocation> collocation, int groupId);

    boolean delete(int id);

    Collocation get(int id);

    List<Collocation> getAll(int groupId);
}
