package at.htlkaindorf.config.utils;

import at.htlkaindorf.config.Config;

public class InitialConfig extends Config {
    @Override
    public String generateConfigurationString() {
        return "enable\n" +
                "configure terminal";
    }
}
