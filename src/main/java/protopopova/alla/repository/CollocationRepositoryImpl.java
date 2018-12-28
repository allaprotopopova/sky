package protopopova.alla.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import protopopova.alla.model.Collocation;
import protopopova.alla.model.WordGroup;

import java.util.List;

@Repository
public class CollocationRepositoryImpl implements CollocationRepository {

    private CRUDCollocationRepository crudRepository;
    private CRUDWordGroupRepository crudWordGroupRepository;

    @Autowired
    public CollocationRepositoryImpl(CRUDCollocationRepository crudRepository, CRUDWordGroupRepository crudWordGroupRepository) {
        this.crudRepository=crudRepository;
        this.crudWordGroupRepository=crudWordGroupRepository;
    }

    @Override
    @Transactional
    public Collocation save(Collocation collocation, int groupId) {
        WordGroup w = crudWordGroupRepository.getOne(groupId);
        collocation.setWordGroup(w);
        return crudRepository.save(collocation);
    }

    @Override
    @Transactional
    public List<Collocation> save(List<Collocation> collocation, int groupId) {
        collocation.forEach(item-> {
           save(item, groupId);
        });
        return crudRepository.getAll(groupId);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id)!=0;
    }

    @Override
    public boolean deleteByGroupId(int groupId) {
        return crudRepository.deleteByGroupId(groupId)!=0;
    }

    @Override
    @Transactional
    public Collocation get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public List<Collocation> getAll(int groupId) {
        return crudRepository.getAll(groupId);
    }
}
