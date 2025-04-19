package edu.co.unicauca.tallerJPA.capaAccesoADatos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.co.unicauca.tallerJPA.capaAccesoADatos.entities.FormatoA;

public interface FormatoARepository extends JpaRepository<FormatoA, Integer> {
    // No additional methods are needed for basic CRUD operations
    
}
