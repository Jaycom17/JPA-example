package edu.co.unicauca.tallerJPA.capaAccesoADatos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.co.unicauca.tallerJPA.capaAccesoADatos.entities.Observacion;

public interface ObservacionRepository extends JpaRepository<Observacion, Integer> {
    // No additional methods are needed for basic CRUD operations
    // You can define custom query methods here if needed
    
}
