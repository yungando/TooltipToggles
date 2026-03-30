package yungando.tooltiptoggles.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Gui;
import net.minecraft.network.chat.MutableComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import yungando.tooltiptoggles.TooltipToggles;

@Mixin(Gui.class)
public class GuiMixin {
  @WrapWithCondition(method = "extractSelectedItemName", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/MutableComponent;withStyle(Lnet/minecraft/ChatFormatting;)Lnet/minecraft/network/chat/MutableComponent;", ordinal = 1))
  private boolean TooltipToggles$removeItalics(MutableComponent instance, ChatFormatting formatting) {
    return !TooltipToggles.config.removeItalics();
  }
}
