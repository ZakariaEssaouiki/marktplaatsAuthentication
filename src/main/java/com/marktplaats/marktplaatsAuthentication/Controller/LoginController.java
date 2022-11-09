package com.marktplaats.marktplaatsAuthentication.Controller;

import com.marktplaats.marktplaatsAuthentication.Model.Gebruiker;
import com.marktplaats.marktplaatsAuthentication.Service.GebruikerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import java.util.Optional;

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
    public ResponseEntity<Object> Register(@RequestBody Gebruiker gebruiker){
        if(AlleGegevensIngevoerd(gebruiker)){
            if(gebruikerService.GebruikersnaamAlInGebruik(gebruiker.getGebruikersnaam()) || gebruikerService.EmailAlInGebruik(gebruiker.getEmail())){
                return new ResponseEntity<>(gson.toJson("Gebruikersnaam of email is al in gebruik!"),HttpStatus.BAD_REQUEST);
            }
            else{
                gebruikerService.Create(gebruiker);
                return new ResponseEntity<>(gson.toJson("Gebruiker is succesvol aangemaakt"), HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(gson.toJson("Voer aub alle invoervelden in."),HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/login")
    public ResponseEntity<Object> Login(String gebrOfEmail, String wachtwoord){
        if(!gebrOfEmail.isEmpty() && !wachtwoord.isEmpty()){
            if(gebruikerService.Login(gebrOfEmail,wachtwoord) != null){
                Optional<Gebruiker> gebruiker = gebruikerService.Login(gebrOfEmail,wachtwoord);
                return new ResponseEntity<>(gebruiker,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(gson.toJson("Voer aub een email en/of wachtwoord in."),HttpStatus.BAD_REQUEST);
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
