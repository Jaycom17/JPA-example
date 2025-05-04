package edu.co.unicauca.tallerJPA.capaAccesoADatos.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("FormatotiA") 
@Getter
@Setter
public class FormatotiA extends FormatoA{
    @Column(name = "nombre_estudiante2", length = 100)
    private String nombreEstudiante2;
    @Column(name = "codigo_estudiante2", length = 100)
    private String codigoEstudiante2;

    public FormatotiA() {
        super();
    }

    public FormatotiA(String titulo, String objetivoGeneral, String objetivosEspecifico, String nombreEstudiante1,String nombreEstudiante2, String codigoEstudiante1, String codigoEstudiante2) {
        super(titulo, objetivoGeneral, objetivosEspecifico, nombreEstudiante1, codigoEstudiante1);
        this.nombreEstudiante2 = nombreEstudiante2;
        this.codigoEstudiante2 = codigoEstudiante2;
    }
}
