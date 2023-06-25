package at.htlkaindorf.config;

/**
 * Abstract class to represent a specific config
 */
public abstract class Config {
    public abstract String generateConfigurationString();

    @Override
    public boolean equals(Object o) {
        return this.getClass().equals(o.getClass());
    }
}
