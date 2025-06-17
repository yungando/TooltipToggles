package yungando.tooltiptoggles.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.text.MutableText;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.client.gui.hud.InGameHud;
import yungando.tooltiptoggles.TooltipToggles;

@Mixin(InGameHud.class)
public class InGameHudMixin {
  @WrapWithCondition(method = "renderHeldItemTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/text/MutableText;formatted(Lnet/minecraft/util/Formatting;)Lnet/minecraft/text/MutableText;", ordinal = 1))
  private boolean TooltipToggles$removeItalics(MutableText instance, Formatting formatting) {
    return !TooltipToggles.config.removeItalics();
  }
}
