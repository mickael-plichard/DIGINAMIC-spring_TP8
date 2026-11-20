package fr.diginamic.hello.dao;

import fr.diginamic.hello.entites.Etape;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class EtapeDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void insertEtape(Etape etape) {
        em.persist(etape);
    }
}
