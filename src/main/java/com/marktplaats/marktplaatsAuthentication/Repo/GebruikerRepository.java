package com.marktplaats.marktplaatsAuthentication.Repo;

import com.marktplaats.marktplaatsAuthentication.Model.Gebruiker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GebruikerRepository extends JpaRepository<Gebruiker, Integer> {
    boolean findByGebruikersnaam(String gebruikersnaam);
    boolean findByEmail(String email);
}
