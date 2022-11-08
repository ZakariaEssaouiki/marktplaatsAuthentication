package com.marktplaats.marktplaatsAuthentication.Repo;

import com.marktplaats.marktplaatsAuthentication.Model.Gebruiker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GebruikerRepository extends JpaRepository<Gebruiker, Integer> {
    @Query("select (count(g) > 0) from Gebruiker g where g.gebruikersnaam = :naam")
    boolean findByGebruikersnaam(@Param("naam") String gebruikersnaam);

    @Query("SELECT (COUNT(g) > 0) FROM Gebruiker g where g.email = :email")
    boolean findByEmail(@Param("email") String email);

}
