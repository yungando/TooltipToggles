package yungando.tooltiptoggles.mixin;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import yungando.tooltiptoggles.TooltipToggles;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class ItemStackMixin {
  @Inject(method = "getTooltipLines", at = @At("RETURN"), cancellable = true)
  protected void TooltipToggles$editTooltip(Item.TooltipContext context, Player player, TooltipFlag type, CallbackInfoReturnable<List<Component>> cir) {
    cir.setReturnValue(TooltipToggles.tooltipEditor((ItemStack) (Object) this, type, cir.getReturnValue()));
  }
}
