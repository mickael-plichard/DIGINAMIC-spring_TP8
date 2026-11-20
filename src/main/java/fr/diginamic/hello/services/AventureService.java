package fr.diginamic.hello.services;

import fr.diginamic.hello.dao.AventureDao;
import fr.diginamic.hello.dao.EtapeDao;
import fr.diginamic.hello.entites.Aventure;
import fr.diginamic.hello.entites.Etape;
import fr.diginamic.hello.exceptions.ExceptionFonctionnelle;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class AventureService {

    @Autowired
    private AventureDao aventureDao;

    @Autowired
    private EtapeDao etapeDao;

    @PostConstruct
    public void initData() {
        Aventure tresor = new Aventure("Le trésor perdu", 3);
        Aventure montagne = new Aventure("La montagne sacrée", 7);
        Aventure foret = new Aventure("La forêt enchantée", 2);
        Aventure tour = new Aventure("La tour noire", 9);
        aventureDao.insertAventure(tresor);
        aventureDao.insertAventure(montagne);
        aventureDao.insertAventure(foret);
        aventureDao.insertAventure(tour);
        etapeDao.insertEtape(new Etape("TRE-1", LocalDate.of(2026, 6, 1), LocalDate.of(2026, 6, 3), tresor));
        etapeDao.insertEtape(new Etape("TRE-2", LocalDate.of(2026, 6, 4), LocalDate.of(2026, 6, 6), tresor));
        etapeDao.insertEtape(new Etape("MON-1", LocalDate.of(2026, 7, 10), LocalDate.of(2026, 7, 15), montagne));
    }

    public List<Aventure> extractAventures() {
        return aventureDao.extractAventures();
    }

    public Aventure extractAventure(int idAventure) throws ExceptionFonctionnelle {
        Aventure aventure = aventureDao.extractAventure(idAventure);
        if (aventure == null) {
            throw new ExceptionFonctionnelle("L'aventure d'identifiant " + idAventure + " n'existe pas.");
        }
        return aventure;
    }

    public List<Aventure> extractAventures(String suffixe) throws ExceptionFonctionnelle {
        List<Aventure> resultat = aventureDao.extractAventures(suffixe);
        if (resultat.isEmpty()) {
            throw new ExceptionFonctionnelle("Aucune aventure dont le nom commence par " + suffixe + " n'a été trouvée");
        }
        return resultat;
    }

    public List<Aventure> extractAventures(int min) throws ExceptionFonctionnelle {
        List<Aventure> resultat = aventureDao.extractAventures(min);
        if (resultat.isEmpty()) {
            throw new ExceptionFonctionnelle("Aucune aventure n'a une difficulté supérieure à " + min);
        }
        return resultat;
    }

    public List<Aventure> extractAventures(int min, int max) throws ExceptionFonctionnelle {
        List<Aventure> resultat = aventureDao.extractAventures(min, max);
        if (resultat.isEmpty()) {
            throw new ExceptionFonctionnelle("Aucune aventure n'a une difficulté comprise entre " + min + " et " + max);
        }
        return resultat;
    }

    public List<Aventure> insertAventure(Aventure aventure) throws ExceptionFonctionnelle {
        if (!aventureDao.extractAventuresParNom(aventure.getNom()).isEmpty()) {
            throw new ExceptionFonctionnelle("L'aventure existe déjà");
        }
        aventureDao.insertAventure(aventure);
        return aventureDao.extractAventures();
    }

    public List<Aventure> modifierAventure(int idAventure, Aventure aventureModifiee) throws ExceptionFonctionnelle {
        extractAventure(idAventure);
        for (Aventure a : aventureDao.extractAventuresParNom(aventureModifiee.getNom())) {
            if (a.getId() != idAventure) {
                throw new ExceptionFonctionnelle("L'aventure existe déjà");
            }
        }
        aventureDao.modifierAventure(idAventure, aventureModifiee);
        return aventureDao.extractAventures();
    }

    public List<Aventure> supprimerAventure(int idAventure) throws ExceptionFonctionnelle {
        extractAventure(idAventure);
        aventureDao.supprimerAventure(idAventure);
        return aventureDao.extractAventures();
    }
}
