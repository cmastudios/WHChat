package me.cmastudios.plugins.WarhubModChat.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerInfo {
	public static boolean isPlayer(String string) {
		try {
		    Player test = Bukkit.getServer().getPlayer(string);
		    if (test == null) {
		        return false;
		    } else {
		        return true;
		    }
		} catch (Exception ex) {
		    return false;
		} 
	}
	public static Player toPlayer(String name) {
		if (!isPlayer(name)) {
			return null;
		} else {
			return Bukkit.getServer().getPlayer(name);
		}
	}
	public static String PlayerToString(Player player) {
		if (player == null) return "CONSOLE";
		return player.getName();
	}

}
