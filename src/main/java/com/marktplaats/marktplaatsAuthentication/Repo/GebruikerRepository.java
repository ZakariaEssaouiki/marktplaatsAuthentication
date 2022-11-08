package com.marktplaats.marktplaatsAuthentication.Repo;

import com.marktplaats.marktplaatsAuthentication.Model.Gebruiker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GebruikerRepository extends JpaRepository<Gebruiker, Integer> {
    boolean findGebruikerByEmail(String email);
    boolean findGebruikerByGebruikersnaam(String gebruikersnaam);
    Optional<Gebruiker> findGebruikerByGebruikersnaamAndWachtwoordOrEmailAndWachtwoord(String gebrOfEmail, String wachtwoord);
}
