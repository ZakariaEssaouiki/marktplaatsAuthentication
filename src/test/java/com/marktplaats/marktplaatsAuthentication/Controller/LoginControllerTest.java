package com.marktplaats.marktplaatsAuthentication.Controller;

import com.marktplaats.marktplaatsAuthentication.Model.Gebruiker;
import com.marktplaats.marktplaatsAuthentication.Model.Geslacht;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {

    private LoginController controller = new LoginController();
    @Test
    void login() {
    }

    @Test
    void alleGegevensIngevoerd() {
        Gebruiker gebruiker = new Gebruiker("ludotje","ludo@gmail.com","Ludo123!",
                "ludo","sanders", LocalDate.now(), Geslacht.Man,12);
        Gebruiker gebruiker2 = new Gebruiker();
        Assert.isTrue(controller.AlleGegevensIngevoerd(gebruiker));
        //Assert.isTrue(!controller.AlleGegevensIngevoerd(gebruiker2));
    }

}