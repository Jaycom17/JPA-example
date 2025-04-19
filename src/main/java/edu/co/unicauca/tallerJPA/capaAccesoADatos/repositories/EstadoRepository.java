package edu.co.unicauca.tallerJPA.capaAccesoADatos.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.co.unicauca.tallerJPA.capaAccesoADatos.entities.Estado;

public interface EstadoRepository extends CrudRepository<Estado, Integer> {
    // No additional methods are needed for basic CRUD operations
    
}
