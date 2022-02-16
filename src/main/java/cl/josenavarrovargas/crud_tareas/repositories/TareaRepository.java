package cl.josenavarrovargas.crud_tareas.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.josenavarrovargas.crud_tareas.models.Tarea;

@Repository
public interface TareaRepository extends CrudRepository<Tarea, Long> {
    public abstract ArrayList<Tarea> findByVigente(boolean vigente);
}
