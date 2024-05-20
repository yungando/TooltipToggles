package yungando.hidenbt.tooltip;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.item.ArmorItem;
import net.minecraft.text.*;
import net.minecraft.util.Formatting;
import yungando.hidenbt.HideNBT;

import java.util.ArrayList;
import java.util.Comparator;

public class TooltipChanger
{
    public ArrayList<Text> Main(MinecraftClient client, ItemStack itemStack, ArrayList<Text> list)   
    {
        if (HideNBT.config.hideNBT() && itemStack.hasNbt())
        {
            ArrayList<Text> nbt = new ArrayList<>();
            nbt.add(Text.translatable("item.nbt_tags", itemStack.getNbt().getSize()).formatted(Formatting.DARK_GRAY));
            int nbtIndex = list.indexOf(nbt.get(0));
            if (nbtIndex >= 0)
            {
                list.remove(nbtIndex);
            }
        }

        if(HideNBT.config.hideID())
        {
            ArrayList<Text> id = new ArrayList<>();
            id.add(Text.literal(Registries.ITEM.getId(itemStack.getItem()).toString()).formatted(Formatting.DARK_GRAY));
            int idIndex = list.indexOf(id.get(0));
            if (idIndex >= 0)
            {
                list.remove(idIndex);
            }
        }

        if (HideNBT.config.hideArmorMaterial() && itemStack.getItem() instanceof ArmorItem armorItem)
        {
            ArrayList<Text> material = new ArrayList<>();
            material.add(Text.translatable("armorchroma.tooltip.material", armorItem.getMaterial().toString().toLowerCase()).formatted(Formatting.DARK_GRAY));
            int materialIndex = list.indexOf(material.get(0));
            if (materialIndex >= 0)
            {
                list.remove(materialIndex);
            }
        }

        if (HideNBT.config.removeItalics())
        {
            for (Text tooltip : list)
            {
                RemoveItalics(tooltip);
            }
        }

        if (HideNBT.config.sortAttributes())
        {
            int modifiersIndex = -1;
            for (Text tooltip : list)
            {
                if (tooltip.toString().contains("item.modifiers"))
                {
                    modifiersIndex = list.indexOf(tooltip);
                    break;
                }
            }

            if (modifiersIndex >= 0)
            {
                list = SortAttributes(modifiersIndex, list);
            }
        }

        return list;
    }

    public void RemoveItalics(Text tooltip)
    {
        if (tooltip instanceof MutableText tooltipText)
        {
            if (tooltipText.getStyle().isItalic())
            {
                tooltipText.setStyle(tooltipText.getStyle().withItalic(false));
            }

            for (Text siblingTooltip : tooltipText.getSiblings())
            {
                RemoveItalics(siblingTooltip);
            }
        }
    }

    public ArrayList<Text> SortAttributes(int modifiersIndex, ArrayList<Text> list)
    {
        ArrayList<Text> attributeModifiers = new ArrayList<>();
        for (Text tooltip : list)
        {
            if (tooltip.toString().contains("attribute.modifier"))
            {
                attributeModifiers.add(tooltip);
            }
        }

        list.removeAll(attributeModifiers);

        attributeModifiers.sort(Comparator.comparing(tooltip -> tooltip.toString().split("attribute.name.generic.")[1].split("'")[0]));

        list.addAll(modifiersIndex+1, attributeModifiers);

        return list;
    }
}
