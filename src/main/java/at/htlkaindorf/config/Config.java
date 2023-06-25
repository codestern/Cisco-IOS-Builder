package at.htlkaindorf.config;

public abstract class Config {

    public abstract String generateConfigurationString();

    @Override
    public boolean equals(Object o) {
        return this.getClass().equals(o.getClass());
    }
}
