package com.virtuo.gestbancaire.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@SequenceGenerator(name = "compte_seq", allocationSize = 1)
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "compte_seq")
    private long compteId;
    private String nom;

    @OneToMany(mappedBy = "compte")
    private List<Operation> operations;

    public Compte() {
    }

    public Compte(long compteId, String nom) {
        this.compteId = compteId;
        this.nom = nom;
    }

    public long getId() {
        return compteId;
    }

    public void setId(long compteId) {
        this.compteId = compteId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
