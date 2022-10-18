package com.marktplaats.marktplaatsAuthentication.Controller;

import com.marktplaats.marktplaatsAuthentication.Model.Gebruiker;
import com.marktplaats.marktplaatsAuthentication.Service.GebruikerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static java.sql.DriverManager.println;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {

    @Autowired
    private GebruikerService gebruikerService;

    @PostMapping("/login")
    public void Login(@RequestBody Gebruiker gebruiker){
        if(gebruikerService.GebruikersnaamAlInGebruik(gebruiker.getGebruikersnaam()) || gebruikerService.EmailAlInGebruik(gebruiker.getEmail())){
            println("Gebruikersnaam of email is al in gebruik!");
        }
        else if(AlleGegevensIngevoerd(gebruiker)){
            println("Voer aub alle invoervelden correct in.");
        }
        else{
            gebruikerService.Create(gebruiker);
        }
    }

    public boolean AlleGegevensIngevoerd(Gebruiker gebruiker){
        if(!gebruiker.getGebruikersnaam().equals("") && !gebruiker.getVoornaam().equals("")
                && !gebruiker.getAchternaam().equals("") && !gebruiker.getEmail().equals("")
                && !gebruiker.getGeboorteDatum().equals(null) && !gebruiker.getGeslacht().equals(null)){
            return true;
        }
        return false;
    }

}
