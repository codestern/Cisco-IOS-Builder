package at.htlkaindorf.gui;

import at.htlkaindorf.config.utils.VlanConfig;

import javax.swing.*;
import java.awt.event.*;

public class VlanDialog extends JDialog {
    private JPanel pnContent;
    private JButton btOK;
    private JButton btCancel;
    private JTextField tfVlanID;
    private JTextField tfVlanName;

    private VlanConfig config;

    public VlanDialog(VlanConfig config) {
        this.config = config;

        setContentPane(pnContent);
        setModal(true);
        getRootPane().setDefaultButton(btOK);
        initComponents();
        pack();
        setLocationRelativeTo(null);
    }


    private void initComponents() {
        btOK.addActionListener(e -> onOK());
        btCancel.addActionListener(e -> onCancel());

        if (this.config.getVlanID() != VlanConfig.NOT_CONFIGURED_ID) {
            tfVlanID.setText(String.valueOf(this.config.getVlanID()));
            tfVlanName.setText(this.config.getName());
        }

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        pnContent.registerKeyboardAction(e -> onCancel(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        this.config.setName(this.tfVlanName.getText());
        this.config.setVlanID(Integer.parseInt(this.tfVlanID.getText()));

        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
