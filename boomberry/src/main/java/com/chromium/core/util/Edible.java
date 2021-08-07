package com.chromium.core.util;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public interface Edible {
	
	ItemStack getFood();
	Integer getHunger();
	ArrayList<PotionEffect> getOnEatApply() ;

}
