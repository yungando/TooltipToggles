package yungando.tooltiptoggles.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import yungando.tooltiptoggles.config.TooltipTogglesConfig.TooltipTogglesAutoConfig;

public class ModMenuEntrypoint implements ModMenuApi {
  @Override
  public ConfigScreenFactory<?> getModConfigScreenFactory() {
    return parent -> AutoConfig.getConfigScreen(TooltipTogglesAutoConfig.class, parent).get();
  }
}