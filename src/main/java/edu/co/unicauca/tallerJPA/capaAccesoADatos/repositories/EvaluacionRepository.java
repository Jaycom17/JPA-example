package edu.co.unicauca.tallerJPA.capaAccesoADatos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.co.unicauca.tallerJPA.capaAccesoADatos.entities.Evaluacion;

public interface EvaluacionRepository extends JpaRepository<Evaluacion, Integer> {
    // No additional methods are needed for basic CRUD operations
    
}
