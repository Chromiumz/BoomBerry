package com.chromium.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.chromium.core.util.CustomItem;
import com.chromium.strangefood.Logger;

import net.md_5.bungee.api.ChatColor;

public class CustomItemCommand implements CommandExecutor {
	//
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!sender.hasPermission("chromium.core.customitem")) {
			sender.sendMessage("You're not allowed to do that!");
			return false;
		}
		
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length > 1) {
				if(args[0].equals("give")) {
					ItemStack item = CustomItem.getItemBySimilarName(args[1]);
					if(item != null) {
						p.getInventory().addItem(item);
						Logger.logNoPrefix(p, "&aYou received a similar item named: "+item.getItemMeta().getDisplayName());
						return true;
					}
				}
			}
			if(args.length == 0) {
				Logger.logNoPrefix(p, "&7&m---&r &f&lChromium Core &a&lItems&r &7&m---&r\n"
						+ "&b/customitem help &f| &7&oBrings this information up\n"
						+ "&b/customitem give <name> &f| &7&oGives the item if the name is similar\n"
						+ "&b/customitem listfqn &f| &7&oList all custom items by there full name (including color)\n"
						+ "&b/customitem list &f| &7&oList all custom items by there full name");
			}
			if(args.length > 0) {
				if(args[0].equals("help")) {
					Logger.logNoPrefix(p, "&7&m---&r &f&lChromium Core &a&lItems&r &7&m---&r\n"
							+ "&b/customitem help &f| &7&oBrings this information up\n"
							+ "&b/customitem give <name> &f| &7&oGives the item if the name is similar\n"
							+ "&b/customitem listfqn &f| &7&oList all custom items by there full name (including color)\n"
							+ "&b/customitem list &f| &7&oList all custom items by there full name");
					return true;
				}
				if(args[0].equals("listfqn")) {
					StringBuilder items = new StringBuilder("&7&m---&r &f&lChromium Core &a&lItems&r &7&m---&r\n");
					for (Entry<String, CustomItem> e : CustomItem.getAllItems().entrySet()) {
						items.append(e.getValue().getItem().getItemMeta().getDisplayName()+"\n");
					}
					Logger.logNoPrefix(p,items.toString());
					
				}
				if(args[0].equals("list")) {
					StringBuilder items = new StringBuilder("&7&m---&r &f&lChromium Core &a&lItems&r &7&m---&r\n");
					for (Entry<String, CustomItem> e : CustomItem.getAllItems().entrySet()) {
						items.append("&b"+ChatColor.stripColor(e.getValue().getItem().getItemMeta().getDisplayName())+"\n");
					}
					Logger.logNoPrefix(p,items.toString());
					
				}
			}
			
		}
		
		
		return true;
	}

}
