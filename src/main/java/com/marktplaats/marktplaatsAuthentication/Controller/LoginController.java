package com.marktplaats.marktplaatsAuthentication.Controller;

import com.marktplaats.marktplaatsAuthentication.Model.Gebruiker;
import com.marktplaats.marktplaatsAuthentication.Service.GebruikerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import static java.sql.DriverManager.println;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {

    @Autowired
    private GebruikerService gebruikerService;

    final private Gson gson = new Gson();

    public LoginController(GebruikerService gebruikerService){
        this.gebruikerService = gebruikerService;
    }

    @PostMapping("/register")
    public void Register(@RequestBody Gebruiker gebruiker){
        if(AlleGegevensIngevoerd(gebruiker)){
            if(gebruikerService.GebruikersnaamAlInGebruik(gebruiker.getGebruikersnaam()) || gebruikerService.EmailAlInGebruik(gebruiker.getEmail())){
                println("Gebruikersnaam of email is al in gebruik!");

            }
            else{
                gebruikerService.Create(gebruiker);
            }
        }
        String message = this.gson.toJson("Voer aub alle gegevens in.");
        println(message);
    }

    public boolean AlleGegevensIngevoerd(Gebruiker gebruiker){
        if(gebruiker.getGebruikersnaam() != null && gebruiker.getVoornaam() != null
                && gebruiker.getAchternaam() != null && gebruiker.getEmail() != null
                && !gebruiker.getGeboorteDatum().equals(null) && !gebruiker.getGeslacht().equals(null)){
            return true;
        }
        return false;
    }

}
