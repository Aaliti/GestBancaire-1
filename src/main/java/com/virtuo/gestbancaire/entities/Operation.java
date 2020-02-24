package com.virtuo.gestbancaire.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@SequenceGenerator(name = "operation_seq",allocationSize = 1)
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "operation_seq")
    private long id;

    private double montant;
    private Date date;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "compte_id")
    private Compte compte;
}
