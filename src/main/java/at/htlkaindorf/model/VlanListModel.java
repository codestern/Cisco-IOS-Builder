package at.htlkaindorf.model;

import at.htlkaindorf.config.utils.VlanConfig;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class VlanListModel extends AbstractListModel<VlanConfig> {
    private List<VlanConfig> vlanConfigs;

    public VlanListModel() {
        this.vlanConfigs = new ArrayList<>();
    }

    public void addVlanConfig(VlanConfig config) {
        if (!this.vlanConfigs.contains(config)) {
            this.vlanConfigs.add(config);
            fireIntervalAdded(
                    this,
                    0,
                    vlanConfigs.size() - 1
            );
        }
    }

    public void removeVlanConfig(VlanConfig config) {
        this.vlanConfigs.remove(config);
        fireIntervalRemoved(
                this,
                0,
                vlanConfigs.size() == 0 ? 0 : vlanConfigs.size() - 1
        );
    }


    public void updateVlan(VlanConfig config) {
        int index = this.vlanConfigs.indexOf(config);

        if (index != -1) {
            this.vlanConfigs.set(index, config);
        }
    }

    @Override
    public int getSize() {
        return this.vlanConfigs.size();
    }

    @Override
    public VlanConfig getElementAt(int index) {
        return this.vlanConfigs.get(index);
    }
}
