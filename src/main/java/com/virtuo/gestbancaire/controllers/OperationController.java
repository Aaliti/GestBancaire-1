package com.virtuo.gestbancaire.controllers;


import com.virtuo.gestbancaire.Services.CompteService;
import com.virtuo.gestbancaire.Services.OperationService;
import com.virtuo.gestbancaire.dto.CompteSolde;
import com.virtuo.gestbancaire.entities.Compte;
import com.virtuo.gestbancaire.entities.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller

public class OperationController {

    @Autowired
    CompteService compServ;

    @Autowired
    OperationService operServ;

    @GetMapping("/comptes")
    public String listComptes(Model model) {

        //List<Compte> comptes = compServ.getAll();
        List<CompteSolde> comptesSolde = operServ.getComptesSoldes();
        model.addAttribute("comptesList", comptesSolde);
        return "comptes/list-comptes";
    }

    @GetMapping(path = "/{id}/operations")
    public String historiqueComptes(@PathVariable("id") long id, Model model) {

        model.addAttribute("nomCompte", compServ.getById(id).getNom());
        model.addAttribute("solde", operServ.getSolde(id));
        model.addAttribute("operations", operServ.getOperationsByCompte_Id(id));

        return "comptes/operations";
    }

    @GetMapping("/{id}/transaction")
    public String viewTransactions(@PathVariable("id") long id, Model model /*, HttpSession session*/) {

        Operation newOp = new Operation();

        //session.setAttribute("id", id);

        model.addAttribute("nomCompte", compServ.getById(id).getNom());
        model.addAttribute("newOp", newOp);
            model.addAttribute("soldeCompte", operServ.getSolde(id) );
        model.addAttribute("listeComptes", operServ.getComptesSoldes());

        System.out.println(compServ.getAll());

        return "comptes/transactions";
    }

    @PostMapping("/operation/save")
    public String doTransaction(Model model, Operation operation, @RequestParam("compteId") long cpt_id, @RequestParam("action") String radio) /*HttpSession session,*/ {

        //long id = (long) session.getAttribute("id");

        operation.setCompte(compServ.getById(cpt_id));
        //session.invalidate();
        if (radio.equals("Debit")) {
            if (operation.getMontant() <= operServ.getSolde(cpt_id)) {
                operation.setMontant(-operation.getMontant());
            } else {
                return "redirect:/" + cpt_id + "/transactions";
            }
        }

        operServ.save(operation);

        return "redirect:/" + cpt_id + "/operations";
    }

    @GetMapping("/operation/virement")
    public String doVirement(Model model ) {

        model.addAttribute("listeComptes", operServ.getComptesSoldes());
        System.out.println(compServ.getAll());
        return "comptes/virement";
    }

    @PostMapping("/formResult")
    @ResponseBody
    public HashMap<String,String> result(@RequestParam("id")long id){
        HashMap<String,String> map=new HashMap<>();
        map.put("nom",compServ.getById(id).getNom());
        map.put("solde",operServ.getSolde(id).toString());
        return map;
    }
}