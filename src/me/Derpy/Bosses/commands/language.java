package me.Derpy.Bosses.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import me.Derpy.Bosses.MoreBosses;
import net.md_5.bungee.api.ChatColor;

public class language implements CommandExecutor, TabCompleter{

	private MoreBosses plugin;
	
	public language(MoreBosses plugin) {
		this.plugin = plugin;
		plugin.getCommand("blanguage").setExecutor(this);
		
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Inable to execute command as console");
			return true;
		}
		final Player p = (Player) sender;
		if(p.hasPermission("bosses.language")) {
		    if(args[0].toLowerCase().equals("english")) {
		    	plugin.getConfig().set("language", "english");
		    	plugin.saveConfig();
		    	Bukkit.broadcastMessage(ChatColor.GOLD+"[MoreBosses] Reloading Server");
		    	Bukkit.broadcastMessage(ChatColor.GREEN+"[MoreBosses] Language: English");
		    	Bukkit.getServer().reload();
		    	return true;
		    }else if(args[0].toLowerCase().equals("norsk")) {
		    	plugin.getConfig().set("language", "norsk");
		    	plugin.saveConfig();
		    	Bukkit.broadcastMessage(ChatColor.GOLD+"[MoreBosses] Omlasting av server");
		    	Bukkit.broadcastMessage(ChatColor.GREEN+"[MoreBosses] Språk: Norsk");
		    	Bukkit.getServer().reload();
		    	return true;
		    }else if(args[0].toLowerCase().equals("español")) {
		    	plugin.getConfig().set("language", "español");
		    	plugin.saveConfig();
		    	Bukkit.broadcastMessage(ChatColor.GOLD+"[MoreBosses] Servidor de Recarga");
		    	Bukkit.broadcastMessage(ChatColor.GREEN+"[MoreBosses] Idioma: Español");
		    	Bukkit.getServer().reload();
		    	return true;
		    }
		}else {
			p.sendMessage("You do not have the required permissions to execute this command");
			return true;
		}
		return false;
	}
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("blanguage")) {
			if (args.length == 1) {
				ArrayList<String> available = new ArrayList<String>();
				available.add("english");
				available.add("norsk");
				available.add("español");
				Collections.sort(available);
				return available;
			}
		}
		
		return null;
	}
}