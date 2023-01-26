package com.dh.proyectoback.ProyectoClinicaOdontologica.service;

import com.dh.proyectoback.ProyectoClinicaOdontologica.entity.Odontologo;
import com.dh.proyectoback.ProyectoClinicaOdontologica.globalExceptionHandler.ResourceNotFoundException;
import com.dh.proyectoback.ProyectoClinicaOdontologica.repository.OdontologoRepository;
import org.apache.log4j.Logger;
import org.hibernate.boot.model.source.spi.RelationalValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {
    private OdontologoRepository odontologoRepository;
    private static final Logger LOGGER = Logger.getLogger(OdontologoService.class);

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    public Odontologo guardarOdontologo (Odontologo odontologo){
        LOGGER.info("Se ha iniciado el guardado del odontologo con id, nombre y apellido: "+odontologo.getId()+""+ odontologo.getNombre()+" "+odontologo.getApellido());
        return odontologoRepository.save(odontologo);
    }
    public void eliminarOdontologoId (Long id)throws ResourceNotFoundException {
        Optional<Odontologo> odontologoAEliminar = buscarOdontologoId(id);
        if (odontologoAEliminar.isPresent()){
            odontologoRepository.deleteById(id);
            LOGGER.warn("se ha eliminado el odontologo con id: "+ id);
        }else {
            throw new ResourceNotFoundException("El odontologo a eliminar no existe en la base de datos.");
        }
    }
    public void actualizarOdontologo(Odontologo odontologo){
        LOGGER.info("Se ha iniciado la actualizacion del odontologo con id: " +odontologo.getId());
        odontologoRepository.save(odontologo);
    }
    public Optional<Odontologo> buscarOdontologoId(long id){
        LOGGER.info("se inicio la busqueda del odontologo con id: "+id);
        return odontologoRepository.findById(id);
    }
    public List<Odontologo> listarOdontologos(){
        LOGGER.info("Se ha iniciado la operacino de listar todos los odontologos");
        return odontologoRepository.findAll();
    }

}
