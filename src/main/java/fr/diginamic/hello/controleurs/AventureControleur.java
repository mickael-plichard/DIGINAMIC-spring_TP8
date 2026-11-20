package fr.diginamic.hello.controleurs;

import fr.diginamic.hello.entites.Aventure;
import fr.diginamic.hello.exceptions.ExceptionFonctionnelle;
import fr.diginamic.hello.services.AventureService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/aventures")
public class AventureControleur {

    @Autowired
    private AventureService aventureService;

    @GetMapping
    public List<Aventure> getAventures() {
        return aventureService.extractAventures();
    }

    @GetMapping("/{id}")
    public Aventure getAventure(@PathVariable int id) throws ExceptionFonctionnelle {
        return aventureService.extractAventure(id);
    }

    @GetMapping("/nom/{suffixe}")
    public List<Aventure> getAventuresParNom(@PathVariable String suffixe) throws ExceptionFonctionnelle {
        return aventureService.extractAventures(suffixe);
    }

    @GetMapping("/difficulte/{min}")
    public List<Aventure> getAventuresDifficulteSuperieure(@PathVariable int min) throws ExceptionFonctionnelle {
        return aventureService.extractAventures(min);
    }

    @GetMapping("/difficulte/{min}/{max}")
    public List<Aventure> getAventuresDifficulteEntre(@PathVariable int min, @PathVariable int max) throws ExceptionFonctionnelle {
        return aventureService.extractAventures(min, max);
    }

    @PostMapping
    public List<Aventure> ajouterAventure(@Valid @RequestBody Aventure nouvelleAventure) throws ExceptionFonctionnelle {
        return aventureService.insertAventure(nouvelleAventure);
    }

    @PutMapping("/{id}")
    public List<Aventure> modifierAventure(@PathVariable int id, @Valid @RequestBody Aventure aventureModifiee) throws ExceptionFonctionnelle {
        return aventureService.modifierAventure(id, aventureModifiee);
    }

    @DeleteMapping("/{id}")
    public List<Aventure> supprimerAventure(@PathVariable int id) throws ExceptionFonctionnelle {
        return aventureService.supprimerAventure(id);
    }
}
