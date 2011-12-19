package me.cmastudios.plugins.WarhubModChat;

import java.util.ArrayList;
import java.util.List;

import me.cmastudios.plugins.WarhubModChat.util.Config;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;

public class plrLstnr extends PlayerListener {
	public static WarhubModChat plugin;
	 
    public plrLstnr(WarhubModChat instance) {
        plugin = instance;
    }
    @SuppressWarnings("static-access")
	public void onPlayerChat (PlayerChatEvent event) {
    	if (plugin.channels.containsKey(event.getPlayer())) {
    		if (plugin.channels.get(event.getPlayer()).equalsIgnoreCase("mod")) {
    			List<Player> sendto = new ArrayList<Player>();
    			for (Player p : Bukkit.getServer().getOnlinePlayers()) {
    				if (plugin.permissions.has(p, "warhub.moderator")) {
    					sendto.add(p);
    				}
    			}
    			for (Player p : sendto) {
    				p.sendMessage(Config.read("modchat-format").replace("&", "¤").replace("%player", event.getPlayer().getDisplayName()).replace("%message", event.getMessage()));
    			}
    			plugin.log.info("[MODCHAT] "+event.getPlayer().getDisplayName()+": "+event.getMessage());
    			sendto.clear();
    			event.setCancelled(true);
    		}
    		if (plugin.channels.get(event.getPlayer()).equalsIgnoreCase("alert")) {
    			Bukkit.getServer().broadcastMessage(Config.read("alert-format").replace("&", "¤").replace("%player", event.getPlayer().getDisplayName()).replace("%message", event.getMessage()));
    			event.setCancelled(true);
    		}
    	}
    }
    public void onPlayerJoin (PlayerJoinEvent event) {
    	plugin.channels.remove(event.getPlayer());
    }

}
