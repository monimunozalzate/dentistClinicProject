package com.dh.proyectoback.ProyectoClinicaOdontologica.controller;

import com.dh.proyectoback.ProyectoClinicaOdontologica.entity.Paciente;
import com.dh.proyectoback.ProyectoClinicaOdontologica.globalExceptionHandler.ResourceNotFoundException;
import com.dh.proyectoback.ProyectoClinicaOdontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
//@CrossOrigin(origins = "*")
public class PacienteController {

    private PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listaPacientes(){
        return ResponseEntity.ok(pacienteService.buscarTodosPacientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> pacienteBuscar(@PathVariable Long id){
        Optional<Paciente> result = pacienteService.buscarPacienteId(id);
        if (result.isPresent()){
            return ResponseEntity.ok(result.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Paciente> registrarNuevoPaciente(@RequestBody Paciente paciente){
        Paciente pacienteGuardado = pacienteService.guardarPaciente(paciente);
        if (pacienteGuardado.getId() != null){
            return ResponseEntity.ok(pacienteGuardado);
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<String > actualizarPaciente (@RequestBody Paciente paciente){
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacienteId(paciente.getId());
        if (pacienteBuscado.isPresent()){
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("Se ha actualizado el paciente con nombre y apellido "+paciente.getNombre()+" "+paciente.getApellido() );
        }else {
            return ResponseEntity.badRequest().body("El paciente con id: "+paciente.getId()+" no existe en la Base de Datos.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String > eliminarPaciente(@PathVariable Long id)throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacienteId(id);
        if (pacienteBuscado.isPresent()){
            pacienteService.eliminarPacienteId(id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Se ha eliminado el paciente con id: "+id);
        }else {
            throw new ResourceNotFoundException("No se ha encontrado el paciente con el id: "+id+". Verifique que si existe.");
//            return ResponseEntity.badRequest().body("No se ha encontrado el paciente con el id: "+id+". Verifique que si existe.");
        }
    }

}
