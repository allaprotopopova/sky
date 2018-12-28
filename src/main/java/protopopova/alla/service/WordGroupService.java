package protopopova.alla.service;

import protopopova.alla.model.Collocation;
import protopopova.alla.model.WordGroup;

import java.util.List;

public interface WordGroupService {

    WordGroup create(WordGroup wordGroup);

    WordGroup update(WordGroup wordGroup);

    void delete(int id);

    WordGroup get(int id);

    List<WordGroup> getAll();
}
