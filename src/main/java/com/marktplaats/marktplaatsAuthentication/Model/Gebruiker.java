package com.marktplaats.marktplaatsAuthentication.Model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "Gebruikers")
public class Gebruiker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String gebruikersnaam;
    @Column(unique = true)
    private String email;
    private String wachtwoord;
    private String voornaam;
    private String achternaam;
    private LocalDate geboorteDatum;
    private Geslacht geslacht;

    public Gebruiker(String gebruikersnaam, String email, String wachtwoord, String voornaam,String achternaam,
                     LocalDate geboorteDatum, Geslacht geslacht,int id) {
        this.gebruikersnaam = gebruikersnaam;
        this.email = email;
        this.wachtwoord = wachtwoord;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.geboorteDatum = geboorteDatum;
        this.geslacht = geslacht;
        this.id = id;
    }

    public Gebruiker(){}

    public int getLeeftijd(){
        return (int)ChronoUnit.YEARS.between(this.geboorteDatum, LocalDate.now());
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getGebruikersnaam() {return gebruikersnaam;}
    public void setGebruikersnaam(String gebruikersnaam) {this.gebruikersnaam = gebruikersnaam;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getWachtwoord() {return wachtwoord;}
    public void setWachtwoord(String wachtwoord) {this.wachtwoord = wachtwoord;}
    public LocalDate getGeboorteDatum() {return geboorteDatum;}
    public void setGeboorteDatum(LocalDate geboorteDatum) {this.geboorteDatum = geboorteDatum;}
    public Geslacht getGeslacht() {return geslacht;}
    public void setGeslacht(Geslacht geslacht) {this.geslacht = geslacht;}
    public String getVoornaam() {return voornaam;}
    public void setVoornaam(String voornaam) {this.voornaam = voornaam;}
    public String getAchternaam() {return achternaam;}
    public void setAchternaam(String achternaam) {this.achternaam = achternaam;}

    @Override
    public String toString() {
        return this.gebruikersnaam;
    }
}
