package at.htlkaindorf.bl;

import at.htlkaindorf.config.Config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ConfigManager
 * Class for storing all the Configurations and build the complete Script
**/
public class ConfigManager {
    private final List<Config> configs;

    public ConfigManager() {
        this.configs = new ArrayList<>();
    }

    /**
     * Method to add a configuration to the manager.
     * If the configuration already exists, it will be updated
     * @param config The configuration to update or insert
    **/
    public void addConfig(Config config) {
        if (!this.configs.contains(config)) {
            this.configs.add(config);
        } else {
            updateConfig(config);
        }
    }

    /**
     * Method to remove a configuration from the manager
     * @param config The configuration to remove
     */
    public void removeConfig(Config config) {
        this.configs.remove(config);
    }

    /**
     * Method to update a configuration from the manager
     * @param config The configuration to update
     */
    public void updateConfig(Config config) {
        int index = configs.indexOf(config);

        if (index != -1) {
            configs.set(index, config);
        }
    }

    /**
     * Methode to generate the configuration script based on the stored configs
     * @return String containing the configuration script
     */
    public String generateConfigScript() {
        StringBuilder output = new StringBuilder();

        for (Config c : this.configs) {
            output.append(c.generateConfigurationString()).append("\n!\n");
        }

        return output.toString();
    }

    /**
     * Writes the script to an inserted file
     * @param file the file to write the script to
     * @throws IOException
     */
    public void exportConfigScript(File file) throws IOException {
        String script = this.generateConfigScript();
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        writer.write(script);
        writer.close();
    }
}
