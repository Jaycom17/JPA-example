package edu.co.unicauca.tallerJPA.capaAccesoADatos.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = jakarta.persistence.InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_formato")
@Setter
@Getter
@Table(name = "FormatosA")
public abstract class FormatoA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFormatoA")
    private Integer idFormatoA;
    @Column(name = "titulo", length = 100)
    private String titulo;
    @Column(name = "objetivo_general", columnDefinition = "TEXT")
    private String objetivoGeneral;
    @Column(name = "objetivos_especificos", columnDefinition = "TEXT")
    private String objetivosEspecificos;
    @Column(name = "nombre_estudiante1", length = 100)
    private String nombreEstudiante1;
    @Column(name = "codigo_estudiante1", length = 100)
    private String codigoEstudiante1;
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { jakarta.persistence.CascadeType.PERSIST, jakarta.persistence.CascadeType.MERGE })
    @JoinColumn(name = "idfkDocente")
    private Docente objDocente;

    @OneToOne(mappedBy = "objFormatoA", cascade = { jakarta.persistence.CascadeType.PERSIST, jakarta.persistence.CascadeType.MERGE }, fetch = FetchType.LAZY)
    private Estado objEstado;

    @OneToMany(mappedBy = "objFormatoA", fetch = FetchType.LAZY)
    private List<Evaluacion> evaluaciones;

    public FormatoA() {
        this.evaluaciones = new ArrayList<Evaluacion>();
    }
    

    public FormatoA(String titulo, String objetivo_general, String objetivos_especifico, String nombre_estudiante1, String codigo_estudiante1) {
        this.titulo = titulo;
        this.objetivoGeneral = objetivo_general;
        this.objetivosEspecificos = objetivos_especifico;
        this.nombreEstudiante1 = nombre_estudiante1;
        this.codigoEstudiante1 = codigo_estudiante1;
        this.evaluaciones = new ArrayList<Evaluacion>();
    }
}
