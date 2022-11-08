package com.marktplaats.marktplaatsAuthentication.Service;

import com.marktplaats.marktplaatsAuthentication.Model.Gebruiker;

import java.util.List;
import java.util.Optional;

public interface IGebruikerService {

    public Optional<Gebruiker> FindById(int id);
    public void Create(Gebruiker gebruiker);
    public void Delete(Gebruiker gebruiker);
    public boolean GebruikersnaamAlInGebruik(String gebruikersnaam);
    public boolean EmailAlInGebruik(String email);
    public List<Gebruiker> GetAll();
    public Optional<Gebruiker> Login(String gebrOfEmail, String wachtwoord);
}
