package com.dh.proyectoback.ProyectoClinicaOdontologica.service;

import com.dh.proyectoback.ProyectoClinicaOdontologica.dto.TurnoDTO;
import com.dh.proyectoback.ProyectoClinicaOdontologica.entity.Domicilio;
import com.dh.proyectoback.ProyectoClinicaOdontologica.entity.Odontologo;
import com.dh.proyectoback.ProyectoClinicaOdontologica.entity.Paciente;
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

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class TurnoServiceTest {

    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private TurnoService turnoService;

    @Test
    @Order(1)
    void guardarTurno() {
        Domicilio domicilio = new Domicilio("Callecita",777,"Laureles","MEdellin");
        Paciente paciente = new Paciente("Agus","Londono","669", LocalDate.now(),"agus@correo.com", domicilio);
        Odontologo odontologo = new Odontologo("MAT111","Ada","DeLosDientes");
        odontologoService.guardarOdontologo(odontologo);
        pacienteService.guardarPaciente(paciente);

        Domicilio dom = new Domicilio("Calle",3,"miCasa","Medellin");
        Paciente pac = new Paciente("Laura","Mora","985", LocalDate.now(),"laura@correo.com", dom);
        Odontologo odo = new Odontologo("MAT0987","Pablo","Caries");
        odontologoService.guardarOdontologo(odo);
        pacienteService.guardarPaciente(pac);
    }

    @Test
    @Order(2)
    void buscarTurnoId() {
        Long ID = 1L;
        Optional<TurnoDTO> result = turnoService.buscarTurnoId(ID);
        assertNotNull(result);    }

    @Test
    @Order(3)
    void actualizarTurno() {
        Long ID = 1L;
        LocalDate date = LocalDate.now();
        TurnoDTO aActualizar = new TurnoDTO(ID, LocalDate.of(2022, 12,12),2L, 2L);
        turnoService.actualizarTurno(aActualizar);
        Optional<TurnoDTO> actualizado = turnoService.buscarTurnoId(ID);
        assertEquals(ID, aActualizar.getId());
        assertEquals(date,aActualizar.getFecha());
        assertEquals(2L, aActualizar.getOdontologoId());
        assertEquals(2L,aActualizar.getPacienteId());
}
    @Test
    @Order(5)
    void eliminarTurno() {
            Long ID = 1L;
            turnoService.eliminarTurno(ID);
            Optional<TurnoDTO> result = turnoService.buscarTurnoId(ID);
        assertNotNull(result);
    };


    @Test
    @Order(4)
    void buscarTodosTurnos() {
            List<TurnoDTO> result = turnoService.buscarTodosTurnos();
        assertEquals(1, result.size());    }
}