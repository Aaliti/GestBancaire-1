package com.virtuo.gestbancaire.dao;

import com.virtuo.gestbancaire.entities.Compte;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompteRepository extends PagingAndSortingRepository<Compte, Long> {

    @Override
    public List<Compte> findAll();

    @Override
    Optional<Compte> findById(Long aLong);

    @Override
    Page<Compte> findAll(Pageable pageable);

    @Query(nativeQuery = true, value = "select * FROM compte where compte_id != ?1")
    public List<Compte> findComptes(Long id);

}
