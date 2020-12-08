package sh.keptn.integrationtesting.environment;

/**
 * @author warber
 **/
public class DownloadFailedException extends Exception {
    public DownloadFailedException() {
        super();
    }

    public DownloadFailedException(String s) {
        super(s);
    }

    public DownloadFailedException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public DownloadFailedException(Throwable throwable) {
        super(throwable);
    }
}
