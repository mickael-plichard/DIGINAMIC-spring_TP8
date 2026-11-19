package fr.diginamic.hello.controleurs;

import fr.diginamic.hello.entites.Ville;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

    @GetMapping
    public List<Ville> getVilles() {
        return List.of(
                new Ville("Nice", 343000),
                new Ville("Carcassonne", 47800),
                new Ville("Narbonne", 53400),
                new Ville("Lyon", 484000),
                new Ville("Foix", 9700),
                new Ville("Pau", 77200),
                new Ville("Marseille", 850700),
                new Ville("Tarbes", 40600)
        );
    }
}
