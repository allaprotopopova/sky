package protopopova.alla.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import protopopova.alla.Application;
import protopopova.alla.model.Collocation;
import protopopova.alla.repository.CollocationRepository;

import java.util.List;

@Service
public class CollocationServiceImpl implements protopopova.alla.service.CollocationService {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    CollocationRepository repository;

    @Autowired
    public CollocationServiceImpl(CollocationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collocation save(Collocation collocation, int groupId) {
        log.debug("save {} with wordGroupId={}", collocation, groupId);
        return repository.save(collocation, groupId);
    }

    @Override
    @Transactional
    public List<Collocation> save(List<Collocation> collocation, int groupId) {
        return repository.save(collocation, groupId);
    }

    @Override
    public boolean delete(int id) {
        log.debug("delete collocation with id={}", id);
        return repository.delete(id);
    }

    @Override
    public Collocation get(int id) {
        log.debug("get collocation with id={}", id);
        return repository.get(id);
    }

    @Override
    public List<Collocation> getAll(int groupId) {
        log.debug("getAll() with groupId={}", groupId);
        return repository.getAll(groupId);
    }
}
