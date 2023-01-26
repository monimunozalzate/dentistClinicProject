package com.dh.proyectoback.ProyectoClinicaOdontologica.globalExceptionHandler;


public class BadRequestException extends Exception{

  public BadRequestException(String msn){
      super(msn);
  }

}
