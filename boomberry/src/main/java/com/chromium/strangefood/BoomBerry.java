package com.chromium.strangefood;

import java.util.ArrayList;
import java.util.function.Consumer;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.Vector;

import com.chromium.core.util.EdibleCustomItem;

public class BoomBerry extends EdibleCustomItem {

	private static Consumer<Player> onEatConsumer = (p) -> {
		p.setHealth(p.getMaxHealth());
		
		Location location = p.getLocation();
		World world = location.getWorld();
		
		world.createExplosion(location.getX(), location.getY(), location.getZ(), 3, false, false);
		p.playSound(location, Sound.EXPLODE, 10, 1);
		p.setVelocity(new Vector(0,.75,0));
	};
	
	public BoomBerry() {
		super(BoomBerry.class,
				Material.APPLE,
				"&bBoom Berry",
				true,
				2,
				onEatConsumer,
				new ArrayList<PotionEffect>(),
				"&f&oReady to burst with flavor!");
	}
	
}
