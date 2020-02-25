package com.virtuo.gestbancaire.controllers;


import com.virtuo.gestbancaire.Services.CompteService;
import com.virtuo.gestbancaire.Services.OperationService;
import com.virtuo.gestbancaire.entities.Compte;
import com.virtuo.gestbancaire.entities.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller

public class OperationController {

    @Autowired
    CompteService compServ;
    @Autowired
    OperationService operServ;


    @GetMapping("/comptes")
    public String listComptes(Model model) {

        List<Compte> comptes = compServ.getAll();
        model.addAttribute("comptesList", comptes);
        return "comptes/list-comptes";
    }


    @GetMapping(path = "/{id}/operations")
    public String historiqueComptes(@PathVariable("id") long id, Model model) {
        
        model.addAttribute("nomCompte", compServ.getById(id).getNom());
        model.addAttribute("solde", operServ.getSolde(id));
        model.addAttribute("operations", operServ.getOperationsByCompte_Id(id));

        return "comptes/operations";
    }

    @GetMapping("/{id}/transactions")
    public String viewTransactions(@PathVariable("id") long id, Model model) {
        Operation newOp = new Operation();


        model.addAttribute("id",id);
        model.addAttribute("nomCompte", compServ.getById(id).getNom());
        model.addAttribute("newOp", newOp);


        return "comptes/transactions";
    }

    @PostMapping("/operation/save")
    public String doTransaction(Model model, Operation operation,@RequestParam("compteId")long id,@RequestParam("action")String radio) {

        operation.setCompte(compServ.getById(id));

        if (radio.equals("Debit")){
            if (operation.getMontant()<=operServ.getSolde(id)){
                operation.setMontant(-operation.getMontant());
            }else{

                return "redirect:/"+id+"/transactions";
            }
        }

        operServ.save(operation);

        return "redirect:/"+id+"/operations";
    }
}
