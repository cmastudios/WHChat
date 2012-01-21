package me.cmastudios.plugins.WarhubModChat;

import java.util.ArrayList;
import java.util.List;
import me.cmastudios.plugins.WarhubModChat.util.Config;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class plrLstnr implements Listener {
	public static WarhubModChat plugin;
	 
    public plrLstnr(WarhubModChat instance) {
        plugin = instance;
    }
    @SuppressWarnings("static-access")
    @EventHandler(event = PlayerChatEvent.class, priority = EventPriority.HIGHEST)
	public void onPlayerChat (final PlayerChatEvent event) {
    	ArrayList<Player> plrs = new ArrayList<Player>();
    	for (Player plr : plugin.getServer().getOnlinePlayers()) {
    		if (plugin.ignores.containsKey(plr)) plrs.add(plr);
    	}
    	for (Player plr : plrs) {
    		event.getRecipients().remove(plr);
    	}
    	if (event.getMessage().contains("\u00A7") && !plugin.permissions.has(event.getPlayer(), "warhub.moderator")) {
    		event.setMessage(event.getMessage().replaceAll("\u00A7[0-9a-fA-FkK]", ""));
    	}
    	if (plugin.channels.containsKey(event.getPlayer())) {
    		if (plugin.channels.get(event.getPlayer()).equalsIgnoreCase("mod")) {
    			List<Player> sendto = new ArrayList<Player>();
    			for (Player p : Bukkit.getServer().getOnlinePlayers()) {
    				if (plugin.permissions.has(p, "warhub.moderator")) {
    					sendto.add(p);
    				}
    			}
    			for (Player p : sendto) {
    				p.sendMessage(plugin.messageUtil.colorizeText(Config.read("modchat-format")).replace("%player", event.getPlayer().getDisplayName()).replace("%message", event.getMessage()));
    			}
    			plugin.log.info("[MODCHAT] "+event.getPlayer().getDisplayName()+": "+event.getMessage());
    			sendto.clear();
    			event.setCancelled(true);
    		}
    		if (plugin.channels.get(event.getPlayer()).equalsIgnoreCase("alert")) {
    			Bukkit.getServer().broadcastMessage(plugin.messageUtil.colorizeText(Config.read("alert-format")).replace("%player", event.getPlayer().getDisplayName()).replace("%message", event.getMessage()));
    			event.setCancelled(true);
    		}
    	}
    }
    @EventHandler(event = PlayerJoinEvent.class, priority = EventPriority.LOW)
    public void onPlayerJoin (final PlayerJoinEvent event) {
    	plugin.channels.remove(event.getPlayer());
    }

}
