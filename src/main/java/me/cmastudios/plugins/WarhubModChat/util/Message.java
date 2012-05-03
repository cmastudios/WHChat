package me.cmastudios.plugins.WarhubModChat.util;

import org.bukkit.ChatColor;

public class Message {
	public static String colorizeText(String string) {
		return string.replaceAll("&([0-9a-fl-oA-FL-O])", ChatColor.COLOR_CHAR
				+ "$1");
	}
	public String parseColor(String string) {
		return Message.colorizeText(string);
	}

}
