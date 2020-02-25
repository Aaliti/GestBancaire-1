package com.virtuo.gestbancaire.controllers;


import com.virtuo.gestbancaire.Services.CompteService;
import com.virtuo.gestbancaire.Services.OperationService;
import com.virtuo.gestbancaire.entities.Compte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller

public class OperationController {

    @Autowired
    CompteService compServ;

    @Autowired
    OperationService operServ;

    @GetMapping("/comptes")
    public String listComptes(Model model){

        List<Compte> comptes = compServ.getAll();
        model.addAttribute("comptesList" , comptes);
        return "comptes/list-comptes";
    }


    @GetMapping(path = "/{id}/operations")
    public String historiqueComptes(@PathVariable("id") long id, Model model){
        model.addAttribute("solde", operServ.getSolde(id));
        model.addAttribute("compte",compServ.getById(id));

        return "comptes/operation";
    }



}
