package proservices.models.entities.actores;

import lombok.Getter;
import lombok.Setter;
import proservices.converters.DiaDeLaSemanaConverter;
import proservices.models.entities.EntidadPersistente;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table(name = "prestador_disponibilidad")
@Setter
@Getter
public class Disponibilidad extends EntidadPersistente {
    @ManyToOne
    @JoinColumn(name = "prestador_id")
    private Prestador prestador;

    @Column(name = "horaInicio", columnDefinition = "TIME")
    private LocalTime horaInicio;

    @Column(name = "horaFin", columnDefinition = "TIME")
    private LocalTime horaFin;

    @Convert(converter = DiaDeLaSemanaConverter.class)
    private DayOfWeek dia;
}
