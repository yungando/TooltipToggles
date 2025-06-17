package yungando.tooltiptoggles.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.*;
import yungando.tooltiptoggles.TooltipToggles;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class TooltipMixin {
  @Inject(method = "getTooltip", at = @At("RETURN"), cancellable = true)
  protected void TooltipToggles$editTooltip(Item.TooltipContext context, PlayerEntity player, TooltipType type, CallbackInfoReturnable<List<Text>> cir) {
    cir.setReturnValue(TooltipToggles.tooltipEditor((ItemStack) (Object) this, type, cir.getReturnValue()));
  }
}
