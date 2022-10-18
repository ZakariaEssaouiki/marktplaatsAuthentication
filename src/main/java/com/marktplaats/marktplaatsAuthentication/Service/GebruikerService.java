package com.marktplaats.marktplaatsAuthentication.Service;

import com.marktplaats.marktplaatsAuthentication.Model.Gebruiker;

import java.util.Optional;

public interface GebruikerService {

    public Optional<Gebruiker> FindById(int id);
    public void Create(Gebruiker gebruiker);
    public void Delete(Gebruiker gebruiker);
    public boolean GebruikersnaamAlInGebruik(String gebruikersnaam);
    public boolean EmailAlInGebruik(String email);
}
