package me.cmastudios.plugins.WarhubModChat.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Message {
	public static void send (Player player, String message) {
		if (player == null) {
			System.out.println(message.replace("¤a", "").replace("¤b", "").replace("¤c", "").replace("¤d", "").replace("¤e", "").replace("¤f", "").replace("¤1", "").replace("¤2", "").replace("¤3", "").replace("¤4", "").replace("¤5", "").replace("¤6", "").replace("¤7", "").replace("¤8", "").replace("¤9", "").replace("¤0", ""));
		} else {
			player.sendMessage(message);
		}
	}
	public static void broadcast (String message) {
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
	        Message.send(p, message);
	      }
		System.out.println(message.replace("¤a", "").replace("¤b", "").replace("¤c", "").replace("¤d", "").replace("¤e", "").replace("¤f", "").replace("¤1", "").replace("¤2", "").replace("¤3", "").replace("¤4", "").replace("¤5", "").replace("¤6", "").replace("¤7", "").replace("¤8", "").replace("¤9", "").replace("¤0", ""));
	}

}
