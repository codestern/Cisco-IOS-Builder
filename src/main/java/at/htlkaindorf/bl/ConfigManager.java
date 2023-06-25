package at.htlkaindorf.bl;

import at.htlkaindorf.config.Config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigManager {
    private final List<Config> configs;

    public ConfigManager() {
        this.configs = new ArrayList<>();
    }

    public void addConfig(Config config) {
        if (!this.configs.contains(config)) {
            this.configs.add(config);
        } else {
            updateConfig(config);
        }
    }

    public void removeConfig(Config config) {
        this.configs.remove(config);
    }

    public void updateConfig(Config config) {
        int index = configs.indexOf(config);

        if (index != -1) {
            configs.set(index, config);
        }
    }

    public String generateConfigScript() {
        StringBuilder output = new StringBuilder();

        for (Config c : this.configs) {
            output.append(c.generateConfigurationString()).append("\n!\n");
        }

        return output.toString();
    }

    public void exportConfigScript(File file) throws IOException {
        String script = this.generateConfigScript();
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        writer.write(script);
        writer.close();
    }

}
