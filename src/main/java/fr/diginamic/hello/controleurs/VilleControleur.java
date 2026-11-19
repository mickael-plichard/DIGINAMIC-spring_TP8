package fr.diginamic.hello.controleurs;

import fr.diginamic.hello.entites.Ville;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleControleur {
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

    @GetMapping
    public List<Ville> getVilles() {
        return villes;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ville> getVille(@PathVariable int id) {
        Ville ville = trouver(id);
        if (ville == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ville);
    }

    @PostMapping
    public ResponseEntity<String> ajouterVille(@RequestBody Ville nouvelleVille) {
        if (nomExiste(nouvelleVille.getNom(), 0)) {
            return ResponseEntity.badRequest().body("La ville existe déjà");
        }
        ajouter(nouvelleVille);
        return ResponseEntity.ok("Ville insérée avec succès");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modifierVille(@PathVariable int id, @RequestBody Ville donnees) {
        Ville ville = trouver(id);
        if (ville == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucune ville avec l'id " + id);
        }
        if (nomExiste(donnees.getNom(), id)) {
            return ResponseEntity.badRequest().body("La ville existe déjà");
        }
        ville.setNom(donnees.getNom());
        ville.setPopulation(donnees.getPopulation());
        return ResponseEntity.ok("Ville modifiée avec succès");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerVille(@PathVariable int id) {
        boolean supprimee = villes.removeIf(v -> v.getId() == id);
        if (!supprimee) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucune ville avec l'id " + id);
        }
        return ResponseEntity.ok("Ville supprimée avec succès");
    }

    // ---- Utils ----
    private void ajouter(Ville ville) {
        ville.setId(++dernierId);
        villes.add(ville);
    }

    private Ville trouver(int id) {
        return villes.stream().filter(v -> v.getId() == id).findFirst().orElse(null);
    }

    private boolean nomExiste(String nom, int idExclu) {
        return villes.stream()
                .anyMatch(v -> v.getId() != idExclu && v.getNom().equalsIgnoreCase(nom));
    }
}
