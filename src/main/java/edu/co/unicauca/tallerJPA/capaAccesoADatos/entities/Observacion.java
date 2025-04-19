package edu.co.unicauca.tallerJPA.capaAccesoADatos.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "observaciones")
public class Observacion {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "idObservacion")
    private Integer idObservacion;
    @Column(name = "observacion", columnDefinition = "TEXT")
    private String observacion;
    @Column(name = "fecha_registro_observacion")
    private Date fechaRegistroObservacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idfkEvaluacion")
    private Evaluacion objEvaluacion;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "observacion_docente", joinColumns = @JoinColumn(name = "idfkDocente"), inverseJoinColumns = @JoinColumn(name = "idObservacion"))
    private List<Docente> docentes;
}
