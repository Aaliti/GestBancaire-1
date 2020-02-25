package com.virtuo.gestbancaire.Services;

import com.virtuo.gestbancaire.dao.CompteRepository;
import com.virtuo.gestbancaire.entities.Compte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompteService {

    @Autowired
    CompteRepository compRepo;

    public Compte save(Compte compte) {
        return compRepo.save(compte);
    }

    public List<Compte> getAll() {
        return compRepo.findAll();
    }

    public Compte getById(Long id) {
        return compRepo.findById(id).get();
    }

    public void deleteById(Long id) {
        compRepo.deleteById(id);
    }

    public Page<Compte> getAll(Pageable pageable) {
        return compRepo.findAll(pageable);
    }


}
