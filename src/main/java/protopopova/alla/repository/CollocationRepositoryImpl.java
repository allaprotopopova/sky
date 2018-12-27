package protopopova.alla.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import protopopova.alla.model.Collocation;

import java.util.List;

@Repository
public class CollocationRepositoryImpl implements CollocationRepository {

    private JdbcTemplate jdbcTemplate;
    private static final RowMapper<Collocation> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Collocation.class);

    @Autowired
    public CollocationRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }

    @Override
    public Collocation save(Collocation collocation) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Collocation get(int id) {
        return null;
    }

    @Override
    public List<Collocation> getAll() {
        List<Collocation> col = jdbcTemplate.query("Select * from collocations", ROW_MAPPER);
        return col;
    }
}
