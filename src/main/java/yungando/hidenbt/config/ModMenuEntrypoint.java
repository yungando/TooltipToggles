package yungando.hidenbt.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import yungando.hidenbt.HideNBT;
import yungando.hidenbt.config.HideNBTConfig.HideNBTAutoConfig;

public class ModMenuEntrypoint implements ModMenuApi {

    private static final ConfigScreenFactory<?> FACTORY = HideNBT.USE_AUTO_CONFIG
            ? parent -> AutoConfig.getConfigScreen(HideNBTAutoConfig.class, parent).get()
            : parent -> null;
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return FACTORY;
    }
}