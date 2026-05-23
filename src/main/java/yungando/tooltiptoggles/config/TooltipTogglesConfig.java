package yungando.tooltiptoggles.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.Tooltip;
import yungando.tooltiptoggles.TooltipToggles;

public class TooltipTogglesConfig {
  public boolean hideComponents() { return true; }
  public boolean hideID() { return false; }
  public boolean hideCreativeCategories() { return false; }
  public boolean hideCreativeTags() { return false; }
  public boolean removeItalics() { return true; }
  public boolean sortAttributes() { return true; }

  @SuppressWarnings("FieldMayBeFinal")
  @Config(name = TooltipToggles.MODID)
  public static class TooltipTogglesAutoConfig extends TooltipTogglesConfig implements ConfigData {
    @Tooltip
    private boolean hideComponents = super.hideComponents();
    @Tooltip
    private boolean hideID = super.hideID();
    @Tooltip
    private boolean hideCreativeCategories = super.hideCreativeCategories();
    @Tooltip
    private boolean hideCreativeTags = super.hideCreativeTags();
    @Tooltip
    private boolean removeItalics = super.removeItalics();
    @Tooltip
    private boolean sortAttributes = super.sortAttributes();

    @Override
    public boolean hideComponents() { return hideComponents; }
    @Override
    public boolean hideID() {  return hideID; }
    @Override
    public boolean hideCreativeCategories() { return hideCreativeCategories; }
    @Override
    public boolean hideCreativeTags() { return hideCreativeTags; }
    @Override
    public boolean removeItalics() { return removeItalics; }
    @Override
    public boolean sortAttributes() { return sortAttributes; }
  }
}
