package me.tokyojack.mcmarket.blacklist;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.tokyojack.mcmarket.blacklist.commands.BList;
import me.tokyojack.mcmarket.blacklist.commands.Blacklist;
import me.tokyojack.mcmarket.blacklist.commands.UnBlacklist;
import me.tokyojack.mcmarket.blacklist.events.PlayerJoin;
import me.tokyojack.mcmarket.blacklist.utils.Kommand.Kommand.KommandManager;
import me.tokyojack.mcmarket.blacklist.utils.Konfig.ConfigFile;
import me.tokyojack.mcmarket.blacklist.utils.Konfig.Konfig;

public class Core extends JavaPlugin {

	private static Core plugin;

	public static Core getPlugin() {
		return plugin;
	}

	private BlacklistManager blacklistManager;

	public BlacklistManager getBlacklistManager() {
		return this.blacklistManager;
	}

	private Konfig konfig;

	public Konfig getKonfig() {
		return this.konfig;
	}

	public void onEnable() {
		plugin = this;

		this.blacklistManager = new BlacklistManager();

		new KommandManager().addCommand(new Blacklist()).addCommand(new UnBlacklist()).addCommand(new BList()).build();

		this.konfig = new Konfig(this)
				.addConfigFile(new ConfigFile("lang")
						.setString("playerJoinedWhenBlacklisted", "&4You've been blacklisted for: %reason%")
						.setString("blacklist.noPerm", "&cYou cannot do this command :(")
						.setString("blacklist.notCorrectArgs", "&c/blacklist (user) (reason) [-s]")
						.setString("blacklist.alreadyBlacklisted", "&cThat player is already blacklisted!")
						.setString("blacklist.kickPlayerWhenWhitelisted", "&cYou've been blacklisted!")
						.setString("blacklist.success", "&aYou have successfully blacklisted %player%!")
						.setString("blacklist.broadcast", "&c%player% has been blacklisted")
						.setString("unblacklist.noPerm", "&cYou cannot do this command :(")
						.setString("unblacklist.notCorrectArgs", "&c/unblacklist (user)")
						.setString("unblacklist.playerIsNotReadyBlacklisted", "&cThat player isn't blacklisted!")
						.setString("unblacklist.success", "&aYou've successfully removed %player%'s blacklist")
						.setString("unblacklist.broadcast", "&c%player% has been unblacklisted")
						.setString("blist.noPerm", "&cYou cannot do this command :(")
						.setString("blist.noBlackListedPlayers", "&cThere aren't any blacklisted players, YET")
						.setString("blist.isNotANumber", "&cThat page number is not a number!")
						.setString("blist.pastPages", "&cThere aren't any pages past that point!")
						.setString("blist.lineFormat", "&a%player%: %reason% FROM %blackLister%")
						.setString("blist.page", "&7&oYou're on page %page%"))
				.addConfigFile(new ConfigFile("blacklisted_players").setArray("blacklisted_players")).build(false);

		PluginManager pluginManager = getServer().getPluginManager();
		pluginManager.registerEvents(new PlayerJoin(), this);

		this.konfig.getArray("blacklisted_players").forEach(blacklisted_player -> {
			String[] spl = blacklisted_player.split("\\|");

			UUID uuid = UUID.fromString(spl[0]);
			String reason = spl[1];
			UUID blacklister = UUID.fromString(spl[2]);

			this.blacklistManager.addPlayer(uuid, reason, blacklister);
		});
	}

	public void onDisable() {
		ArrayList<String> finals = new ArrayList<String>();

		this.blacklistManager.getBlacklistedPlayers()
				.forEach((uuid, as) -> finals.add(uuid.toString() + "|" + as.getReason() + "|" + as.getBlackLister()));

		this.konfig.setArray("blacklisted_players", finals);
		this.konfig.build(true);
	}
}
