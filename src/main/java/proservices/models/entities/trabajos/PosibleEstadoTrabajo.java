package proservices.models.entities.trabajos;

import lombok.Getter;
import lombok.Setter;
import proservices.models.entities.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "trabajo_posible_estado")
@Setter
@Getter
public class PosibleEstadoTrabajo extends EntidadPersistente {
    @Column(name = "nombre")
    private String nombre;
}
