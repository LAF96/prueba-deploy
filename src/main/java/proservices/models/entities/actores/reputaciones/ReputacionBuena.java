package proservices.models.entities.actores.reputaciones;

import proservices.models.entities.trabajos.Calificacion;
import proservices.models.entities.trabajos.Trabajo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("buena")
public class ReputacionBuena extends Reputacion {

    @Override
    public void recibirCalificacion(Calificacion calificacion) {
        //
    }

    @Override
    public void serContratado(Trabajo trabajo) {
        //
    }
}
