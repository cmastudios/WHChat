package me.cmastudios.plugins.WarhubModChat;

public class LogIP implements Runnable {

	@Override
	public void run() {
		/*
		if (WarhubModChat.playerNameToLog == null)
			return;
		if (WarhubModChat.playerIpToLog == null)
			return;
		if (Config.config.getString("mysql.password").equals(null)
				|| Config.config.getString("mysql.password").equals("invalid")) {
			return;
		}
		String user = Config.config.getString("mysql.username");
		String pass = Config.config.getString("mysql.password");
		String url = "jdbc:mysql://" + Config.config.getString("mysql.host")
				+ ":" + Config.config.getString("mysql.port") + "/"
				+ Config.config.getString("mysql.database");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date date = new Date();
		String time = dateFormat.format(date);
		Connection conn;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			PreparedStatement sampleQueryStatement = conn
					.prepareStatement("INSERT INTO `"
							+ Config.config.getString("mysql.database")
							+ "`.`iplogs` (`id`, `ip`, `player`, `date`) "
							+ "VALUES (NULL, '" + WarhubModChat.playerIpToLog
							+ "', '" + WarhubModChat.playerNameToLog + "', '"
							+ time + "');");
			sampleQueryStatement.executeUpdate();
			sampleQueryStatement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			Logger.getLogger("Minecraft").severe("[WHChat] Cannot add IP to database! " + e);
		}
*/
	}

}
