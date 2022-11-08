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
import com.google.gson.Gson;

import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class LoginControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private GebruikerService gebruikerService;
    private LoginController controller;

    final private Gson gson = new Gson();

    @BeforeEach
    void setUp(){
        //gebruikerService = new GebruikerService();
    }
    @Test
    void register() throws Exception {
        this.gebruikerService = Mockito.mock(GebruikerService.class);
        this.controller = new LoginController(this.gebruikerService);
        Gebruiker gebruiker = new Gebruiker("jan01","jan@example.com","Jan123","jan","janssen",
                LocalDate.now(),Geslacht.Man,100);
        Gebruiker gebruikerFalse = new Gebruiker();
        gebruikerFalse.setId(12);
        this.controller.Register(gebruiker);
        //this.controller.Register(gebruikerFalse);
        String message = this.gson.toJson("Gebruiker is succesvol aangemaakt.");
        mvc.perform(get("/register"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(equalTo(message).toString()));
    }

    @Test
    void alleGegevensIngevoerd() {
        this.gebruikerService = Mockito.mock(GebruikerService.class);
        this.controller = new LoginController(this.gebruikerService);
        Gebruiker gebruiker = new Gebruiker("ludotje","ludo@gmail.com","Ludo123!",
                "ludo","sanders", LocalDate.now(), Geslacht.Man,12);
        Gebruiker gebruiker2 = new Gebruiker();
        Assert.isTrue(controller.AlleGegevensIngevoerd(gebruiker));
        Assert.isTrue(!controller.AlleGegevensIngevoerd(gebruiker2));
    }

}