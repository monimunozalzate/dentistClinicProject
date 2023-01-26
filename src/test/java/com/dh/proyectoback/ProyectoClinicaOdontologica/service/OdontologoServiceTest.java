package com.dh.proyectoback.ProyectoClinicaOdontologica.service;

import com.dh.proyectoback.ProyectoClinicaOdontologica.entity.Odontologo;
import com.dh.proyectoback.ProyectoClinicaOdontologica.globalExceptionHandler.BadRequestException;
import com.dh.proyectoback.ProyectoClinicaOdontologica.globalExceptionHandler.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void guardarOdontologo() throws BadRequestException {
        Odontologo aGuardar = new Odontologo("M123","Simon","SinDientes");
        Odontologo guardado = odontologoService.guardarOdontologo(aGuardar);
        assertEquals(1L, guardado.getId());
        assertEquals(aGuardar.getMatricula(), guardado.getMatricula());
        assertEquals(aGuardar.getNombre(), guardado.getNombre());
        assertEquals(aGuardar.getApellido(), guardado.getApellido());
    }

    @Test
    @Order(5)
    void eliminarOdontologoId() throws ResourceNotFoundException {
        Long id = 1L;
        odontologoService.eliminarOdontologoId(id);
        Optional<Odontologo> odontologo = odontologoService.buscarOdontologoId(id);
        assertNotNull(odontologo);
    }

    @Test
    @Order(2)
    void actualizarOdontologo() throws ResourceNotFoundException{
        Long id = 1L;
        Odontologo aActualizar = new Odontologo(id, "M123","Raton","Perez");
        odontologoService.actualizarOdontologo(aActualizar);
        Optional<Odontologo> actualizado = odontologoService.buscarOdontologoId(id);

        assertEquals(id, aActualizar.getId());
        assertEquals("M123", aActualizar.getMatricula());
        assertEquals("Raton", aActualizar.getNombre());
        assertEquals("Perez", aActualizar.getApellido());
    }

    @Test
    @Order(3)
    void buscarOdontologoId() throws BadRequestException {
        Long id= 1L;
        Optional<Odontologo> result = odontologoService.buscarOdontologoId(id);
        assertNotNull(result);
    }

    @Test
    @Order(4)
    void listarOdontologos() {
        List<Odontologo> result = odontologoService.listarOdontologos();
        assertEquals(1, result.size());
    }
}