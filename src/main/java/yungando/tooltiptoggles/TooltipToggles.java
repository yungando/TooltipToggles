package yungando.tooltiptoggles;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
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

  private static void removeItalics(Text tooltip) {
    if (tooltip instanceof MutableText tooltipText) {
      if (tooltipText.getStyle().isItalic()) {
        tooltipText.setStyle(tooltipText.getStyle().withItalic(false));
      }

      for (Text siblingTooltip : tooltipText.getSiblings()) {
        removeItalics(siblingTooltip);
      }
    }
  }

  private static List<Text> sortAttributes(int modifiersIndex, List<Text> list) {
    ArrayList<Text> attributeModifiers = new ArrayList<>();
    for (Text tooltip : list) {
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

  public static List<Text> tooltipEditor(ItemStack itemStack, TooltipType type, List<Text> list) {
    if (TooltipToggles.config.removeItalics()) {
      for (Text tooltip : list) {
        removeItalics(tooltip);
      }
    }

    if (TooltipToggles.config.sortAttributes()) {
      int modifiersIndex = -1;
      for (Text tooltip : list) {
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
      ArrayList<Text> components = new ArrayList<>();
      assert itemStack.getComponents() != null;
      components.add(Text.translatable("item.components", itemStack.getComponents().size()).formatted(Formatting.DARK_GRAY));
      int componentsIndex = list.indexOf(components.getFirst());
      if (componentsIndex >= 0) {
        list.remove(componentsIndex);
      }
    }

    if (TooltipToggles.config.hideID()) {
      ArrayList<Text> id = new ArrayList<>();
      id.add(Text.literal(Registries.ITEM.getId(itemStack.getItem()).toString()).formatted(Formatting.DARK_GRAY));
      int idIndex = list.indexOf(id.getFirst());

      if (idIndex >= 0) {
        list.remove(idIndex);
      }
    }

    return list;
  }
}