package com.chromium.core.listeners;

import java.util.function.Consumer;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.chromium.core.util.CustomItem;
import com.chromium.core.util.ToolCustomItem;

public class ItemListener implements Listener {
	
	private Consumer<PlayerInteractEvent> onRightClickConsumer;
	private Consumer<EntityShootBowEvent> onBowFireConsumer;
	private Consumer<ProjectileLaunchEvent> onThrowConsumer;
	
	public void setOnRightClickConsumer(Consumer<PlayerInteractEvent> e) {
		this.onRightClickConsumer = e;
	}
	
	public Consumer<PlayerInteractEvent> getOnRightClickConsumer() {
		return onRightClickConsumer;
	}
	
	@EventHandler
	public void onRightClick(PlayerInteractEvent e) {
		if(e.getItem() == null)
			return;
		if(e.getItem().getType().equals(Material.AIR))
			return;
		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
			CustomItem custom = verifyCustomItem(e.getItem());
			ToolCustomItem customTool = ToolCustomItem.getItem(e.getItem());
			if(custom != null) {
				if(custom.getOnRightClickConsumer() != null) {
					custom.getOnRightClickConsumer().accept(e);
					return;
				}
			}
			if(customTool != null) {
				if(customTool.getOnRightClickConsumer() != null) {
					customTool.getOnRightClickConsumer().accept(e);
					return;
				}
			}
		}
	}
	
	public void setOnBowFireConsumer(Consumer<EntityShootBowEvent> e) {
		this.onBowFireConsumer = e;
	}
	
	public Consumer<EntityShootBowEvent> getOnBowFireConsumer() {
		return onBowFireConsumer;
	}
	
	@EventHandler
	public void onBowFire(EntityShootBowEvent e) {
		ToolCustomItem custom = ToolCustomItem.getItem(e.getBow());
		if(custom != null && custom.getOnBowFireConsumer() != null) {
			custom.getOnBowFireConsumer().accept(e);
		}
	}
	
	public void setOnThrowConsumer(Consumer<ProjectileLaunchEvent> e) {
		this.onThrowConsumer = e;
	}
	
	public Consumer<ProjectileLaunchEvent> getOnThrowConsumer() {
		return onThrowConsumer;
	}
	
	@EventHandler
	public void onThrow(ProjectileLaunchEvent e) {
		if (e.getEntity().getShooter() instanceof Player) {
		    Player player = (Player) e.getEntity().getShooter();
		    ItemStack stack = player.getItemInHand();
		    CustomItem custom = verifyCustomItem(stack);
		    if(custom != null && custom.getOnThrowConsumer() != null) {
				custom.getOnThrowConsumer().accept(e);
			}
		}
		
	}

	private CustomItem verifyCustomItem(ItemStack item) {
		return CustomItem.getCustomItem(item);
	}
}
