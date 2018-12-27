package protopopova.alla.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import protopopova.alla.model.Collocation;

import java.util.List;

@Repository
public class CollocationRepositoryImpl implements CollocationRepository {

    private CRUDCollocationRepository crudRepository;

    @Autowired
    public CollocationRepositoryImpl(CRUDCollocationRepository crudRepository) {
        this.crudRepository=crudRepository;
    }

    @Override
    public Collocation save(Collocation collocation) {
        return crudRepository.save(collocation);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id)!=0;
    }

    @Override
    public Collocation get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public List<Collocation> getAll() {
        List<Collocation> col = crudRepository.findAll();;
        return col;
    }
}
