package me.tokyojack.mcmarket.blacklist.objects;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BlacklistedPlayer {

	private String reason;
	private UUID blacklister;

	public BlacklistedPlayer(String reason, UUID blackLister) {
		this.reason = reason;
		this.blacklister = blackLister;
	}

	public BlacklistedPlayer(String reason, String blackLister) {
		this.reason = reason;
		this.blacklister = getUUIDFromPlayername(blackLister);
	}

	public String getReason() {
		return this.reason;
	}

	public UUID getBlackLister() {
		return this.blacklister;
	}

	@SuppressWarnings("deprecation")
	private UUID getUUIDFromPlayername(String playerName) {
		Player player = Bukkit.getPlayer(playerName);
		return player != null ? player.getUniqueId() : Bukkit.getOfflinePlayer(playerName).getUniqueId();
	}

}
