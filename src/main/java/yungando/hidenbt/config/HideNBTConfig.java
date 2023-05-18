package yungando.hidenbt.config;

import dev.isxander.yacl.api.*;
import dev.isxander.yacl.config.ConfigEntry;
import dev.isxander.yacl.config.ConfigInstance;
import dev.isxander.yacl.config.GsonConfigInstance;
import dev.isxander.yacl.gui.controllers.TickBoxController;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.text.Text;

public class HideNBTConfig {
    public static final ConfigInstance<HideNBTConfig> INSTANCE = new GsonConfigInstance<>(
            HideNBTConfig.class,
            FabricLoader.getInstance().getConfigDir().resolve("hidenbt.json")
    );

    @ConfigEntry
    public boolean hideNBT = true;
    @ConfigEntry
    public boolean hideID = false;
    @ConfigEntry
    public boolean hideArmorMaterial = true;

    public static ConfigCategory getConfigCategory() {
        HideNBTConfig config = INSTANCE.getConfig();
        HideNBTConfig defaults = INSTANCE.getDefaults();

        return ConfigCategory.createBuilder()
                .name(Text.translatable("hidenbt.title"))
                .option(Option.createBuilder(boolean.class)
                        .name(Text.translatable("hidenbt.config.hidenbt.title"))
                        .tooltip(Text.translatable("hidenbt.config.hidenbt.desc"))
                        .binding(
                                defaults.hideNBT,
                                () -> config.hideNBT,
                                val -> config.hideNBT = val
                        )
                        .controller(TickBoxController::new)
                        .build())
                .option(Option.createBuilder(boolean.class)
                        .name(Text.translatable("hidenbt.config.hideid.title"))
                        .tooltip(Text.translatable("hidenbt.config.hideid.desc"))
                        .binding(
                                defaults.hideID,
                                () -> config.hideID,
                                val -> config.hideID = val
                        )
                        .controller(TickBoxController::new)
                        .build())
                .option(Option.createBuilder(boolean.class)
                        .name(Text.translatable("hidenbt.config.hidearmormat.title"))
                        .tooltip(Text.translatable("hidenbt.config.hidearmormat.desc"))
                        .binding(
                                defaults.hideArmorMaterial,
                                () -> config.hideArmorMaterial,
                                val -> config.hideArmorMaterial = val
                        )
                        .controller(TickBoxController::new)
                        .build())
                .build();
    }
}