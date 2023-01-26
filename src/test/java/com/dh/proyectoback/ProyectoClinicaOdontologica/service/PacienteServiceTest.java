package com.dh.proyectoback.ProyectoClinicaOdontologica.service;

import com.dh.proyectoback.ProyectoClinicaOdontologica.entity.Domicilio;
import com.dh.proyectoback.ProyectoClinicaOdontologica.entity.Paciente;
import com.dh.proyectoback.ProyectoClinicaOdontologica.globalExceptionHandler.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    void guardarPaciente() {
        Domicilio domicilio = new Domicilio("Calle",3,"Medellin","Medellin");
        Paciente aGuardar = new Paciente("Monica", "Munoz", "123", LocalDate.of(2022,12,11),"movi@gmail.com",domicilio);
        Paciente guardado = pacienteService.guardarPaciente(aGuardar);
        assertEquals(1L, guardado.getId());
        assertEquals(aGuardar.getDni(), guardado.getDni());
        assertEquals(aGuardar.getDomicilio().getCalle(), guardado.getDomicilio().getCalle());
        assertEquals(aGuardar.getDomicilio().getNumero(), guardado.getDomicilio().getNumero());
    }


    @Test
    @Order(2)
    void buscarPacienteId() {
        Long id = 1L;
        Optional<Paciente> paciente = pacienteService.buscarPacienteId(id);
        assertNotNull(paciente);
    }

    @Test
    @Order(3)
    void actualizarPaciente() {
        Long id = 1L;
        Domicilio domicilio = new Domicilio(id,"Calle Montañosa",5678,"Montañas","Bogota");
        LocalDate fecha = LocalDate.now();
        Paciente aActualizar = new Paciente(id,"Alma","Munoz","12", fecha,"lalma@correo.com", domicilio);
        pacienteService.actualizarPaciente(aActualizar);
        Optional<Paciente> actualizado = pacienteService.buscarPacienteId(id);

        assertEquals(id, aActualizar.getId());
        assertEquals("Alma", aActualizar.getNombre());
        assertEquals("Munoz", aActualizar.getApellido());
        assertEquals(fecha, aActualizar.getFechaIngreso());
        assertEquals(aActualizar.getDomicilio().getCalle(), aActualizar.getDomicilio().getCalle());
        assertEquals(aActualizar.getDomicilio().getLocalidad(), aActualizar.getDomicilio().getLocalidad());
    }

    @Test
    @Order(5)
    void eliminarPacienteId() throws ResourceNotFoundException {
        Long id = 1L;
        pacienteService.eliminarPacienteId(id);
        Optional<Paciente> result = pacienteService.buscarPacienteId(id);
        assertNotNull(result);
    }

    @Test
    @Order(4)
    void buscarTodosPacientes() {
        List<Paciente> result = pacienteService.buscarTodosPacientes();
        assertEquals(1, result.size());
    }
}