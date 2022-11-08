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
        Gebruiker gebruikerFalse = new Gebruiker();
        gebruikerFalse.setId(12);
        this.controller.Register(gebruiker);
        verify(gebruikerService, never()).Create(gebruikerFalse);
        verify(gebruikerService).Create(gebruiker);
        verify(gebruikerService).GebruikersnaamAlInGebruik(gebruiker.getGebruikersnaam());
        verify(gebruikerService).EmailAlInGebruik(gebruiker.getEmail());
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