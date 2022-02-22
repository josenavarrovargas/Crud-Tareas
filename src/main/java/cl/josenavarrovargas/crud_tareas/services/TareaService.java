package cl.josenavarrovargas.crud_tareas.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.josenavarrovargas.crud_tareas.models.Tarea;
import cl.josenavarrovargas.crud_tareas.repositories.TareaRepository;

@Service
public class TareaService {
    
    @Autowired
    TareaRepository tareaRepository;

    public Optional<Tarea> obtenerTareaPorId(Long identificador) {
        return (Optional<Tarea>) tareaRepository.findById(identificador);
    }

    public ArrayList<Tarea> obtenerTareas() {
        return (ArrayList<Tarea>) tareaRepository.findAll();
    }

    public ArrayList<Tarea> obtenerTareasPorVigencia(boolean vigente) {
        return tareaRepository.findByVigente(vigente);
    }

    public Tarea guardarTarea(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    public Tarea actualizarTarea(Tarea tarea) {
        if (null == tarea.getIdentificador() || tarea.getIdentificador() == 0L) {
            return null;
        }

        Optional<Tarea> tareaObtenida = tareaRepository.findById(tarea.getIdentificador());

        if (tareaObtenida.isEmpty()) {
            return null;
        }

        tarea.setFechaCreacion(tareaObtenida.get().getFechaCreacion());

        return tareaRepository.save(tarea);
    }

    public boolean eliminarTarea(Long identificador) {
        try {
            tareaRepository.deleteById(identificador);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
}
