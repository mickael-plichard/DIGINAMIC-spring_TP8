package fr.diginamic.hello.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Etape {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String code;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    
    @ManyToOne
    @JoinColumn(name = "ID_AVENTURE")
    @JsonIgnore
    private Aventure aventure;

    public Etape() {}

    public Etape(String code, LocalDate dateDebut, LocalDate dateFin, Aventure aventure) {
        this.code = code;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.aventure = aventure;
    }
    
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getCode() {return code;}
    public void setCode(String code) {this.code = code;}
    public LocalDate getDateDebut() {return dateDebut;}
    public void setDateDebut(LocalDate dateDebut) {this.dateDebut = dateDebut;}
    public LocalDate getDateFin() {return dateFin;}
    public void setDateFin(LocalDate dateFin) {this.dateFin = dateFin;}
    public Aventure getAventure() {return aventure;}
    public void setAventure(Aventure aventure) {this.aventure = aventure;}
}
