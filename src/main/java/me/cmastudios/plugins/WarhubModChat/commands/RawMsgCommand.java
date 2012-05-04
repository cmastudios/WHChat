package me.cmastudios.plugins.WarhubModChat.commands;

import me.cmastudios.plugins.WarhubModChat.util.Message;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RawMsgCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
			String[] arg3) {
		StringBuilder msg = new StringBuilder();
		for (String m : arg3) {
			msg.append(m).append(" ");
		}
		Bukkit.getServer().broadcastMessage(Message.colorizeText(msg.toString()));
		return true;
	}

}
