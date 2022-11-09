package com.marktplaats.marktplaatsAuthentication.Service;

import com.marktplaats.marktplaatsAuthentication.Model.Gebruiker;
import com.marktplaats.marktplaatsAuthentication.Model.Geslacht;
import com.marktplaats.marktplaatsAuthentication.Repo.GebruikerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GebruikerServiceTest {

    @Mock
    private GebruikerRepository repo;
    private GebruikerService gebruikerService;

    @BeforeEach
    void setUp(){
        this.gebruikerService = new GebruikerService(repo);
    }

    @Test
    void findById() {
        //when
        gebruikerService.FindById(1);
        //then
        verify(repo).findById(1);
        verify(repo, Mockito.never()).deleteById(1);
    }

    @Test
    void getAll() {
        //when
        gebruikerService.GetAll();
        //then
        verify(repo).findAll();
    }

    @Test
    void create() {
        //when
        Gebruiker gebruiker = new Gebruiker("jan01","jan@example.com","Jan123","jan","janssen",
                LocalDate.now(), Geslacht.Man,100);
        gebruikerService.Create(gebruiker);
        //then
        //gebruiker die wordt aangemaakt en opgeslagen wordt onderschept.
        ArgumentCaptor<Gebruiker> argumentCaptor =
                ArgumentCaptor.forClass(Gebruiker.class);
        verify(repo).save(argumentCaptor.capture());
        Gebruiker capturedGebruiker = argumentCaptor.getValue();
        //Controleren of dezelfde gebruiker wordt aangemaakt.
        assertThat(capturedGebruiker).isEqualTo(gebruiker);
    }

    @Test
    void delete() {
        //when
        Gebruiker gebruiker = new Gebruiker("jan01","jan@example.com","Jan123","jan","janssen",
                LocalDate.now(), Geslacht.Man,100);
        gebruikerService.Delete(gebruiker);
        //then
        verify(repo).delete(gebruiker);
    }

    @Test
    void gebruikersnaamAlInGebruik() {
        //when
        gebruikerService.GebruikersnaamAlInGebruik("jan");
        //then
        verify(repo).findGebruikerByGebruikersnaam("jan");
    }

    @Test
    void checkIfGebruikersnaamIsNullOfLeeg() {
        //when
        gebruikerService.GebruikersnaamAlInGebruik(" ");
        //then
        verify(repo, never()).findGebruikerByGebruikersnaam(" ");
    }

    @Test
    void emailAlInGebruik() {
        //when
        gebruikerService.EmailAlInGebruik("jan@example.com");
        //then
        verify(repo).findGebruikerByEmail("jan@example.com");
    }

    @Test
    void chechIfEmailIsNullOfLeeg() {
        //when
        gebruikerService.EmailAlInGebruik("");
        //then
        verify(repo, never()).findGebruikerByEmail("");
    }

    @Test
    void login() {
        //when
        gebruikerService.Login("ludo101","Welkom101");
        //then
        verify(repo).findGebruikerByGebruikersnaamAndWachtwoordOrEmailAndWachtwoord("ludo101","Welkom101");
        verify(repo,never()).findGebruikerByGebruikersnaamAndWachtwoordOrEmailAndWachtwoord(" "," ");
    }
}