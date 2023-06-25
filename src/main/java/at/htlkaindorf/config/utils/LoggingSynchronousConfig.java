package at.htlkaindorf.config.utils;

import at.htlkaindorf.config.Config;

public class LoggingSynchronousConfig extends Config {
    private boolean enabled;

    public LoggingSynchronousConfig(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String generateConfigurationString() {
        return "line console 0\n" +
                "logging synchronous";
    }
}
