package com.virtuo.gestbancaire.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@SequenceGenerator(name = "operation_seq", allocationSize = 1)
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "operation_seq")
    private long id;
    private double montant;
    private Date date;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "compte_id")
    private Compte compte;

    public Operation() {
    }

    public Operation(long id, double montant, Date date) {
        this.id = id;
        this.montant = montant;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }
}
