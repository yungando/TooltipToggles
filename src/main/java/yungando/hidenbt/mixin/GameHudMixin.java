package yungando.hidenbt.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.item.ItemStack;
import yungando.hidenbt.HideNBT;

@Mixin (InGameHud.class)
public class GameHudMixin
{
    @Redirect(method = "renderHeldItemTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;hasCustomName()Z"))
    public boolean removeItalics(ItemStack stack)
    {
        return !HideNBT.config.removeItalics();
    }
}
