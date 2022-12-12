package proservices.models.entities.trabajos;

import lombok.Getter;
import lombok.Setter;
import proservices.models.entities.EntidadPersistente;
import proservices.models.entities.actores.Prestador;

import javax.persistence.*;

@Entity
@Table(name = "calificacion")
@Setter
@Getter
public class Calificacion extends EntidadPersistente {
    @Column(name = "estrellas")
    private Integer estrellas;

    @Column(name = "opinionLibre")
    private String opinionLibre;

    @OneToOne
    private Trabajo trabajo;

    @ManyToOne
    @JoinColumn(name = "prestador_id")
    private Prestador prestador;
}
