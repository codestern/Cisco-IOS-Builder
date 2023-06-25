package at.htlkaindorf.config.utils;

import at.htlkaindorf.config.Config;

public class HostnameConfig extends Config {
    private String hostname;

    public HostnameConfig(String hostname) {
        this.hostname = hostname;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    @Override
    public String generateConfigurationString() {
        return String.format("hostname %s", this.hostname);
    }
}
