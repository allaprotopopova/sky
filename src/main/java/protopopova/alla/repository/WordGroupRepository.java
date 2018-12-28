package protopopova.alla.repository;

import protopopova.alla.model.WordGroup;

import java.util.List;

public interface WordGroupRepository {

    WordGroup save(WordGroup wordGroup);

    boolean delete(int id);

    WordGroup get(int id);

    List<WordGroup> getAll();



}