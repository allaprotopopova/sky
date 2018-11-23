package protopopova.alla.repository;

import protopopova.alla.model.Collocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CollocationRepositoryImpl implements CollocationRepository {

    private Map<Integer, Collocation> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(100);

    private void init() {
        repository.put(counter.incrementAndGet(), new Collocation(counter.get(), "sustained", "injury"));
        repository.put(counter.incrementAndGet(), new Collocation(counter.get(), "to get", "a rash"));
        repository.put(counter.incrementAndGet(), new Collocation(counter.get(), "to receive", "treatment"));
        repository.put(counter.incrementAndGet(), new Collocation(counter.get(), "to relieve", "stiffness"));
        repository.put(counter.incrementAndGet(), new Collocation(counter.get(), "to prescribe", "painkillers"));
        repository.put(counter.incrementAndGet(), new Collocation(counter.get(), "to have", "a stroke"));
    }

    public CollocationRepositoryImpl() {
        init();
    }

    @Override
    public Collocation save(Collocation collocation) {
        Objects.requireNonNull(collocation, "user must not be null");
        if (collocation.isNew()) {
            collocation.setId(counter.incrementAndGet());
            repository.put(collocation.getId(), collocation);
            return collocation;
        }
        return repository.computeIfPresent(collocation.getId(), (id, oldUser) -> collocation);
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id) != null;
    }

    @Override
    public Collocation get(int id) {
        return repository.get(id);
    }

    @Override
    public List<Collocation> getAll() {
        return new ArrayList<>(repository.values());
    }
}
