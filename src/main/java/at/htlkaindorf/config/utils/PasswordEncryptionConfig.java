package at.htlkaindorf.config.utils;

import at.htlkaindorf.config.Config;

public class PasswordEncryptionConfig extends Config {
    @Override
    public String generateConfigurationString() {
        return "service password-encryption";
    }
}
