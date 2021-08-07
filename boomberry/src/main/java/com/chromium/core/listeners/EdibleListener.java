package com.chromium.core.listeners;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import com.chromium.core.util.CustomItem;
import com.chromium.core.util.Edible;
import com.chromium.core.util.EdibleCustomItem;

public class EdibleListener implements Listener {
	
	@SuppressWarnings("unchecked")
	@EventHandler
	public void onEat(PlayerItemConsumeEvent e) {
		Player p = e.getPlayer();
		
		EdibleCustomItem custom = null;
		
		if(e.getItem().equals(p.getEquipment().getItemInHand())) {
			custom = EdibleCustomItem.getEdibleCustomItem(p.getEquipment().getItemInHand());
		}
		
		if(custom == null) {
			return;
		} else {
			p.getItemInHand().setAmount(p.getItemInHand().getAmount()-1);
			int newHunger = p.getFoodLevel()+custom.getHunger();
			p.setFoodLevel(newHunger > 20 ? 20 : newHunger);
			custom.getOnEatApply().forEach( (effect) -> {
				p.addPotionEffect(effect);
			});
			e.setItem(p.getItemInHand());
			custom.getOnEatConsumer().accept(p);
			e.setCancelled(true);
			}
		}
	}
