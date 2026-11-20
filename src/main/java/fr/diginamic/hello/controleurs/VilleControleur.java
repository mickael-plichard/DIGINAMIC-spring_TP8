package fr.diginamic.hello.controleurs;

import fr.diginamic.hello.entites.Ville;
import fr.diginamic.hello.exceptions.ExceptionFonctionnelle;
import fr.diginamic.hello.services.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

    @Autowired
    private VilleService villeService;

    @GetMapping
    public List<Ville> getVilles() {
        return villeService.extraireToutes();
    }

    @GetMapping("/{id}")
    public Ville getVille(@PathVariable int id) throws ExceptionFonctionnelle {
        return villeService.extraireParId(id);
    }

    @PostMapping
    public ResponseEntity<String> ajouterVille(@RequestBody Ville nouvelleVille) throws ExceptionFonctionnelle {
        villeService.insererVille(nouvelleVille);
        return ResponseEntity.ok("Ville insérée avec succès");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modifierVille(@PathVariable int id, @RequestBody Ville donnees) throws ExceptionFonctionnelle {
        villeService.modifierVille(id, donnees);
        return ResponseEntity.ok("Ville modifiée avec succès");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerVille(@PathVariable int id) throws ExceptionFonctionnelle {
        villeService.supprimer(id);
        return ResponseEntity.ok("Ville supprimée avec succès");
    }

    // ---- Recherches ----

    @GetMapping("/nom/{nom}")
    public List<Ville> rechercherParDebutNom(@PathVariable String nom) throws ExceptionFonctionnelle {
        return villeService.rechercherParDebutNom(nom);
    }

    @GetMapping("/population/{min}")
    public List<Ville> rechercherPopulationSuperieure(@PathVariable int min) throws ExceptionFonctionnelle {
        return villeService.rechercherPopulationSuperieure(min);
    }

    @GetMapping("/population/{min}/{max}")
    public List<Ville> rechercherPopulationEntre(@PathVariable int min, @PathVariable int max) throws ExceptionFonctionnelle {
        return villeService.rechercherPopulationEntre(min, max);
    }
}
