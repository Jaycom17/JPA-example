package edu.co.unicauca.tallerJPA.capaAccesoADatos.entities;

import java.util.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "estados")
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEstado")
    private Integer idEstado;
    @Column(name = "estado_actual", length = 50)
    private String estadoActual;
    @Column(name = "fecha_registro_estado")
    private Date fechaRegistroEstado;

    @OneToOne
    @MapsId
    @JoinColumn(name = "idfkFormatoa", referencedColumnName = "idFormatoA", unique = true)
    private FormatoA objFormatoA;
}
