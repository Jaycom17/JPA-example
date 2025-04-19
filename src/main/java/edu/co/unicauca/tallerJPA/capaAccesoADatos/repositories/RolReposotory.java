package edu.co.unicauca.tallerJPA.capaAccesoADatos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.co.unicauca.tallerJPA.capaAccesoADatos.entities.Rol;

public interface RolReposotory extends JpaRepository<Rol, Integer> {
    // No additional methods are needed for basic CRUD operations
    // You can add custom query methods if needed
    
}
