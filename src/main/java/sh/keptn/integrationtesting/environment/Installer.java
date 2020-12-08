package sh.keptn.integrationtesting.environment;

/**
 * @author warber
 **/
public interface Installer {

    void install() throws EnvironmentException;

    void uninstall() throws EnvironmentException;
}
