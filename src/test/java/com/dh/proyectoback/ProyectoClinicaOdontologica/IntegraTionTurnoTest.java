//package com.dh.proyectoback.ProyectoClinicaOdontologica;
//
//import com.dh.proyectoback.ProyectoClinicaOdontologica.dto.TurnoDTO;
//import com.dh.proyectoback.ProyectoClinicaOdontologica.entity.Domicilio;
//import com.dh.proyectoback.ProyectoClinicaOdontologica.entity.Odontologo;
//import com.dh.proyectoback.ProyectoClinicaOdontologica.entity.Paciente;
//import com.dh.proyectoback.ProyectoClinicaOdontologica.service.OdontologoService;
//import com.dh.proyectoback.ProyectoClinicaOdontologica.service.PacienteService;
//import com.dh.proyectoback.ProyectoClinicaOdontologica.service.TurnoService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//
//@SpringBootTest
//@AutoConfigureMockMvc(addFilters = false)
//public class IntegraTionTurnoTest {
//
//    @Autowired
//    private PacienteService pacienteService;
//    @Autowired
//    private OdontologoService odontologoService;
//    @Autowired
//    private TurnoService turnoService;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    private void cargarTurnoInicial(){
//        Domicilio domicilio = new Domicilio("Calle 34 b", 87, "Laureles", "Medellin");
//        Paciente paciente = new Paciente("Martin", "Londono","1234", LocalDate.of(2022, 12, 7), "martin@gmail.com",domicilio);
//        Paciente pacienteGuardado = pacienteService.guardarPaciente(paciente);
//        Odontologo odontologo = new Odontologo("1789","Margarita", "Bermudez");
//        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologo);
//
//        TurnoDTO turnoDTO = new TurnoDTO();
//        turnoDTO.setFecha(LocalDate.of(2022, 12, 07));
//        turnoDTO.setPacienteId(pacienteGuardado.getId());
//        turnoDTO.setOdontologoId(odontologoGuardado.getId());
//        turnoService.guardarTurno(turnoDTO);
//    }
//
//    @Test
//    public void listadoTurnoTest() throws Exception{
//        cargarTurnoInicial();
//        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.get("/turnos").accept(MediaType.APPLICATION_JSON))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
//        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
//    }
//
//}
