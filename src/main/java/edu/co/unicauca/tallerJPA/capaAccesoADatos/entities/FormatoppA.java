package edu.co.unicauca.tallerJPA.capaAccesoADatos.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FormatoppA extends FormatoA {
    @Column(name = "nombre_asesor", length = 100)
    private String nombreAsesor;
    @Column(name = "ruta_carta_aceptacion", length = 255)
    private String rutaCartaAceptacion;

    public FormatoppA() {
        super();
    }

    public FormatoppA(String titulo, String objetivoGeneral, String objetivosEspecifico, String nombreEstudiante1, String nombreAsesor, String rutaCartaAceptacion) {
        super(titulo, objetivoGeneral, objetivosEspecifico, nombreEstudiante1);
        this.nombreAsesor = nombreAsesor;
        this.rutaCartaAceptacion = rutaCartaAceptacion;
    }
    
}
