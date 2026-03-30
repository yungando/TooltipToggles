package yungando.tooltiptoggles;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import yungando.tooltiptoggles.config.TooltipTogglesConfig;
import yungando.tooltiptoggles.config.TooltipTogglesConfig.TooltipTogglesAutoConfig;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TooltipToggles implements ClientModInitializer {
  public static final String MODID = "tooltiptoggles";
  public static final boolean USE_AUTO_CONFIG = FabricLoader.getInstance().isModLoaded("cloth-config2");
  public static TooltipTogglesConfig config;

  @Override
  public void onInitializeClient() {
    if (USE_AUTO_CONFIG) {
      AutoConfig.register(TooltipTogglesAutoConfig.class, GsonConfigSerializer::new);
      config = AutoConfig.getConfigHolder(TooltipTogglesAutoConfig.class).getConfig();
    } else {
      config = new TooltipTogglesConfig();
    }
  }

  private static void removeItalics(Component tooltip) {
    if (tooltip instanceof MutableComponent tooltipText) {
      if (tooltipText.getStyle().isItalic()) {
        tooltipText.setStyle(tooltipText.getStyle().withItalic(false));
      }

      for (Component siblingTooltip : tooltipText.getSiblings()) {
        removeItalics(siblingTooltip);
      }
    }
  }

  private static List<Component> sortAttributes(int modifiersIndex, List<Component> list) {
    ArrayList<Component> attributeModifiers = new ArrayList<>();
    for (Component tooltip : list) {
      if (tooltip.toString().contains("attribute.modifier")) {
        attributeModifiers.add(tooltip);
        if (attributeModifiers.size() > 2) {
          return list;
        }
      }
    }

    list.removeAll(attributeModifiers);
    attributeModifiers.sort(Comparator.comparing(tooltip -> tooltip.toString().split("attribute.name.")[1].split("'")[0]));
    list.addAll(modifiersIndex + 1, attributeModifiers);

    return list;
  }

  public static List<Component> tooltipEditor(ItemStack itemStack, TooltipFlag type, List<Component> list) {
    if (TooltipToggles.config.removeItalics()) {
      for (Component tooltip : list) {
        removeItalics(tooltip);
      }
    }

    if (TooltipToggles.config.sortAttributes()) {
      int modifiersIndex = -1;
      for (Component tooltip : list) {
        if (tooltip.toString().contains("item.modifiers")) {
          modifiersIndex = list.indexOf(tooltip);
          break;
        }
      }

      if (modifiersIndex >= 0) {
        list = sortAttributes(modifiersIndex, list);
      }
    }

    if (!type.isAdvanced()) { return list; }

    if (TooltipToggles.config.hideComponents() && !itemStack.getComponents().isEmpty()) {
      ArrayList<Component> components = new ArrayList<>();
        components.add(Component.translatable("item.components", itemStack.getComponents().size()).withStyle(ChatFormatting.DARK_GRAY));
      int componentsIndex = list.indexOf(components.getFirst());
      if (componentsIndex >= 0) {
        list.remove(componentsIndex);
      }
    }

    if (TooltipToggles.config.hideID()) {
      ArrayList<Component> id = new ArrayList<>();
      id.add(Component.literal(BuiltInRegistries.ITEM.getKey(itemStack.getItem()).toString()).withStyle(ChatFormatting.DARK_GRAY));
      int idIndex = list.indexOf(id.getFirst());

      if (idIndex >= 0) {
        list.remove(idIndex);
      }
    }

    return list;
  }
}