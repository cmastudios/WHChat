package me.cmastudios.plugins.WarhubModChat;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.dynmap.DynmapAPI;

import me.cmastudios.plugins.WarhubModChat.commands.ChannelCommand;
import me.cmastudios.plugins.WarhubModChat.commands.DeafCommand;
import me.cmastudios.plugins.WarhubModChat.commands.MeCommand;
import me.cmastudios.plugins.WarhubModChat.commands.MuteCommand;
import me.cmastudios.plugins.WarhubModChat.commands.QuickMessageCommand;
import me.cmastudios.plugins.WarhubModChat.commands.SayCommand;
import me.cmastudios.plugins.WarhubModChat.util.*;
import me.cmastudios.plugins.WarhubModChat.SLAPI;

public class WarhubModChat extends JavaPlugin {

	//Permission permissions = new Permission();
	Message messageUtil = new Message();
	String version;
	Logger log = Logger.getLogger("Minecraft");
	private final WarhubModChatListener Listener = new WarhubModChatListener(this);
	public HashMap<Player, String> channels = new HashMap<Player, String>();
	public HashMap<Player, String> ignores = new HashMap<Player, String>();
	public HashMap<String, Integer> mutedplrs = new HashMap<String, Integer>();
	public HashMap<String, Integer> warnings = new HashMap<String, Integer>();
	public HashMap<Player, String> spamcheck = new HashMap<Player, String>();

	@Override
	public void onDisable() {
		channels.clear();
		try {
			SLAPI.save(warnings, "warnings.bin");
			warnings.clear();
			SLAPI.save(mutedplrs, "mutedplrs.bin");
			mutedplrs.clear();
		} catch (Exception e) {
			log.severe("[WHChat] Failed to save data!");
			e.printStackTrace();
		}
		log.info("[WHChat] Disabled!");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onEnable() {
		//permissions.setupPermissions();
		Config.setup(getConfig());
		try {
			warnings = (HashMap<String, Integer>) SLAPI.load("warnings.bin");
			mutedplrs = (HashMap<String, Integer>) SLAPI.load("mutedplrs.bin");
		} catch (Exception e) {
			log.severe("[WHChat] Failed to load data!");
			e.printStackTrace();
		}
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(Listener, this);
		DynmapAPI dynmap = (DynmapAPI)getServer().getPluginManager().getPlugin("dynmap");
		if(dynmap == null || !((org.bukkit.plugin.Plugin)dynmap).isEnabled()) {
			log.info("[WHChat] dynmap not loaded, disabling message cancelling on dynmap");
			DynmapManager.disable();
		} else {
			DynmapManager.enable(this);
		}
		getCommand("channel").setExecutor(new ChannelCommand(this));
		getCommand("alert").setExecutor(new QuickMessageCommand(this));
		getCommand("global").setExecutor(new QuickMessageCommand(this));
		getCommand("modchat").setExecutor(new QuickMessageCommand(this));
		getCommand("deaf").setExecutor(new DeafCommand(this));
		getCommand("mute").setExecutor(new MuteCommand(this));
		getCommand("me").setExecutor(new MeCommand(this));
		getCommand("say").setExecutor(new SayCommand());

		PluginDescriptionFile pdffile = this.getDescription();
		version = pdffile.getVersion();
		log.info("[WHChat] Version " + version
				+ " by cmastudios enabled!");
	}


	public int parseTimeString(String time) {
		if (!time.matches("[0-9]*h?[0-9]*m?"))
			return -1;
		if (time.matches("[0-9]+"))
			return Integer.parseInt(time);
		if (time.matches("[0-9]+m"))
			return Integer.parseInt(time.split("m")[0]);
		if (time.matches("[0-9]+h"))
			return Integer.parseInt(time.split("h")[0]) * 60;
		if (time.matches("[0-9]+h[0-9]+m")) {
			String[] split = time.split("[mh]");
			return (Integer.parseInt(split[0]) * 60)
					+ Integer.parseInt(split[1]);
		}
		return -1;
	}

	public String parseMinutes(int minutes) {
		if (minutes == 1)
			return "one minute";
		if (minutes < 60)
			return minutes + " minutes";
		if (minutes % 60 == 0) {
			if (minutes / 60 == 1)
				return "one hour";
			else
				return (minutes / 60) + " hours";
		}
		if (minutes == -1)
			return "indefinitely";
		int m = minutes % 60;
		int h = (minutes - m) / 60;
		return h + "h" + m + "m";
	}

}
