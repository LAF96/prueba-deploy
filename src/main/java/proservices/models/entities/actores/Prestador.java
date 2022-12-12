package proservices.models.entities.actores;

import lombok.Getter;
import lombok.Setter;
import proservices.models.entities.EntidadPersistente;
import proservices.models.entities.actores.mediosnotificacion.EMedioNotificacion;
import proservices.models.entities.actores.mediosnotificacion.FactoryMedioNotificacion;
import proservices.models.entities.actores.mediosnotificacion.MedioDeNotificacion;
import proservices.models.entities.actores.reputaciones.Reputacion;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "prestador")
@Setter
@Getter
public class Prestador extends EntidadPersistente {
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "foto")
    private String foto;

    @Column(name = "documento")
    private String documento;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoDocumento")
    private TipoDocumento tipoDocumento;

    @Column(name = "fechaNacimiento", columnDefinition = "DATE")
    private LocalDate fechaNacimiento;

    @Column(name = "cuitCuil")
    private String cuitCuil;

    @Column(name = "radioCobertura")
    private Double radioCobertura;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "prestador_email", joinColumns = @JoinColumn(name = "prestador_id"))
    @Column(name = "email")
    private Set<String> emails;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "prestador_telefono", joinColumns = @JoinColumn(name = "prestador_id"))
    @Column(name = "telefono")
    private Set<String> telefonos;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "prestador", cascade = CascadeType.ALL)
    private List<Disponibilidad> disponibilidades;

    @OneToOne
    private Reputacion reputacion;

    @Transient
    private MedioDeNotificacion medioDeNotificacion;

    @Enumerated(EnumType.STRING)
    private EMedioNotificacion eMedioNotificacion;

    public Prestador() {
        this.emails = new LinkedHashSet<>();
        this.telefonos = new LinkedHashSet<>();
        this.disponibilidades = new ArrayList<>();
    }

    public void serNotificado(String mensaje) {
        this.medioDeNotificacion = FactoryMedioNotificacion.obtenerMedio(this.eMedioNotificacion);
        this.medioDeNotificacion.enviarNotificacion("dadas", mensaje);
    }
}
