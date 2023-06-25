package at.htlkaindorf.config.utils;

import at.htlkaindorf.config.Config;

public class LoggingSynchronousConfig extends Config {
    @Override
    public String generateConfigurationString() {
        return "line console 0\n" +
                "logging synchronous";
    }
}
