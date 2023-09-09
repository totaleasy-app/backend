package app.totaleasy.backend.rest.repository;

import app.totaleasy.backend.rest.model.UrnaEletronica;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UrnaEletronicaRepository extends JpaRepository<UrnaEletronica, Integer> {

    boolean existsByNumeroSerie(Integer numeroSerie);

    Optional<UrnaEletronica> findByNumeroSerie(Integer numeroSerie);

    @Modifying
    @Transactional
    void deleteByNumeroSerie(Integer numeroSerie);
}
