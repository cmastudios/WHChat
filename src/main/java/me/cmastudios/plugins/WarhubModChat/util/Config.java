package me.cmastudios.plugins.WarhubModChat.util;

import java.io.File;

import org.bukkit.util.config.Configuration;
import org.bukkit.util.config.ConfigurationNode;

@SuppressWarnings("deprecation")
public class Config {
	protected static Configuration CONFIG;
	static String mainDirectory = "plugins/WarhubModChat";
	static File file = new File(mainDirectory + File.separator + "config.yml");
	public static void setup() {
		new File(mainDirectory).mkdir();
		if(!file.exists()){
		            try {
		                file.createNewFile();
		                write("modchat-format", "&2Modchat> &a%player: %message");
		                write("alert-format", "&4Attention> &c%message");
		                write("say-format", "&5Server> &d%message");
		        		Configuration CONFIG = load();
		        		CONFIG.setHeader(
		        				"# Auto-generated config for WarhubModChat",
		        				"#");
		        		CONFIG.save();
		            } catch (Exception ex) {
		                ex.printStackTrace();
		            }
		}
		
		
	}
	public static void write(String root, Object x){     	
		Configuration CONFIG = load();
		CONFIG.setProperty(root, x);
		CONFIG.setHeader(
				"# Auto-generated config for WarhubModChat",
				"#");
       CONFIG.save();
   }
   public static void delete(String root) {
		Configuration CONFIG = load();
		CONFIG.removeProperty(root);
		CONFIG.save();
   }

   public static String read(String root){
   	Configuration CONFIG = load();
       return CONFIG.getString(root);
   }
   public static ConfigurationNode getNode(String child) {
       return getNode("", child);
   }
	private static ConfigurationNode getNode(String parent, String child) {
       ConfigurationNode parentNode = null;
       if (child.contains(".")) {
           int index = child.lastIndexOf('.');
           parentNode = getNode("", child.substring(0, index));
           child = child.substring(index + 1);
       } else if (parent.length() == 0) {
           parentNode = load();
       } else if (parent.contains(".")) {
           int index = parent.indexOf('.');
           parentNode = getNode(parent.substring(0, index), parent.substring(index + 1));
       } else {
           parentNode = getNode("", parent);
       }

       if (parentNode == null) {
           return null;
       }

       for (String entry : parentNode.getKeys()) {
           if (child.equalsIgnoreCase(entry)) {
               return parentNode.getNode(entry);
           }
       }
       return null;
   }
   public static Configuration load(){

       try {
           Configuration CONFIG = new Configuration(file);
           CONFIG.load();
           return CONFIG;

       } catch (Exception e) {
           e.printStackTrace();
       }
       return null;
   }
   
}
