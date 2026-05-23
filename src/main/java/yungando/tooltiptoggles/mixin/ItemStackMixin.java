package yungando.tooltiptoggles.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Slice;
import yungando.tooltiptoggles.TooltipToggles;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
  @WrapWithCondition(
    method = "addDetailsToTooltip",
    slice = @Slice(
      from = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/TooltipFlag;isAdvanced()Z"),
      to = @At(value = "INVOKE", target = "Lnet/minecraft/core/component/PatchedDataComponentMap;size()I")
    ),
    at = @At(value = "INVOKE", target = "Ljava/util/function/Consumer;accept(Ljava/lang/Object;)V", ordinal = 1),
    require = 1
  )
  private boolean TooltipToggles$hideId(Consumer<?> instance, Object t) {
    return !TooltipToggles.config.hideID();
  }

  @WrapWithCondition(
    method = "addDetailsToTooltip",
    slice = @Slice(
      from = @At(value = "INVOKE", target = "Lnet/minecraft/core/component/PatchedDataComponentMap;size()I")
    ),
    at = @At(value = "INVOKE", target = "Ljava/util/function/Consumer;accept(Ljava/lang/Object;)V", ordinal = 0),
    require = 1
  )
  private boolean TooltipToggles$hideComponents(Consumer<?> instance, Object t) {
    return !TooltipToggles.config.hideComponents();
  }

  @ModifyExpressionValue(method = "getStyledHoverName", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;has(Lnet/minecraft/core/component/DataComponentType;)Z"))
  private boolean TooltipToggles$removeItalics(boolean original) {
    if (TooltipToggles.config.removeItalics()) return false;

    return original;
  }

  @ModifyVariable(method = "forEachModifier(Lnet/minecraft/world/entity/EquipmentSlotGroup;Lorg/apache/commons/lang3/function/TriConsumer;)V", at = @At(value = "STORE"), name = "modifiers")
  private ItemAttributeModifiers TooltipToggles$sortModifiers(ItemAttributeModifiers modifiers) {
    if (!TooltipToggles.config.sortAttributes()) return modifiers;

    List<ItemAttributeModifiers.Entry> sorted = new ArrayList<>(modifiers.modifiers());

    sorted.sort(Comparator.comparing(entry -> {
      Identifier id = BuiltInRegistries.ATTRIBUTE.getKey(entry.attribute().value());
      return id == null ? "" : id.toString();
    }));

    return new ItemAttributeModifiers(sorted);
  }
}
