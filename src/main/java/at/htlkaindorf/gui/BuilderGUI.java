package at.htlkaindorf.gui;

import at.htlkaindorf.bl.ConfigManager;
import at.htlkaindorf.config.Config;
import at.htlkaindorf.config.utils.*;
import at.htlkaindorf.exception.InvalidConfigException;
import at.htlkaindorf.model.VlanListModel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class BuilderGUI extends JFrame {
    private JPanel pnContent;
    private JTextArea taOutput;
    private JTextField tfHostname;
    private JButton btSetHostname;
    private JCheckBox cbLoggingSync;
    private JCheckBox cbPasswordEncryption;
    private JButton btSetBanner;
    private JTextField tfBanner;
    private JButton btRemoveHostname;
    private JButton btRemoveBanner;
    private JList<VlanConfig> lsVlanConfigs;

    private ConfigManager configs;
    private VlanListModel vlanListModel;


    // Vlan PopUp Menu
    private JPopupMenu pmVlanConfigs;
    private JMenuItem miCreateVlanConfig;
    private JMenuItem miUpdateVlanConfig;
    private JMenuItem miRemoveVlanConfig;

    public BuilderGUI(String title) {
        setTitle(title);
        setContentPane(this.pnContent);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        createPopUpMenus();
        initComponents();
        pack();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        this.configs = new ConfigManager();
        this.configs.addConfig(new InitialConfig());
        printCurrentConfig();

        this.vlanListModel = new VlanListModel();
        this.lsVlanConfigs.setModel(this.vlanListModel);

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

        this.btSetBanner.addActionListener(e -> {
            try {
                onSetBanner();
            } catch (InvalidConfigException ex) {
                JOptionPane.showMessageDialog(
                        null,
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });


        // Add PopUpMenu to List
        lsVlanConfigs.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    pmVlanConfigs.show(
                            e.getComponent(),
                            e.getX(),
                            e.getY()
                    );
                }
            }
        });


        this.btRemoveBanner.addActionListener(e -> onRemoveBanner());
        this.btRemoveHostname.addActionListener(e -> onRemoveHostname());
        this.cbLoggingSync.addActionListener(e -> onAddLoggingSynchronous());
        this.cbPasswordEncryption.addActionListener(e -> onAddPasswordEncryption());

        this.miCreateVlanConfig.addActionListener(e -> onAddVlanConfig());
        this.miRemoveVlanConfig.addActionListener(e -> onRemoveVlanConfig());
        this.miUpdateVlanConfig.addActionListener(e -> onUpdateVlanConfig());
    }

    private void createPopUpMenus() {
        // VlanConfig PopUp Menu
        this.pmVlanConfigs = new JPopupMenu("VLAN Configs");
        this.miCreateVlanConfig = new JMenuItem("Create VLAN");
        this.miRemoveVlanConfig = new JMenuItem("Remove VLAN");
        this.miUpdateVlanConfig = new JMenuItem("Update VLAN");

        this.pmVlanConfigs.add(miCreateVlanConfig);
        this.pmVlanConfigs.add(miRemoveVlanConfig);
        this.pmVlanConfigs.add(miUpdateVlanConfig);
    }

    private void onSetHostname() throws InvalidConfigException {
        String hostname = this.tfHostname.getText();
        HostnameConfig config = new HostnameConfig(hostname);

        if (hostname.isBlank()) {
            throw new InvalidConfigException("Please enter the hostname first!");
        }

        this.configs.addConfig(config);

        printCurrentConfig();
    }

    private void onAddPasswordEncryption() {
        PasswordEncryptionConfig config = new PasswordEncryptionConfig();

        if (this.cbPasswordEncryption.isSelected()) {
            this.configs.addConfig(config);
        } else {
            this.configs.removeConfig(config);
        }

        printCurrentConfig();
    }

    private void onAddVlanConfig() {
        VlanConfig config = new VlanConfig(VlanConfig.NOT_CONFIGURED_ID, "");

        VlanDialog dialog = new VlanDialog(config);

        dialog.setVisible(true);


        if (config.getVlanID() != VlanConfig.NOT_CONFIGURED_ID) {
            this.configs.addConfig(config);
            this.vlanListModel.addVlanConfig(config);
            printCurrentConfig();
        }
    }

    private void onSetBanner() throws InvalidConfigException {
        String banner = this.tfBanner.getText();

        if (banner.isBlank()) {
            throw new InvalidConfigException("Please Input your banner!");
        }

        BannerConfig config = new BannerConfig(banner);

        this.configs.addConfig(config);

        printCurrentConfig();
    }

    private void onRemoveHostname() {
        this.configs.removeConfig(new HostnameConfig(""));
        printCurrentConfig();
    }

    private void onRemoveBanner() {
        this.configs.removeConfig(new BannerConfig(""));
        printCurrentConfig();
    }

    private void onAddLoggingSynchronous() {
        LoggingSynchronousConfig config = new LoggingSynchronousConfig();

        if (this.cbLoggingSync.isSelected()) {
            this.configs.addConfig(config);
        } else {
            this.configs.removeConfig(config);
        }

        printCurrentConfig();
    }

    private void onRemoveVlanConfig() {
        VlanConfig config = this.lsVlanConfigs.getSelectedValue();

        if (config != null) {
            this.configs.removeConfig(config);
            this.vlanListModel.removeVlanConfig(config);
            printCurrentConfig();
        }
    }

    private void onUpdateVlanConfig() {
        VlanConfig config = this.lsVlanConfigs.getSelectedValue();

        if (config != null) {
            VlanDialog dialog = new VlanDialog(config);
            dialog.setVisible(true);

            this.configs.updateConfig(config);
            this.vlanListModel.updateVlan(config);

            printCurrentConfig();
        }
    }

    private void printCurrentConfig() {
        this.taOutput.setText(configs.generateConfigScript());
    }

    public static void main(String[] args) {
        BuilderGUI gui = new BuilderGUI("[V.0.0.1] Cisco IOS Builder (by @codestern)");
        gui.setVisible(true);
    }
}
