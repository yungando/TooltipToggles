package yungando.hidenbt;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import yungando.hidenbt.config.HideNBTConfig;
import yungando.hidenbt.config.HideNBTConfig.HideNBTAutoConfig;

public class HideNBT implements ClientModInitializer {

    public static final String MODID = "hidenbt";
    public static final boolean USE_AUTO_CONFIG = FabricLoader.getInstance().isModLoaded("cloth-config2");

    public static HideNBTConfig config;

    @Override
    public void onInitializeClient() {

        if(USE_AUTO_CONFIG)
        {
            AutoConfig.register(HideNBTAutoConfig.class, GsonConfigSerializer::new);
            config = AutoConfig.getConfigHolder(HideNBTAutoConfig.class).getConfig();
        }
        else
        {
            config = new HideNBTConfig();
        }
    }
}