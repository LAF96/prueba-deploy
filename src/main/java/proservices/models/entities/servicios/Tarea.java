package proservices.models.entities.servicios;

import lombok.Getter;
import lombok.Setter;
import proservices.models.entities.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tarea")
@Setter
@Getter
public class Tarea extends EntidadPersistente {
    @Column(name = "descripcion")
    private String descripcion;

    @Override
    public String toString() {
        return "Tarea{" +
                "descripcion='" + descripcion + '\'' +
                '}';
    }
}
