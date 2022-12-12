package proservices.models.entities.trabajos;

import lombok.Getter;
import lombok.Setter;
import proservices.models.entities.EntidadPersistente;
import proservices.models.entities.actores.Consumidor;
import proservices.models.entities.servicios.ServicioPrestado;
import proservices.models.entities.servicios.Tarea;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trabajo")
@Setter
@Getter
public class Trabajo extends EntidadPersistente {
    @Column(name = "fechaAlta", columnDefinition = "DATE")
    private LocalDate fechaAlta;

    @ManyToOne
    @JoinColumn(name = "consumidor_id")
    private Consumidor consumidor;

    @ManyToOne
    @JoinColumn(name = "servicio_prestado_id")
    private ServicioPrestado servicioPrestado;

    @Column(name = "precio")
    private Double precio;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private PosibleEstadoTrabajo estado;

    @Column(name = "fecha", columnDefinition = "DATE")
    private LocalDate fecha;

    @Column(name = "horaInicio", columnDefinition = "TIME")
    private LocalTime horaIncio;

    @Column(name = "horaFin", columnDefinition = "TIME")
    private LocalTime horaFin;

    @Embedded
    private Direccion direccion;

    @Column(name = "finalizadoSegunPrestador")
    private Boolean finalizadoSegunPrestador;

    @Column(name = "finalizadoSegunConsumidor")
    private Boolean finalizadoSegunConsumidor;

    @ManyToMany
    private List<Tarea> tareas;

    public Trabajo() {
        this.tareas = new ArrayList<>();
    }
}
