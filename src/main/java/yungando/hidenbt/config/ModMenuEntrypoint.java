package yungando.hidenbt.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.isxander.yacl.api.YetAnotherConfigLib;
import net.minecraft.text.Text;

public class ModMenuEntrypoint implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> YetAnotherConfigLib.createBuilder()
                .title(Text.translatable("hidenbt.title"))
                .category(HideNBTConfig.getConfigCategory())
                .save(HideNBTConfig.INSTANCE::save)
                .build().generateScreen(parent);
    }
}