package at.htlkaindorf.gui;

import at.htlkaindorf.config.Config;
import at.htlkaindorf.config.utils.HostnameConfig;
import at.htlkaindorf.config.utils.LoggingSynchronousConfig;
import at.htlkaindorf.exception.InvalidConfigException;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class BuilderGUI extends JFrame {
    private JPanel pnContent;
    private JTextArea taOutput;
    private JTextField tfHostname;
    private JButton btSetHostname;
    private JCheckBox cbLoggingSync;
    private JCheckBox cbPasswordEncryption;

    private List<Config> configs;

    public BuilderGUI(String title) {
        setTitle(title);
        setContentPane(this.pnContent);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        pack();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        this.configs = new ArrayList<>();

        this.btSetHostname.addActionListener(e -> {
            try {
                onSetHostname();
            } catch (InvalidConfigException ex) {
                JOptionPane.showMessageDialog(
                        null,
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

    }

    private void onSetHostname() throws InvalidConfigException {
        String hostname = this.tfHostname.getText();
        HostnameConfig config = new HostnameConfig(hostname);

        if (hostname.isBlank()) {
            throw new InvalidConfigException("Please enter the hostname first!");
        }

        if (!this.configs.contains(config)) {
            this.configs.add(config);
        } else {
            this.configs.remove(config);
            this.configs.add(config);
        }

        printCurrentConfig();
    }


    private void printCurrentConfig() {
        StringBuilder output = new StringBuilder();

        this.taOutput.setText("");

        output.append("enable\n").append("configure terminal\n!\n");

        for (Config c : this.configs) {
            output.append(c.generateConfigurationString()).append("\n!\n");
        }

        this.taOutput.setText(output.toString());
    }

    public static void main(String[] args) {
        BuilderGUI gui = new BuilderGUI("[V.0.0.1] Cisco IOS Builder (by @codestern)");
        gui.setVisible(true);
    }
}
