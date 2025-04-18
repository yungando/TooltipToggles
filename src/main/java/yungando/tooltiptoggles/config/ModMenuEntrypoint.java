package yungando.tooltiptoggles.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import yungando.tooltiptoggles.TooltipToggles;
import yungando.tooltiptoggles.config.TooltipTogglesConfig.TooltipTogglesAutoConfig;

public class ModMenuEntrypoint implements ModMenuApi {
  private static final ConfigScreenFactory<?> FACTORY = TooltipToggles.USE_AUTO_CONFIG
      ? parent -> AutoConfig.getConfigScreen(TooltipTogglesAutoConfig.class, parent).get()
      : parent -> null;

  @Override
  public ConfigScreenFactory<?> getModConfigScreenFactory() {
    return FACTORY;
  }
}