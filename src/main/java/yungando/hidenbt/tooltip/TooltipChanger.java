package yungando.hidenbt.tooltip;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ArmorItem;
import net.minecraft.text.*;
import net.minecraft.util.Formatting;

import java.util.ArrayList;

public class TooltipChanger
{
    public ArrayList<Text> Main(MinecraftClient client, ItemStack itemStack, ArrayList<Text> list)   
    {
        if (itemStack.hasNbt())
        {
            ArrayList<Text> nbt = new ArrayList<Text>();
            nbt.add(MutableText.of(new TranslatableTextContent("item.nbt_tags", itemStack.getNbt().getKeys().size())).formatted(Formatting.DARK_GRAY));
            int nbtIndex = list.indexOf(nbt.get(0));
            list.remove(nbtIndex);
        }

        if (itemStack.getItem() instanceof ArmorItem armorItem)
        {
            ArrayList<Text> material = new ArrayList<Text>();
            material.add(MutableText.of(new TranslatableTextContent("armorchroma.tooltip.material", armorItem.getMaterial().toString().toLowerCase())).formatted(Formatting.DARK_GRAY));
            int materialIndex = list.indexOf(material.get(0));
            if(materialIndex >= 0)
            {
                list.remove(materialIndex);
            }
        }

        return list;
    }
}
