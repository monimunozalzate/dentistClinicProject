package com.dh.proyectoback.ProyectoClinicaOdontologica.controller;

import com.dh.proyectoback.ProyectoClinicaOdontologica.entity.Odontologo;
import com.dh.proyectoback.ProyectoClinicaOdontologica.globalExceptionHandler.ResourceNotFoundException;
import com.dh.proyectoback.ProyectoClinicaOdontologica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
//@CrossOrigin(origins = "*")
public class odontologoController {

    public OdontologoService odontologoService;

    @Autowired
    public odontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> listaOdontologos (){
        return ResponseEntity.ok(odontologoService.listarOdontologos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologoId(@PathVariable Long id){
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoId(id);
        if (odontologoBuscado.isPresent()){
            return ResponseEntity.ok(odontologoBuscado.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Odontologo> registrarNuevoOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    @PutMapping
    public ResponseEntity<String> actualizarOdontologo (@RequestBody Odontologo odontologo){
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoId(odontologo.getId());
        if (odontologoBuscado.isPresent()){
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("Se ha actualizado el odontologo con nombre y apellido: "+odontologo.getNombre()+" "+odontologo.getApellido());
        }else {
            return ResponseEntity.badRequest().body("El odontologo con id "+ odontologo.getId()+" no existe.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoId(id);
        if (odontologoBuscado.isPresent()){
            odontologoService.eliminarOdontologoId(id);
            return ResponseEntity.ok("Se ha eliminado el odontologo con id: "+id);
        }else {
            throw new ResourceNotFoundException("No se ha encontrado el paciente con el id: "+id+". Verifique que si existe.");
//            return ResponseEntity.notFound().build();
        }
    }

}
