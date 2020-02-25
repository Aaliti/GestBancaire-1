package com.virtuo.gestbancaire.Services;

import com.virtuo.gestbancaire.dao.OperationRepository;
import com.virtuo.gestbancaire.entities.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationService {

    @Autowired
    OperationRepository operRepo;

    public Operation save(Operation operation) {
        return operRepo.save(operation);
    }

    public List<Operation> getAll() {
        return operRepo.findAll();
    }


    public Operation getById(Long id) {
        return operRepo.findById(id).get();
    }


    public void deleteById(Long id) {
        operRepo.deleteById(id);
    }

    public Page<Operation> getAll(Pageable pageable) {
        return operRepo.findAll(pageable);
    }

    public Double getSolde(Long id) {
        if (operRepo.getSolde(id) == null) {
            return new Double(0.0);
        } else {
            return operRepo.getSolde(id);
        }
    }

    public List<Operation> getOperationsByCompte_Id(Long id) {
        return operRepo.findByCompte_Id(id);
    }


}
