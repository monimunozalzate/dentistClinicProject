package com.dh.proyectoback.ProyectoClinicaOdontologica.globalExceptionHandler;


import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

  private static final Logger LOGGER = Logger.getLogger(GlobalException.class);

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<?> todosLosErrorres(Exception ex, WebRequest req){
//        logger.error(ex.getMessage());
//        return new ResponseEntity("Error: "+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<String> procesarResourceNotFoundException(ResourceNotFoundException rnfe){
        LOGGER.error("Error, el sistema detecto un problema. Se ha registrado el siguiente mensaje: "+
                rnfe.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> procesarBadRequestException(BadRequestException badRequestException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(badRequestException.getMessage());
    }

}
