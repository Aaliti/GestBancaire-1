package com.virtuo.gestbancaire.DAO;

import com.virtuo.gestbancaire.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
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
}
