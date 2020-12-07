package sh.keptn.integrationtesting.environment;

public class EnvironmentException extends RuntimeException {
    public EnvironmentException() {
        super();
    }

    public EnvironmentException(String s) {
        super(s);
    }

    public EnvironmentException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public EnvironmentException(Throwable throwable) {
        super(throwable);
    }
}


