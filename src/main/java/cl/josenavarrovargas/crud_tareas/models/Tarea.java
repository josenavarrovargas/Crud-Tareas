package cl.josenavarrovargas.crud_tareas.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tarea")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long identificador;

    private String descripcion;
    private LocalDateTime fechaCreacion;
    private boolean vigente;

    public Tarea(Long identificador, String descripcion, LocalDateTime fechaCreacion, boolean vigente) {
        this.identificador = identificador;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.vigente = vigente;
    }

    public Tarea() {
    }

    public Long getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Long identificador) {
        this.identificador = identificador;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean isVigente() {
        return vigente;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }

    @Override
    public String toString() {
        return "Tarea [descripcion=" + descripcion + ", fechaCreacion=" + fechaCreacion + ", identificador="
                + identificador + ", vigente=" + vigente + "]";
    }

}
