package edu.co.unicauca.tallerJPA.capaAccesoADatos.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FormatotiA extends FormatoA{
    @Column(name = "nombre_estudiante2", length = 100)
    private String nombreEstudiante2;

    public FormatotiA() {
        super();
    }

    public FormatotiA(String titulo, String objetivoGeneral, String objetivosEspecifico, String nombreEstudiante1, String nombreEstudiante2) {
        super(titulo, objetivoGeneral, objetivosEspecifico, nombreEstudiante1);
        this.nombreEstudiante2 = nombreEstudiante2;
    }
}
