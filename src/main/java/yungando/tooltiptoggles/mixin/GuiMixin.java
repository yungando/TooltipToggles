package yungando.tooltiptoggles.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import yungando.tooltiptoggles.TooltipToggles;

@Mixin(Gui.class)
public class GuiMixin {
  @ModifyExpressionValue(method = "extractSelectedItemName", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;has(Lnet/minecraft/core/component/DataComponentType;)Z"))
  private boolean TooltipToggles$removeItalics(boolean original) {
    if (TooltipToggles.config.removeItalics()) return false;

    return original;
  }
}
