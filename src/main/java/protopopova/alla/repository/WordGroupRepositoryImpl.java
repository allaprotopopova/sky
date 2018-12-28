package protopopova.alla.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import protopopova.alla.model.WordGroup;

import java.util.List;

@Repository
public class WordGroupRepositoryImpl implements WordGroupRepository {

    private CRUDWordGroupRepository crudRepository;

    @Autowired
    public WordGroupRepositoryImpl(CRUDWordGroupRepository repository) {
        this.crudRepository = repository;
    }

    @Override
    public WordGroup save(WordGroup wordGroup) {
        return crudRepository.save(wordGroup);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id)!=0;
    }

    @Override
    @Transactional
    public WordGroup get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public List<WordGroup> getAll() {
        List<WordGroup> list = crudRepository.findAll();
        return list;
    }
}
