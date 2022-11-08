package com.marktplaats.marktplaatsAuthentication.Service;

import com.marktplaats.marktplaatsAuthentication.Model.Gebruiker;
import com.marktplaats.marktplaatsAuthentication.Repo.GebruikerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GebruikerService implements IGebruikerService{
    @Autowired
    private GebruikerRepository repo;
    public GebruikerService(GebruikerRepository repo){
        this.repo = repo;
    }
    @Override
    public Optional<Gebruiker> FindById(int id) {
        Optional<Gebruiker> gebruiker = repo.findById(id);
        return gebruiker;
    }

    @Override
    public void Create(Gebruiker gebruiker) {
        repo.save(gebruiker);
    }

    @Override
    public void Delete(Gebruiker gebruiker) {
        repo.delete(gebruiker);
    }

    @Override
    public boolean GebruikersnaamAlInGebruik(String gebruikersnaam) {
        if(gebruikersnaam != null){
            if(!repo.findByGebruikersnaam(gebruikersnaam)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean EmailAlInGebruik(String email) {
        if(email != null){
            if(!repo.findByEmail(email)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Gebruiker> GetAll() {
        return repo.findAll();
    }
}
