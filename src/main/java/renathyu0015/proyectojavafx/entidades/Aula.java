package renathyu0015.proyectojavafx.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "aulas",
    // No se permiten dos aulas con el mismo nombre en el mismo edificio
    uniqueConstraints = @UniqueConstraint(
        name = "uk_aulas_nombre_edificio",
        columnNames = {"nombre", "edificio"}
    )
)
public class Aula {

    // ---------- Enums ----------
    // Los nombres (NORMAL, ASIGNADA...) deben coincidir letra por letra
    // con los ENUM de las columnas tipo_aula y estado en la base de datos

    public enum TipoAula {
        NORMAL("Normal"),
        CENTRO_DE_COMPUTO("Centro de cómputo"),
        LABORATORIO("Laboratorio"),
        ESPECIAL("Especial");

        private final String descripcion;

        TipoAula(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }

        // El ComboBox usa toString() para decidir qué texto mostrar
        @Override
        public String toString() {
            return descripcion;
        }
    }

    public enum EstadoAula {
        ASIGNADA("Asignada"),
        ASIGNADA_TEMPORALMENTE("Asignada temporalmente"),
        EN_MANTENIMIENTO("En mantenimiento"),
        DISPONIBLE("Disponible");

        private final String descripcion;

        EstadoAula(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }

        @Override
        public String toString() {
            return descripcion;
        }
    }

    // ---------- Atributos (columnas) ----------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "edificio", nullable = false, length = 50)
    private String edificio;

    @Column(name = "planta", nullable = false)
    private int planta;

    @Column(name = "capacidad", nullable = false)
    private int capacidad;

    // Se guarda el NOMBRE del enum (ej. "LABORATORIO"), no un número
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_aula", nullable = false)
    private TipoAula tipoAula;

    @Column(name = "responsable", nullable = false, length = 100)
    private String responsable;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoAula estado;

    // ---------- Constructores ----------

    // Hibernate necesita un constructor vacío para crear el objeto al leer de la BD
    public Aula() {
    }

    // Para crear un aula nueva desde el formulario (sin id: lo genera la BD)
    public Aula(String nombre, String edificio, int planta, int capacidad,
                TipoAula tipoAula, String responsable, EstadoAula estado) {
        this.nombre = nombre;
        this.edificio = edificio;
        this.planta = planta;
        this.capacidad = capacidad;
        this.tipoAula = tipoAula;
        this.responsable = responsable;
        this.estado = estado;
    }

    // ---------- Getters y setters ----------

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEdificio() { return edificio; }
    public void setEdificio(String edificio) { this.edificio = edificio; }

    public int getPlanta() { return planta; }
    public void setPlanta(int planta) { this.planta = planta; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    public TipoAula getTipoAula() { return tipoAula; }
    public void setTipoAula(TipoAula tipoAula) { this.tipoAula = tipoAula; }

    public String getResponsable() { return responsable; }
    public void setResponsable(String responsable) { this.responsable = responsable; }

    public EstadoAula getEstado() { return estado; }
    public void setEstado(EstadoAula estado) { this.estado = estado; }

    @Override
    public String toString() {
        return nombre + " (" + edificio + ")";
    }
}