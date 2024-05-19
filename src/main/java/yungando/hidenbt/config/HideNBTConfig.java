package yungando.hidenbt.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.Tooltip;
import yungando.hidenbt.HideNBT;

public class HideNBTConfig {

    public boolean hideNBT() { return true; }
    public boolean hideID() { return false; }
    public boolean hideArmorMaterial() { return true; }
    public boolean removeItalics() { return true; }
    public boolean sortAttributes() { return true; }

    /** Config class requiring AutoConfig */
    @SuppressWarnings("FieldMayBeFinal")
    @Config(name = HideNBT.MODID)
    public static class HideNBTAutoConfig extends HideNBTConfig implements ConfigData {

        @Tooltip private boolean hideNBT = super.hideNBT();
        @Tooltip private boolean hideID = super.hideID();
        @Tooltip private boolean hideArmorMaterial = super.hideArmorMaterial();
        @Tooltip private boolean removeItalics = super.removeItalics();
        @Tooltip private boolean sortAttributes = super.sortAttributes();

        @Override public boolean hideNBT() { return hideNBT; }
        @Override public boolean hideID() { return hideID; }
        @Override public boolean hideArmorMaterial() { return hideArmorMaterial; }
        @Override public boolean removeItalics() { return removeItalics; }
        @Override public boolean sortAttributes() { return sortAttributes; }
    }
}