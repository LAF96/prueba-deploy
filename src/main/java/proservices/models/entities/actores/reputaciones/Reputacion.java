package proservices.models.entities.actores.reputaciones;

import proservices.models.entities.EntidadPersistente;
import proservices.models.entities.actores.Prestador;
import proservices.models.entities.trabajos.Calificacion;
import proservices.models.entities.trabajos.Trabajo;

import javax.persistence.*;

@Entity
@Table(name = "reputacion")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class Reputacion extends EntidadPersistente {
    @OneToOne
    protected Prestador prestador;

    public abstract void recibirCalificacion(Calificacion calificacion);

    public abstract void serContratado(Trabajo trabajo);
}
