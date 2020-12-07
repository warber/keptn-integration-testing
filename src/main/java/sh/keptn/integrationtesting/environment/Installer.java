package sh.keptn.integrationtesting.environment;

public interface Installer {

    void install() throws EnvironmentException;

    void uninstall() throws EnvironmentException;
}
