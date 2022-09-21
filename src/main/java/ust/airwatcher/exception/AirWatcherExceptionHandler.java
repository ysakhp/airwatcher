package ust.airwatcher.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AirWatcherExceptionHandler {

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity userNotFoundException(UserNotFoundException userNotFoundException) {
        return new ResponseEntity("Given username not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UsernameAlreadyExistException.class)
    public ResponseEntity usernameAlreadyExistException(UsernameAlreadyExistException usernameAlreadyExistException) {
        return new ResponseEntity("Username already exist", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = CityAlreadyExistException.class)
    public ResponseEntity cityAlreadyExistException(CityAlreadyExistException cityAlreadyExistException) {
        return new ResponseEntity("City already exist", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = RestClientAccessException.class)
    public ResponseEntity restClientAccessException(RestClientAccessException cityAlreadyExistException) {
        return new ResponseEntity("Unable to connect with external API", HttpStatus.SERVICE_UNAVAILABLE);
    }

}
