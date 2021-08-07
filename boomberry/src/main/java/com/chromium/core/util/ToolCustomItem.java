package com.chromium.core.util;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ToolCustomItem extends CustomItem {
	
	private static HashMap<String, ToolCustomItem> toolCustomItems = new HashMap<String, ToolCustomItem>();
	
	public ToolCustomItem(Class c, Material material, String name, boolean glow, String... lore) {
		super(c, material, name, glow, lore);
		
		toolCustomItems.put(getKey(super.getItem()), this);
	}
	
	public static ToolCustomItem getItem(ItemStack i) {
		return toolCustomItems.get(getKey(i));
		
	}
}
