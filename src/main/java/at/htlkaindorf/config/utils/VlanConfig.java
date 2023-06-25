package at.htlkaindorf.config.utils;

import at.htlkaindorf.config.Config;

public class VlanConfig extends Config {
    public static final int NOT_CONFIGURED_ID = -1;

    private int vlanID;
    private String name;

    public VlanConfig(int vlanID, String name) {
        this.vlanID = vlanID;
        this.name = name;
    }

    public VlanConfig() {
    }

    public int getVlanID() {
        return vlanID;
    }

    public void setVlanID(int vlanID) {
        this.vlanID = vlanID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof VlanConfig vlan) {
            return vlan.vlanID == this.vlanID;
        }

        return super.equals(o);
    }

    @Override
    public String generateConfigurationString() {
        return String.format("vlan %d\n" +
                "name %s", this.vlanID, this.name);
    }

    @Override
    public String toString() {
        return String.format("VLAN %d (%s)", this.vlanID, this.name);
    }

}
