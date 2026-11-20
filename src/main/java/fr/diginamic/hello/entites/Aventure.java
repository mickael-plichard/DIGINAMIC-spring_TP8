package fr.diginamic.hello.entites;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Aventure {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Le nom de l'aventure est obligatoire")
    @Size(min = 2, message = "Le nom de l'aventure doit contenir au moins 2 caractères")
    private String nom;

    @Min(value = 1, message = "La difficulté doit être supérieure ou égale à 1")
    private int difficulte;

    @OneToMany(mappedBy = "aventure", cascade = CascadeType.REMOVE)
    private List<Etape> etapes = new ArrayList<>();

    public Aventure() {}

    public Aventure(String nom, int difficulte) {
        this.nom = nom;
        this.difficulte = difficulte;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}
    public int getDifficulte() {return difficulte;}
    public void setDifficulte(int difficulte) {this.difficulte = difficulte;}
    public List<Etape> getEtapes() {return etapes;}
    public void setEtapes(List<Etape> etapes) {this.etapes = etapes;}

    @Override
    public String toString() {
        return "#" + id + " " + nom + " (difficulté " + difficulte + ")";
    }
}
