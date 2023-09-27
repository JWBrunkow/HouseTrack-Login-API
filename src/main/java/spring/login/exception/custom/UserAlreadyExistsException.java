package spring.login.exception.custom;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super("A user with this information already exists");
    }
}
