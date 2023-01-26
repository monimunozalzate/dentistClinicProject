package com.dh.proyectoback.ProyectoClinicaOdontologica.controller;

import com.dh.proyectoback.ProyectoClinicaOdontologica.dto.TurnoDTO;
//import com.dh.proyectoback.ProyectoClinicaOdontologica.globalExceptionHandler.BadRequestException;
import com.dh.proyectoback.ProyectoClinicaOdontologica.globalExceptionHandler.BadRequestException;
import com.dh.proyectoback.ProyectoClinicaOdontologica.globalExceptionHandler.ResourceNotFoundException;
import com.dh.proyectoback.ProyectoClinicaOdontologica.service.OdontologoService;
import com.dh.proyectoback.ProyectoClinicaOdontologica.service.PacienteService;
import com.dh.proyectoback.ProyectoClinicaOdontologica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
@CrossOrigin(origins = "*")
public class TurnoController {

    private TurnoService turnoService;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;

    @Autowired
    public TurnoController(TurnoService turnoService, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @GetMapping
    public ResponseEntity<List<TurnoDTO>> listaTurnos() {
        return ResponseEntity.ok(turnoService.buscarTodosTurnos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscarTurnoId(@PathVariable Long id) {
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurnoId(id);
        if (turnoBuscado.isPresent()) {
            return ResponseEntity.ok(turnoBuscado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

   @PostMapping
    public ResponseEntity<TurnoDTO> registrarTurno(@RequestBody TurnoDTO turno)  {
      ResponseEntity<TurnoDTO> respuesta;

      if (pacienteService.buscarPacienteId(turno.getPacienteId()).isPresent() &&
      odontologoService.buscarOdontologoId(turno.getOdontologoId()).isPresent()){
         respuesta= ResponseEntity.ok(turnoService.guardarTurno(turno));
      }else {
          return ResponseEntity.badRequest().build();

     }
      return respuesta;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String > eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        if (turnoService.buscarTurnoId(id).isPresent()){
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Se ha eliminado el turno con id: "+id);
        }else {
            throw new ResourceNotFoundException("No se encontró el turno con id: "+id);
//            return ResponseEntity.badRequest().body("No se encontró el turno con id: "+id);
        }
    }
    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody TurnoDTO turno){
        if (turnoService.buscarTurnoId(turno.getId()).isPresent()){
            if (pacienteService.buscarPacienteId(turno.getPacienteId()).isPresent() &&
            odontologoService.buscarOdontologoId(turno.getOdontologoId()).isPresent()){
                turnoService.actualizarTurno(turno);
                return ResponseEntity.ok("El turno con id: "+turno.getId()+" fue actualizado exitosamente.");
            }else {
                return ResponseEntity.badRequest().body("Error al actualizar el turno. Rectifiue la informacion");
            }
        }else {
            return ResponseEntity.badRequest().body("Error al actualizar. El turno no se encuentra en base de datos.");
        }
    }


}



