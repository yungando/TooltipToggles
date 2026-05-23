package yungando.tooltiptoggles.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import yungando.tooltiptoggles.TooltipToggles;

import java.util.List;

@Mixin(CreativeModeInventoryScreen.class)
public class CreativeInventoryMixin {
  @WrapWithCondition(method = "getTooltipFromContainerItem", at = @At(value = "INVOKE", target = "Ljava/util/List;add(ILjava/lang/Object;)V"))
  private boolean TooltipToggles$hideCreativeCategories(List<?> instance, int i, Object e) {
    return !TooltipToggles.config.hideCreativeCategories();
  }

  @WrapWithCondition(method = "lambda$getTooltipFromContainerItem$0", at = @At(value = "INVOKE", target = "Ljava/util/List;add(ILjava/lang/Object;)V"))
  private static boolean TooltipToggles$hideCreativeTags(List<?> instance, int i, Object e) {
    return !TooltipToggles.config.hideCreativeTags();
  }
}
