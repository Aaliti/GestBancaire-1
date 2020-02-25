package com.virtuo.gestbancaire.controllers;

import com.virtuo.gestbancaire.Services.CompteService;
import com.virtuo.gestbancaire.dao.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    CompteService compteServ;

    @GetMapping("/")
    public String getAll() {

        return "index";
    }


}
