package com.dh.proyectoback.ProyectoClinicaOdontologica.repository;

import com.dh.proyectoback.ProyectoClinicaOdontologica.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Optional<Usuario> findByEmail(String email);

}
