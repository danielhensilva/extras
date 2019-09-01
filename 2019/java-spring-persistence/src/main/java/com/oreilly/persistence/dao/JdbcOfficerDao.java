package com.oreilly.persistence.dao;

import com.oreilly.persistence.entities.Officer;
import com.oreilly.persistence.entities.Rank;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class JdbcOfficerDao implements OfficerDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcOfficerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Officer save(Officer officer) {
        Map<String, Object> parameters = this.createParameters(officer);

        Integer newId = (Integer) new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("officers")
                .usingGeneratedKeyColumns("id")
                .executeAndReturnKey(parameters);

        officer.setId(newId);
        return officer;
    }

    @Override
    public Optional<Officer> findById(Integer id) {
        String sql = "SELECT * FROM officers WHERE id=?";
        List<Officer> officers = jdbcTemplate.query(sql, this.getRowMapper(), id);

        if (officers.isEmpty())
            return Optional.empty();

        if (officers.size() > 1)
            throw new NoSuchElementException("Unexpected multiple items");

        Optional<Officer> result = Optional.ofNullable(officers.get(0));
        return result;
    }

    @Override
    public List<Officer> findAll() {
        String sql = "SELECT * FROM officers";
        List<Officer> officers = jdbcTemplate.query(sql, this.getRowMapper());
        return officers;
    }

    @Override
    public long count() {
        return this.jdbcTemplate.queryForObject("SELECT count(*) FROM officers", Long.class);
    }

    @Override
    public void delete(Officer officer) {
        this.jdbcTemplate.update("DELETE FROM officers WHERE id=?", officer.getId());
    }

    @Override
    public boolean existsById(Integer id) {
        return this.jdbcTemplate.queryForObject("SELECT EXISTS(SELECT 1 FROM officers WHERE id=?)", Boolean.class, id);
    }

    private RowMapper<Officer> getRowMapper() {
        return (row, num) -> new Officer(
                row.getInt("id"),
                Rank.valueOf(row.getString("rank")),
                row.getString("first_name"),
                row.getString("last_name")
        );
    }

    private Map<String, Object> createParameters(Officer officer) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("rank", officer.getRank());
        parameters.put("first_name", officer.getFirstName());
        parameters.put("last_name", officer.getLastName());
        return parameters;
    }
}
