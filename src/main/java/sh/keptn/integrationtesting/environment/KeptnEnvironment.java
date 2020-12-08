package sh.keptn.integrationtesting.environment;

/**
 * @author warber
 **/
public abstract class KeptnEnvironment {

    protected final Installer installer;

    protected KeptnEnvironment(Installer installer) {
        this.installer = installer;
    }

    public abstract void init();

    public abstract void tearDown();
}
