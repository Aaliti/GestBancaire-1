package com.virtuo.gestbancaire.dao;

import com.virtuo.gestbancaire.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface OperationRepository extends PagingAndSortingRepository<Operation, Long> {

    @Override
    public List<Operation> findAll();

    @Override
    public Optional<Operation> findById(Long aLong);

    @Override
    Page<Operation> findAll(Pageable pageable);

    @Query(nativeQuery = true, value = "select SUM(montant) from operation where compte_id = ?1")
    public Double getSolde(Long id);

    @Query(nativeQuery = true, value = "select * from operation where compte_id = ?1")
    public List<Operation> findByCompte_Id(Long id);
}
