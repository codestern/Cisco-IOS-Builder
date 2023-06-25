package at.htlkaindorf.config.utils;

import at.htlkaindorf.config.Config;

public class BannerConfig extends Config {
    private String banner;

    public BannerConfig(String banner) {
        this.banner = banner;
    }

    @Override
    public String generateConfigurationString() {
        return String.format("banner motd # %s #", this.banner);
    }
}
