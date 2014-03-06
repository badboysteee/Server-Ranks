package com.ste.ServerRanks;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerRanks extends JavaPlugin {
	

	public void onEnable() {
			getConfig().options().copyDefaults(true);
			saveDefaultConfig();
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			Player p = (Player) sender;
			
			if(cmd.getName().equalsIgnoreCase("RanksList")) {
				for(String Ranks : getConfig().getStringList("Ranks")) {
					if(sender.hasPermission("ranks.ranklist")) {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', Ranks));
					}
				}
			}
		}
		if(cmd.getName().equalsIgnoreCase("Ranks")) {
				if(sender.hasPermission("ranks.rankreload")) {
					if(args.length == 0) {
						sender.sendMessage(ChatColor.GOLD + "/Command <Reload/Help>");
					}
					
					if(args.length == 1) {
						if(args[0].equalsIgnoreCase("reload")) {
							reloadConfig();
							saveConfig();
							sender.sendMessage(ChatColor.GOLD + "Server-Ranks Reloaded!");
						}
					}
					if(args.length == 1) {
						if(args[0].equalsIgnoreCase("help")) {
							if(sender.hasPermission("ranks.rankhelp")) {
							sender.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.RED + "Server" + ChatColor.YELLOW + "-"+ ChatColor.RED + "Ranks" + ChatColor.DARK_GRAY + "]");
							sender.sendMessage(ChatColor.YELLOW + "/ranks help" + ChatColor.GRAY + " - " + "Shows This List");
							sender.sendMessage(ChatColor.YELLOW + "/ranks reload" + ChatColor.GRAY + " - " + "Reloads The Configuration File");
							sender.sendMessage(ChatColor.YELLOW + "/rankslist" + ChatColor.GRAY + " - " + "List All The Ranks On The Server");
							}
						}
					}
				}
			}
		return true;
	}
}
