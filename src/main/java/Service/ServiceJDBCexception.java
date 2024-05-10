package Service;

public class ServiceJDBCexception extends RuntimeException{
    public ServiceJDBCexception(String message) {
        super(message);
    }

    public ServiceJDBCexception(String message, Throwable cause) {
        super(message, cause);
    }
}
