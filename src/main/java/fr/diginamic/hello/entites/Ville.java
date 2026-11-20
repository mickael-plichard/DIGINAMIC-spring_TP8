package fr.diginamic.hello.entites;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Ville {
    private int id;
    @NotNull(message = "Le nom de la ville est obligatoire")
    @Size(min = 2, message = "Le nom de la ville doit contenir au moins 2 caractères")
    private String nom;
    @Min(value = 1, message = "Le nombre d'habitants doit être supérieur ou égal à 1")
    private int population;
    public Ville() {}

    public Ville(String nom, int population) {
        this.nom = nom;
        this.population = population;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}
    public int getPopulation() {return population;}
    public void setPopulation(int population) {this.population = population;}

    @Override
    public String toString() {
        return "#" + id + " " + nom + " (" + population + " hab.)";
    }
}
