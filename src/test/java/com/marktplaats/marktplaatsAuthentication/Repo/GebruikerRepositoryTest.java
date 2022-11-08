package com.marktplaats.marktplaatsAuthentication.Repo;

import com.marktplaats.marktplaatsAuthentication.Model.Gebruiker;
import com.marktplaats.marktplaatsAuthentication.Model.Geslacht;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class GebruikerRepositoryTest {

    @Autowired
    private GebruikerRepository repo;

    @AfterEach
    void tearDown(){
        repo.deleteAll();
    }

    @Test
    void findByGebruikersnaam() {
        //given
        Gebruiker gebruiker = new Gebruiker("jan01","jan@example.com","Jan123","jan","janssen",
                LocalDate.now(), Geslacht.Man,100);
        repo.save(gebruiker);
        //when
        boolean verwachting = repo.findByGebruikersnaam("jan01");
        //then
        assertThat(verwachting).isTrue();
    }

    @Test
    void findByEmail() {
        //given
        Gebruiker gebruiker = new Gebruiker("jan01","jan@example.com","Jan123","jan","janssen",
                LocalDate.now(), Geslacht.Man,100);
        repo.save(gebruiker);
        //when
        boolean verwachting = repo.findByEmail("jan@example.com");
        boolean fouteVerwachting = repo.findByEmail("freek@example.com");
        //then
        assertThat(verwachting).isTrue();
        assertThat(fouteVerwachting).isFalse();
    }

    @Test
    void checkIfEmailExists(){
        //given
        String email = "jan@example.com";
        //when
        boolean verwachting = repo.findByEmail(email);
        //then
        assertThat(verwachting).isFalse();
    }
}