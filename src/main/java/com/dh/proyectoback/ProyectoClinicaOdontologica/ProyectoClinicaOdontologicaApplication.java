package com.dh.proyectoback.ProyectoClinicaOdontologica;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProyectoClinicaOdontologicaApplication {

	public static void main(String[] args) {
//		PropertyConfigurator.configure("log4j.properties");
		SpringApplication.run(ProyectoClinicaOdontologicaApplication.class, args);
	}

}
