package protopopova.alla.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public Collocation save(Collocation collocation) {
        log.debug("save {}", collocation);
        return repository.save(collocation);
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
    public List<Collocation> getAll() {
        log.debug("getAll()");
        return repository.getAll();
    }
}
