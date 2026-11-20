package fr.diginamic.hello.services;

import fr.diginamic.hello.entites.Ville;
import fr.diginamic.hello.exceptions.ExceptionFonctionnelle;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class VilleService {
    private final List<Ville> villes = new ArrayList<>();
    private int dernierId = 0;

    @PostConstruct
    public void initData() {
        ajouter(new Ville("Nice", 343000));
        ajouter(new Ville("Carcassonne", 47800));
        ajouter(new Ville("Narbonne", 53400));
        ajouter(new Ville("Lyon", 484000));
        ajouter(new Ville("Foix", 9700));
        ajouter(new Ville("Pau", 77200));
        ajouter(new Ville("Marseille", 850700));
        ajouter(new Ville("Tarbes", 40600));
    }

    public List<Ville> extraireToutes() {
        return villes;
    }

    public Ville extraireParId(int id) throws ExceptionFonctionnelle {
        for (Ville v : villes) {
            if (v.getId() == id) {
                return v;
            }
        }
        throw new ExceptionFonctionnelle("La ville d'identifiant " + id + " n'existe pas.");
    }

    public void insererVille(Ville ville) throws ExceptionFonctionnelle {
        controle(ville);
        for (Ville v : villes) {
            if (v.getNom().equalsIgnoreCase(ville.getNom())) {
                throw new ExceptionFonctionnelle("La ville existe déjà");
            }
        }
        ajouter(ville);
    }

    public void modifierVille(int id, Ville donnees) throws ExceptionFonctionnelle {
        Ville villeExistante = extraireParId(id);
        controle(donnees);
        for (Ville v : villes) {
            if (v.getId() != id && v.getNom().equalsIgnoreCase(donnees.getNom())) {
                throw new ExceptionFonctionnelle("La ville existe déjà");
            }
        }
        villeExistante.setNom(donnees.getNom());
        villeExistante.setPopulation(donnees.getPopulation());
    }

    public void supprimer(int id) throws ExceptionFonctionnelle {
        Ville villeExistante = extraireParId(id);
        villes.remove(villeExistante);
    }

    // ---- Recherches ----

    public List<Ville> rechercherParDebutNom(String nom) throws ExceptionFonctionnelle {
        List<Ville> resultat = new ArrayList<>();
        for (Ville v : villes) {
            if (v.getNom().toLowerCase().startsWith(nom.toLowerCase())) {
                resultat.add(v);
            }
        }
        if (resultat.isEmpty()) {
            throw new ExceptionFonctionnelle("Aucune ville dont le nom commence par " + nom + " n'a été trouvée");
        }
        return resultat;
    }

    public List<Ville> rechercherPopulationSuperieure(int min) throws ExceptionFonctionnelle {
        List<Ville> resultat = new ArrayList<>();
        for (Ville v : villes) {
            if (v.getPopulation() > min) {
                resultat.add(v);
            }
        }
        if (resultat.isEmpty()) {
            throw new ExceptionFonctionnelle("Aucune ville n'a une population supérieure à " + min);
        }
        return resultat;
    }

    public List<Ville> rechercherPopulationEntre(int min, int max) throws ExceptionFonctionnelle {
        List<Ville> resultat = new ArrayList<>();
        for (Ville v : villes) {
            if (v.getPopulation() > min && v.getPopulation() < max) {
                resultat.add(v);
            }
        }
        if (resultat.isEmpty()) {
            throw new ExceptionFonctionnelle("Aucune ville n'a une population comprise entre " + min + " et " + max);
        }
        return resultat;
    }

    // ---- Utils ----

    private void controle(Ville ville) throws ExceptionFonctionnelle {
        if (ville.getPopulation() < 10) {
            throw new ExceptionFonctionnelle("La ville doit avoir au moins 10 habitants");
        }
        if (ville.getNom() == null || ville.getNom().length() < 2) {
            throw new ExceptionFonctionnelle("La ville doit avoir un nom contenant au moins 2 lettres");
        }
    }

    private void ajouter(Ville ville) {
        ville.setId(++dernierId);
        villes.add(ville);
    }
}
