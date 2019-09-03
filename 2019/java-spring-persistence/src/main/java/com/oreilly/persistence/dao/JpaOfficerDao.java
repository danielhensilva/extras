package com.oreilly.persistence.dao;

import com.oreilly.persistence.entities.Officer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("JpaQlInspection")
@Repository
public class JpaOfficerDao implements OfficerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Officer save(Officer officer) {
        entityManager.persist(officer);
        return officer;
    }

    @Override
    public Optional<Officer> findById(Integer id) {
        Officer entity = entityManager.find(Officer.class, id);
        return Optional.ofNullable(entity);
    }

    @Override
    public List<Officer> findAll() {
        return entityManager
                .createQuery("SELECT o FROM Officer o", Officer.class)
                .getResultList();
    }

    @Override
    public long count() {
        return entityManager
                .createQuery("SELECT COUNT(o.id) FROM Officer o", Long.class)
                .getSingleResult();
    }

    @Override
    public void delete(Officer officer) {
        entityManager.remove(officer);
    }

    @Override
    public boolean existsById(Integer id) {
        Long count = entityManager
                .createQuery("SELECT COUNT(o.id) FROM Officer o WHERE o.id=:id", Long.class)
                .setParameter("id", id)
                .getSingleResult();
        return count > 0;
    }
}
