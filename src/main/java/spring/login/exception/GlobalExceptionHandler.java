package spring.login.exception;

        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.ControllerAdvice;
        import org.springframework.web.bind.annotation.ExceptionHandler;
        import org.springframework.web.bind.annotation.ResponseStatus;
        import org.springframework.web.context.request.WebRequest;
        import spring.login.exception.custom.httpConfigurationException;
        import spring.login.exception.custom.InvalidUserException;
        import spring.login.exception.custom.UserAlreadyExistsException;
        import spring.login.exception.custom.UserNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExistsException ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidUserException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Object> handleInvalidUserException(InvalidUserException ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse("An unexpected error occurred", request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(httpConfigurationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleCustomConfigurationException(httpConfigurationException ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
record ExceptionResponse(String message, String details) {}