package com.dh.proyectoback.ProyectoClinicaOdontologica.service;

import com.dh.proyectoback.ProyectoClinicaOdontologica.entity.Odontologo;
import com.dh.proyectoback.ProyectoClinicaOdontologica.entity.Paciente;
import com.dh.proyectoback.ProyectoClinicaOdontologica.globalExceptionHandler.ResourceNotFoundException;
import com.dh.proyectoback.ProyectoClinicaOdontologica.repository.PacienteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private static final Logger LOGGER = Logger.getLogger(PacienteService.class);
    private PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente guardarPaciente (Paciente paciente){
        LOGGER.info("Se ha iniciado el guardado. ");
        return pacienteRepository.save(paciente);

    }
    public Optional<Paciente> buscarPacienteId (Long id){
        LOGGER.info("Se ha iniciado el proceso de busqueda del paciente con id: "+id);
        return pacienteRepository.findById(id);
    }
    public Paciente actualizarPaciente(Paciente paciente){
        LOGGER.info("Se ha iniciado la actualizacion del paciente con id: "+paciente.getId());
        return pacienteRepository.save(paciente);
    }
    public void eliminarPacienteId(Long id) throws ResourceNotFoundException{

            Optional<Paciente> pacienteAEliminar = buscarPacienteId(id);
            if (pacienteAEliminar.isPresent()){
                pacienteRepository.deleteById(id);
                LOGGER.warn("se ha eliminado el paciente con id: "+ id);
            }else {
                throw new ResourceNotFoundException("El paciente a eliminar no existe en la base de datos.");
            }
        }


    public List<Paciente> buscarTodosPacientes(){
        LOGGER.info("Se estan listando todos lo pacientes.");
        return pacienteRepository.findAll();
    }
    public Optional<Paciente> buscarPacienteEmail (String email) {
        return  pacienteRepository.findByEmail(email);}
}
