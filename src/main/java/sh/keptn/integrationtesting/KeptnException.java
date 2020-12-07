package sh.keptn.integrationtesting;

/**
 * @author warber
 **/
public class KeptnException extends RuntimeException {
    public KeptnException() {
        super();
    }

    public KeptnException(String s) {
        super(s);
    }

    public KeptnException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public KeptnException(Throwable throwable) {
        super(throwable);
    }
}
