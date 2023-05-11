package yungando.hidenbt.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ArmorItem;
import net.minecraft.text.*;
import yungando.hidenbt.tooltip.TooltipChanger;

import java.util.ArrayList;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class TooltipChangerMixin
{
	@Inject(at = @At("RETURN"), method = "getTooltip", cancellable = true)
	protected void editTooltip(PlayerEntity player, TooltipContext context, CallbackInfoReturnable<ArrayList<Text>> info)
	{
		if (context.isAdvanced())
		{
			MinecraftClient client = MinecraftClient.getInstance();
			ItemStack itemStack = ( ItemStack ) ( Object ) this;
			ArrayList<Text> list = info.getReturnValue();

			if (itemStack.hasNbt() || itemStack.getItem() instanceof ArmorItem)
			{
				TooltipChanger tooltipMain = new TooltipChanger();
				info.setReturnValue(tooltipMain.Main(client, itemStack, list));
			}
		}
	}
}