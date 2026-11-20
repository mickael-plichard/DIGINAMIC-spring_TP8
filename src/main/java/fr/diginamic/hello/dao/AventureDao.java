package fr.diginamic.hello.dao;

import fr.diginamic.hello.entites.Aventure;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class AventureDao {

    @PersistenceContext
    private EntityManager em;

    public List<Aventure> extractAventures() {
        TypedQuery<Aventure> query = em.createQuery("SELECT a FROM Aventure a", Aventure.class);
        return query.getResultList();
    }

    public Aventure extractAventure(int idAventure) {
        return em.find(Aventure.class, idAventure);
    }

    public List<Aventure> extractAventuresParNom(String nom) {
        TypedQuery<Aventure> query = em.createQuery("SELECT a FROM Aventure a WHERE UPPER(a.nom) = UPPER(:nom)", Aventure.class);
        query.setParameter("nom", nom);
        return query.getResultList();
    }

    public List<Aventure> extractAventures(String debut) {
        TypedQuery<Aventure> query = em.createQuery("SELECT a FROM Aventure a WHERE UPPER(a.nom) LIKE UPPER(:debut)", Aventure.class);
        query.setParameter("debut", debut + "%");
        return query.getResultList();
    }

    public List<Aventure> extractAventures(int min) {
        TypedQuery<Aventure> query = em.createQuery("SELECT a FROM Aventure a WHERE a.difficulte > :min", Aventure.class);
        query.setParameter("min", min);
        return query.getResultList();
    }

    public List<Aventure> extractAventures(int min, int max) {
        TypedQuery<Aventure> query = em.createQuery("SELECT a FROM Aventure a WHERE a.difficulte > :min AND a.difficulte < :max", Aventure.class);
        query.setParameter("min", min);
        query.setParameter("max", max);
        return query.getResultList();
    }

    @Transactional
    public void insertAventure(Aventure aventure) {
        em.persist(aventure);
    }

    @Transactional
    public void modifierAventure(int idAventure, Aventure aventureModifiee) {
        Aventure aventure = em.find(Aventure.class, idAventure);
        if (aventure != null) {
            aventure.setNom(aventureModifiee.getNom());
            aventure.setDifficulte(aventureModifiee.getDifficulte());
        }
    }

    @Transactional
    public void supprimerAventure(int idAventure) {
        Aventure aventure = em.find(Aventure.class, idAventure);
        if (aventure != null) {
            em.remove(aventure);
        }
    }
}
