package com.chromium.core.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class EdibleCustomItem extends CustomItem implements Edible {

	private static HashMap<String, EdibleCustomItem> edibleCustomItems = new HashMap<String, EdibleCustomItem>();
	
	private Integer hunger;
	private ArrayList<PotionEffect> onEatApply;
	private Consumer<Player> onEatConsumer;
	
	public EdibleCustomItem(Class c, Material material, String name, boolean glow, Integer hunger, Consumer<Player> onEatConsumer, ArrayList<PotionEffect> onEatApply, String... lore) {
		super(c, material, name, glow, lore);
		this.hunger = hunger;
		this.onEatApply = onEatApply;
		this.onEatConsumer = onEatConsumer;
		edibleCustomItems.put(getKey(getItem()), this);
	}

	@Override
	public Integer getHunger() {
		return hunger;
	}

	@Override
	public ArrayList<PotionEffect> getOnEatApply() {
		return onEatApply;
	}
	
	@Override
	public ItemStack getFood() {
		return super.getItem();
	}
	
	public Consumer<Player> getOnEatConsumer(){
		return onEatConsumer;
	}
	
	public static EdibleCustomItem getEdibleCustomItem(ItemStack item) {
		return edibleCustomItems.get(getKey(item));
	}

}
