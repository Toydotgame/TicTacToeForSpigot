package io.github.Toydotgame;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	@Override
	public void onEnable() {
		this.getCommand("tictactoe").setExecutor(new TicTacToe());
	}
	
	@Override
	public void onDisable() {
		// Bukkit/Spigot is glitchy as all hell with disabled plugins.
		// Don't bother with putting anything here, it's not going to help. ;)
	}
}
