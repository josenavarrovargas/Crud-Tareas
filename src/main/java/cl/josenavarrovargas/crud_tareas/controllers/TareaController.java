package cl.josenavarrovargas.crud_tareas.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.josenavarrovargas.crud_tareas.models.Tarea;
import cl.josenavarrovargas.crud_tareas.services.TareaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/tarea")
public class TareaController {
    
    @Autowired
    TareaService tareaService;

    @ApiOperation(value = "Obtiene la tarea por su identificador", 
        notes = "Si la tarea no existe,. se devolverá null. ", 
        response = Tarea.class)
    @ApiResponses({
        @ApiResponse(code = 200, response = Tarea.class, message = "OK")
    })
    @GetMapping(path = "/identificador/{identificador:.*}")
    public Optional<Tarea> obtenerTareaPorId(@PathVariable("identificador") Long identificador) {
        return tareaService.obtenerTareaPorId(identificador);
    }

    @ApiOperation(value = "Obtiene todas las tareas de la base de datos", 
        notes = "Si no existen tareas almacenadas, retorna una lista vacía. ", 
        response = Tarea.class, 
        responseContainer = "List")
    @ApiResponses({
        @ApiResponse(code = 200, response = Tarea.class, message = "OK", responseContainer = "List")
    })
    @GetMapping(path = "/tareas")
    public ArrayList<Tarea> obtenerTareas() {
        return tareaService.obtenerTareas();
    }

    @ApiOperation(value = "Obtiene todas las tareas de la base de datos, según su vigencia. ", 
        notes = "El valor por defecto de vigente es \"true\". Si no existen tareas almacenadas, retorna una lista vacía. ", 
        response = Tarea.class, 
        responseContainer = "List")
    @ApiResponses({
        @ApiResponse(code = 200, response = Tarea.class, message = "OK", responseContainer = "List")
    })
    @GetMapping(path = "/tareas/vigencia")
    public ArrayList<Tarea> obtenerTareasPorVigencia(@RequestParam(defaultValue = "true") boolean vigente) {
        return tareaService.obtenerTareasPorVigencia(vigente);
    }

    @ApiOperation(value = "Guarda una tarea en la BD. ", 
        notes = "Debe entregar todos los campos de la tarea. Puede obviar la Fecha de Creación, y se configurará la fecha actual del servidor. ", 
        response = Tarea.class)
    @ApiResponses({
        @ApiResponse(code = 200, response = Tarea.class, message = "OK")
    })
    @PostMapping(path = "/guardar-tarea")
    public Tarea guardarTarea(@RequestBody Tarea tarea) {
        if (null == tarea.getFechaCreacion()) {
            tarea.setFechaCreacion(LocalDateTime.now());
        }

        return tareaService.guardarTarea(tarea);
    }

    @ApiOperation(value = "Actualiza una tarea. ", 
        notes = "Debe ingresar todos los campos de la tarea a actualizar. ", 
        response = Tarea.class)
    @ApiResponses({
        @ApiResponse(code = 200, response = Tarea.class, message = "OK"), 
        @ApiResponse(code = 400, response = String.class, message = "BAD_REQUEST")
    })
    @PutMapping(path = "/actualizar-tarea")
    public ResponseEntity<?> actualizarTarea(@RequestBody Tarea tarea) {
        Tarea tareaObtenida = tareaService.actualizarTarea(tarea);

        if (null == tareaObtenida) {
            return ResponseEntity.badRequest().body("No se pudo actualizar la tarea con identificador " + tarea.getIdentificador() + " porque no existe. ");
        }

        return ResponseEntity.ok(tareaObtenida);
    }

    @ApiOperation(value = "Elimina una tarea de la BD. ", 
        notes = "Debe ingresar el identificador de una tarea existente, de lo contrario generará un BAD_REQUEST. ", 
        response = Tarea.class)
    @ApiResponses({
        @ApiResponse(code = 200, response = String.class, message = "OK"), 
        @ApiResponse(code = 400, response = String.class, message = "BAD_REQUEST")
    })
    @DeleteMapping(path = "/eliminar-tarea/{identificador:.*}")
    public ResponseEntity<?> eliminarTarea(@PathVariable("identificador") Long identificador) {
        if (tareaService.eliminarTarea(identificador)) {
            return ResponseEntity.ok("Tarea con identificador " + identificador + " eliminada correctamente. ");
        }

        return ResponseEntity.badRequest().body("No se pudo eliminar la tarea con identificador " + identificador + ". ");
    }

}
