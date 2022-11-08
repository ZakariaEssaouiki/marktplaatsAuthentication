package com.marktplaats.marktplaatsAuthentication.Controller;

import com.marktplaats.marktplaatsAuthentication.Model.Gebruiker;
import com.marktplaats.marktplaatsAuthentication.Model.Geslacht;
import com.marktplaats.marktplaatsAuthentication.Service.GebruikerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;

import java.time.LocalDate;
import static org.mockito.Mockito.*;

@WebMvcTest
class LoginControllerTest {

    //@Autowired
    //private MockMvc mvc;
    @MockBean
    private GebruikerService gebruikerService;
    private LoginController controller;

    @BeforeEach
    void setUp(){
        this.gebruikerService = Mockito.mock(GebruikerService.class);
        this.controller = new LoginController(this.gebruikerService);
    }

    @Test
    void register()  {
        Gebruiker gebruiker = new Gebruiker("jan01","jan@example.com","Jan123","jan","janssen",
                LocalDate.now(),Geslacht.Man,100);
        Gebruiker gebruiker2 = new Gebruiker("jan01","jan@example.com","Jan123","jan","janssen",
                LocalDate.now(),Geslacht.Man,101);
        Gebruiker gebruikerFalse = new Gebruiker();
        this.controller.Register(gebruiker);
        this.controller.Register(gebruiker2);
        this.controller.Register(gebruikerFalse);
        verify(gebruikerService, never()).Create(gebruikerFalse);
        //verify(gebruikerService, never()).Create(gebruiker2);
        verify(gebruikerService).GebruikersnaamAlInGebruik(gebruiker2.getGebruikersnaam());
        verify(gebruikerService).EmailAlInGebruik(gebruiker2.getEmail());
        verify(gebruikerService).Create(gebruiker);
        verify(gebruikerService).GebruikersnaamAlInGebruik(gebruiker.getGebruikersnaam());
        verify(gebruikerService).EmailAlInGebruik(gebruiker.getEmail());
    }

    @Test
    void login() {
        //given
        Gebruiker gebruiker = new Gebruiker("jan01","jan@example.com","Jan123","jan","janssen",
                LocalDate.now(),Geslacht.Man,100);
        Gebruiker gebruiker2 = new Gebruiker("tom","tom@example.com","Tom123","tom","janssen",
                LocalDate.now(),Geslacht.Man,101);
        Gebruiker gebruikerFalse = new Gebruiker();
        gebruikerFalse.setEmail("");
        gebruikerFalse.setGebruikersnaam("");
        //when
        this.gebruikerService.Create(gebruiker);
        this.gebruikerService.Create(gebruiker2);
        //gebruiker logt in met gebruikersnaam.
        this.controller.Login(gebruiker.getGebruikersnaam(),gebruiker.getWachtwoord());
        //gebruiker logt in met wachtwoord.
        this.controller.Login(gebruiker2.getEmail(),gebruiker2.getWachtwoord());
        //gebruiker zonder geldige inloggegevens probeert in te loggen.
        this.controller.Login(gebruikerFalse.getEmail(),gebruikerFalse.getWachtwoord());
        //then
        verify(gebruikerService, times(2)).Login(gebruiker.getGebruikersnaam(),gebruiker.getWachtwoord());
        verify(gebruikerService, times(2)).Login(gebruiker2.getEmail(),gebruiker2.getWachtwoord());
        verify(gebruikerService, never()).Login(gebruikerFalse.getEmail(),gebruikerFalse.getWachtwoord());
    }


    @Test
    void alleGegevensIngevoerd() {
        Gebruiker gebruiker = new Gebruiker("ludotje","ludo@gmail.com","Ludo123!",
                "ludo","sanders", LocalDate.now(), Geslacht.Man,12);
        Gebruiker gebruiker2 = new Gebruiker();
        Assert.isTrue(controller.AlleGegevensIngevoerd(gebruiker));
        Assert.isTrue(!controller.AlleGegevensIngevoerd(gebruiker2));
    }
}