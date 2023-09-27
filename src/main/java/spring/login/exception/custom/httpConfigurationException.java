package spring.login.exception.custom;

public class httpConfigurationException extends RuntimeException {
    public httpConfigurationException() {super("There was a configuration error with the HTTP request");
    }
}

