package proservices.models.entities.servicios;

import lombok.Getter;
import lombok.Setter;
import proservices.models.entities.EntidadPersistente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "servicio")
@Setter
@Getter
public class Servicio extends EntidadPersistente {
    @Column(name = "nombre")
    private String nombre;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "servicio_id")
    private List<Tarea> tareas;

    public Servicio() {
        this.tareas = new ArrayList<>();
    }

    public void agregarTareas(Tarea ... tareas) {
        Collections.addAll(this.tareas, tareas);
    }

    public int cantTareas() {
        return this.tareas.size();
    }

    @Override
    public String toString() {
        return "Servicio{" +
                "nombre='" + nombre + '\'' +
                ", tareas=" + tareas +
                '}';
    }

    public ServicioDTO convertirADTO() {
        return new ServicioDTO(this);
    }

    public class ServicioDTO {
        public String nombre;
        public Integer cantTareas;

        public ServicioDTO(Servicio servicio) {
            this.nombre = servicio.nombre;
            this.cantTareas = servicio.cantTareas();
        }
    }
}
