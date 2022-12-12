package proservices.models.entities.actores;

import lombok.Getter;
import lombok.Setter;
import proservices.models.entities.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "consumidor")
@Setter
@Getter
public class Consumidor extends EntidadPersistente {
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "email")
    private String email;
}
