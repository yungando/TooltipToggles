package yungando.tooltiptoggles.mixin;

import net.minecraft.component.ComponentType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.item.ItemStack;
import yungando.tooltiptoggles.TooltipToggles;

@Mixin(InGameHud.class)
public class GameHudMixin {
  @Redirect(method = "renderHeldItemTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;contains(Lnet/minecraft/component/ComponentType;)Z"))
  public boolean removeItalics(ItemStack instance, ComponentType<?> CUSTOM_NAME) {
    return !TooltipToggles.config.removeItalics();
  }
}
