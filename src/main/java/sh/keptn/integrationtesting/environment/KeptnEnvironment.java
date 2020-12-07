package sh.keptn.integrationtesting.environment;

public abstract class KeptnEnvironment {

    protected final Installer installer;

    protected KeptnEnvironment(Installer installer) {
        this.installer = installer;
    }

    public abstract void init();

    public abstract void tearDown();

    public abstract KubeClusterConfig getKubeClusterConfig();
}
