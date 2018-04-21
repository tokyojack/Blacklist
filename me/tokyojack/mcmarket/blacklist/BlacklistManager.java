package me.tokyojack.mcmarket.blacklist;

import java.util.LinkedHashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.tokyojack.mcmarket.blacklist.objects.BlacklistedPlayer;

public class BlacklistManager {

	private LinkedHashMap<UUID, BlacklistedPlayer> blacklistedPlayers;

	public BlacklistManager() {
		this.blacklistedPlayers = new LinkedHashMap<UUID, BlacklistedPlayer>();
	}

	public void addPlayer(String playerName, String reason, String blackLister) {
		this.blacklistedPlayers.put(getUUIDFromPlayername(playerName), new BlacklistedPlayer(reason, blackLister));
	}

	public void addPlayer(UUID playerUUID, String reason, UUID blackLister) {
		this.blacklistedPlayers.put(playerUUID, new BlacklistedPlayer(reason, blackLister));
	}

	public void removePlayer(String playerName) {
		this.blacklistedPlayers.remove(getUUIDFromPlayername(playerName));
	}

	public LinkedHashMap<UUID, BlacklistedPlayer> getBlacklistedPlayers() {
		return this.blacklistedPlayers;
	}

	public String getReason(UUID playerUUID) {
		return this.blacklistedPlayers.get(playerUUID).getReason();
	}

	public boolean containsPlayer(Player player) {
		return this.blacklistedPlayers.containsKey(player.getUniqueId());
	}

	public boolean containsPlayer(UUID playerUUID) {
		return this.blacklistedPlayers.containsKey(playerUUID);
	}

	public boolean containsPlayer(String playerName) {
		return this.blacklistedPlayers.containsKey(getUUIDFromPlayername(playerName));
	}

	@SuppressWarnings("deprecation")
	private UUID getUUIDFromPlayername(String playerName) {
		Player player = Bukkit.getPlayer(playerName);
		return player != null ? player.getUniqueId() : Bukkit.getOfflinePlayer(playerName).getUniqueId();
	}

}