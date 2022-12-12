package proservices.models.entities.trabajos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Setter
@Getter
public class Direccion {
    @Column(name = "calle")
    private String calle;

    @Column(name = "altura")
    private Integer altura;

    @Column(name = "localidad")
    private String localidad;

    @Column(name = "cp")
    private String cp;

    @Column(name = "piso")
    private String piso;

    @Column(name = "depto")
    private String depto;

    @Column(name = "referencia")
    private String referencia;
}
