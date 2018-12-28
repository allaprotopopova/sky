package protopopova.alla.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import protopopova.alla.model.WordGroup;
import protopopova.alla.repository.CollocationRepository;
import protopopova.alla.repository.WordGroupRepository;
import protopopova.alla.util.exception.NotFoundException;

import java.util.List;

import static protopopova.alla.util.ValidationUtil.checkNotFoundWithId;


@Service
public class WordGroupServiceImpl implements WordGroupService {

    private WordGroupRepository repository;
    private CollocationRepository collocationRepository;

    @Autowired
    public WordGroupServiceImpl(WordGroupRepository repository, CollocationRepository collocationRepository) {
        this.repository = repository;
        this.collocationRepository = collocationRepository;
    }

    @Override
    public WordGroup create(WordGroup wordGroup) {
        Assert.notNull(wordGroup, "group nust not be null");
        return repository.save(wordGroup);
    }

    @Override
    public WordGroup update(WordGroup wordGroup) {
        return repository.save(wordGroup);
    }

    @Override
    @Transactional
    public void delete(int id) throws NotFoundException {
        collocationRepository.deleteByGroupId(id);
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public WordGroup get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<WordGroup> getAll() {
        return repository.getAll();
    }
}
