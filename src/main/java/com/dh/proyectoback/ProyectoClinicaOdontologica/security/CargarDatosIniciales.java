package com.dh.proyectoback.ProyectoClinicaOdontologica.security;

import com.dh.proyectoback.ProyectoClinicaOdontologica.entity.Usuario;
import com.dh.proyectoback.ProyectoClinicaOdontologica.entity.UsuarioRole;
import com.dh.proyectoback.ProyectoClinicaOdontologica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CargarDatosIniciales implements ApplicationRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //cargar usuario para probar
        BCryptPasswordEncoder cifrador = new BCryptPasswordEncoder();
        String passwordCifrado = cifrador.encode("digital");

        Usuario usuario = new Usuario("Monica", "Monica",
                "monica@gmail.com", passwordCifrado, UsuarioRole.ROLE_USER);
        usuarioRepository.save(usuario);

        Usuario usuarioAdmin = new Usuario("admin", "admin", "admin@gmail.com", passwordCifrado, UsuarioRole.ROLE_ADMIN);
        usuarioRepository.save(usuarioAdmin);
    }

}
