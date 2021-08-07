package com.chromium.strangefood;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.chromium.command.CustomItemCommand;
import com.chromium.core.listeners.EdibleListener;
import com.chromium.core.listeners.ItemListener;

public class Main extends JavaPlugin implements Listener {

	public void onEnable() {
		Logger.log("&aBigTest has enabled");
		
		getServer().getPluginManager().registerEvents(new EdibleListener(), this);
		getServer().getPluginManager().registerEvents(new ItemListener(), this);
		
		new BoomBerry();
		
		this.getCommand("customitem").setExecutor(new CustomItemCommand());

	}
	
	public void onDisable() {
		Logger.log("&cBigTest has disabled");
	}
	
}
