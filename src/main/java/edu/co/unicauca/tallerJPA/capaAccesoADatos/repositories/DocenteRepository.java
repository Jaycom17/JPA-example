package edu.co.unicauca.tallerJPA.capaAccesoADatos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.co.unicauca.tallerJPA.capaAccesoADatos.entities.Docente;

public interface DocenteRepository extends JpaRepository<Docente, Integer> {
    
}
