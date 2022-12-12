package proservices.models.entities.servicios;

import lombok.Getter;
import lombok.Setter;
import proservices.models.entities.EntidadPersistente;
import proservices.models.entities.actores.Prestador;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "servicio_prestado")
@Setter
@Getter
public class ServicioPrestado extends EntidadPersistente {
    @ManyToOne
    @JoinColumn(name = "servicio_id")
    private Servicio servicio;

    @ManyToOne
    @JoinColumn(name = "prestador_id")
    private Prestador prestador;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "servicio_prestado_foto", joinColumns = @JoinColumn(name = "servicioPrestado_id"))
    @Column(name = "foto")
    private List<String> fotos;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Tarea> tareas;

    public ServicioPrestado() {
        this.fotos = new ArrayList<>();
        this.tareas = new ArrayList<>();
    }
}
