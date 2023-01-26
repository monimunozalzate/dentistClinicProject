package com.dh.proyectoback.ProyectoClinicaOdontologica.service;

import com.dh.proyectoback.ProyectoClinicaOdontologica.dto.TurnoDTO;
import com.dh.proyectoback.ProyectoClinicaOdontologica.entity.Odontologo;
import com.dh.proyectoback.ProyectoClinicaOdontologica.entity.Paciente;
import com.dh.proyectoback.ProyectoClinicaOdontologica.entity.Turno;
import com.dh.proyectoback.ProyectoClinicaOdontologica.repository.OdontologoRepository;
import com.dh.proyectoback.ProyectoClinicaOdontologica.repository.TurnoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    private static final Logger LOGGER = Logger.getLogger(TurnoService.class);
   public TurnoRepository turnoRepository;

   @Autowired
    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    private TurnoDTO turnoATurnoDTO(Turno turno){
       //convertir turno en turnoDTO
        TurnoDTO respuesta =  new TurnoDTO();
        respuesta.setId(turno.getId());
        respuesta.setFecha(turno.getFecha());
        respuesta.setOdontologoId(turno.getOdontologo().getId());
        respuesta.setPacienteId(turno.getPaciente().getId());
        return respuesta;
    }

    private Turno turnoDTOaTurno(TurnoDTO turnoDTO){
       //convertir de turnoDTO a turno
        Turno turno = new Turno();
        Paciente paciente = new Paciente();
        Odontologo odontologo = new Odontologo();

        //cargar lo elementos
        paciente.setId(turnoDTO.getPacienteId());
        odontologo.setId(turnoDTO.getOdontologoId());
        turno.setId(turnoDTO.getId());
        turno.setFecha(turnoDTO.getFecha());

        //asociar los elemnetos
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);

        return turno;
    }

    public TurnoDTO guardarTurno(TurnoDTO turno){
       Turno turnoAGuardar = turnoDTOaTurno(turno);
       Turno turnoGuardado = turnoRepository.save(turnoAGuardar);
       LOGGER.info("Se ha iniciado el proceso de guardado del turno. ");
        return turnoATurnoDTO(turnoGuardado);
    }
    public Optional<TurnoDTO> buscarTurnoId(Long id){
       Optional<Turno> turnoBuscado = turnoRepository.findById(id);
       if (turnoBuscado.isPresent()){
           LOGGER.info("Se ha inicializado el proceso de busqueda del turno con id: "+ id);
           return Optional.of(turnoATurnoDTO(turnoBuscado.get()));
       }else {
           return Optional.empty();
       }
    }
    public void actualizarTurno(TurnoDTO turno){
       Turno turnoAActualizar = turnoDTOaTurno(turno);
       LOGGER.info("Se ha inicializado el proceso de actualizacion del turno con id: "+turno.getId());
       turnoRepository.save(turnoAActualizar);
    }
    public void eliminarTurno(Long id){
       LOGGER.warn("Se ha eliminado el turno con id:"+id);
       turnoRepository.deleteById(id);
    }
    public List<TurnoDTO> buscarTodosTurnos(){
        List<Turno> turnosEncontrados =  turnoRepository.findAll();
        List<TurnoDTO> respuesta = new ArrayList<>();
        LOGGER.info("Se ha inicializado el proceso de listado de todos los turnos en base de datos.");
        for (Turno t: turnosEncontrados){
            respuesta.add(turnoATurnoDTO(t));
        }
        return respuesta;
    }


}
