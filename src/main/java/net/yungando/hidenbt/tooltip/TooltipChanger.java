package net.yungando.hidenbt.tooltip;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.text.*;
import net.minecraft.util.Formatting;

import java.util.ArrayList;

public class TooltipChanger
{
    public ArrayList<Text> Main(MinecraftClient client, ItemStack itemStack, ArrayList<Text> list)
    {        
        ArrayList<Text> temp = new ArrayList<Text>();

        temp.add((new TranslatableText("item.nbt_tags", itemStack.getNbt().getKeys().size())).formatted(Formatting.DARK_GRAY));
        int index = list.indexOf(temp.get(0));
        list.remove(index);

        return list;
    }
}