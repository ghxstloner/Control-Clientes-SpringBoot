package com.ghost.controlclientes.web;

import com.ghost.controlclientes.domain.Persona;
import com.ghost.controlclientes.services.PersonaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ControllerInit {
    @Autowired
    private PersonaService personaService;

    @GetMapping("/")
    public String init (Model model, @AuthenticationPrincipal User user){
        Iterable<Persona> personas = personaService.listarPersonas();
        //Logs que informan a la aplicación dicho funcionamiento
        log.info("Ejecutando controlador SpringMVC");
        log.info("Usuario que hizo login: " + user.getUsername());
        // Modelos que se retornan directamente al index
        model.addAttribute("personas", personas);
        return "index";
    }

    @GetMapping("/agregar")
    public String agregar(Persona persona){
        return "modificar";
    }
    @PostMapping("/guardar")
    public String guardar(@Valid Persona persona, Errors errors){
        if(errors.hasErrors()){
            return "modificar";
        }
        personaService.guardar(persona);
        return "redirect:/";
    }
    //PRIMER MÉTODO BÁSICO
    @GetMapping("/editar/{id_persona}")
    public String editar(Persona persona, Model model){
        persona = personaService.encontrarPersona(persona);
        model.addAttribute("persona", persona);
        return "modificar";
    }

    //SEGUNDO MÉTODO CON QUERY PARAMETER
    @GetMapping("/eliminar")
    public String eliminar(Persona persona){
        personaService.eliminar(persona);
        return "redirect:/";
    }
}
