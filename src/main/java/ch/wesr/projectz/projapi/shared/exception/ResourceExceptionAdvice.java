package ch.wesr.projectz.projapi.shared.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ResourceExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleBusinessValidationException(BusinessValidationException e) {
        log.warn("BusinessValidationException: " + e.getMessage());
        return new ResponseEntity<>("business validation error", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<String> handleBusinessValidationException(ProjectNotFoundException e) {
        log.warn("ProjectNotFoundException: " + e.getMessage());
        return new ResponseEntity<>("Project not found", HttpStatus.NOT_FOUND);
    }

}
