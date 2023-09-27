package spring.login.exception.custom;

public class InvalidUserException extends RuntimeException {
    public InvalidUserException() {
        super("User information is either null or invalid");
    }
}
