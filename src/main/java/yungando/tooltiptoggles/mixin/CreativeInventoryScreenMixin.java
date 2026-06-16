package yungando.tooltiptoggles.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import yungando.tooltiptoggles.TooltipToggles;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

@Mixin(CreativeInventoryScreen.class)
public class CreativeInventoryScreenMixin {
  @WrapWithCondition(method = "getTooltipFromItem", at = @At(value = "INVOKE", target = "Ljava/util/List;add(ILjava/lang/Object;)V"))
  private boolean TooltipToggles$hideCreativeCategories(List<?> instance, int i, Object e) {
    return !TooltipToggles.config.hideCreativeCategories();
  }

  @WrapWithCondition(method = "getTooltipFromItem", at = @At(value = "INVOKE", target = "Ljava/util/Set;forEach(Ljava/util/function/Consumer;)V"))
  private static boolean TooltipToggles$hideCreativeTags(Set<?> instance, Consumer<?> consumer) {
    return !TooltipToggles.config.hideCreativeTags();
  }
}
