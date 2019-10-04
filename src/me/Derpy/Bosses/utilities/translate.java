package me.Derpy.Bosses.utilities;

import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

public class translate {
	public static String get(String index, Plugin plugin) {
		try {
			if(plugin.getConfig().getString("translation."+plugin.getConfig().getString("language")+"."+index).equals("null")) {
				return plugin.getConfig().getString("translation.english."+index);
			}else {
				return plugin.getConfig().getString("translation."+plugin.getConfig().getString("language")+"."+index);
			}
		}catch(Exception e){
			plugin.getServer().getConsoleSender().sendMessage(ChatColor.RED+"[MoreBosses] (LANGUAGE ERROR): Failed to get translation for language."+plugin.getConfig().getString("language")+", index: "+index);
			return ChatColor.RED+"Failed to find translation for index: "+index;
		}
	}
}
