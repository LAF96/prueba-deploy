package proservices.models.entities.actores.reputaciones;

import proservices.models.entities.trabajos.Calificacion;
import proservices.models.entities.trabajos.Trabajo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class ReputacionRegular extends Reputacion {
    @Override
    public void recibirCalificacion(Calificacion calificacion) {
        //TRANSICION
    }

    @Override
    public void serContratado(Trabajo trabajo) {
        //VERIFICAR SI REALMENTE PUEDE SER CONTRATADO
        // EN CASO DE QUE NO
        Boolean puedeSerContrado = true;
        if(puedeSerContrado) {
            //
        }
        else throw new RuntimeException("El prestador no puede ser contrarado más de 4 veces");
    }
}
