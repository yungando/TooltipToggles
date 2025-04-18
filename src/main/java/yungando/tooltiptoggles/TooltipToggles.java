package yungando.tooltiptoggles;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import yungando.tooltiptoggles.config.TooltipTogglesConfig;
import yungando.tooltiptoggles.config.TooltipTogglesConfig.TooltipTogglesAutoConfig;

public class TooltipToggles implements ClientModInitializer {
  public static final String MODID = "tooltiptoggles";
  public static final boolean USE_AUTO_CONFIG = FabricLoader.getInstance().isModLoaded("cloth-config2");

  public static TooltipTogglesConfig config;

  @Override
  public void onInitializeClient() {

    if (USE_AUTO_CONFIG) {
      AutoConfig.register(TooltipTogglesAutoConfig.class, GsonConfigSerializer::new);
      config = AutoConfig.getConfigHolder(TooltipTogglesAutoConfig.class).getConfig();
    } else {
      config = new TooltipTogglesConfig();
    }
  }
}