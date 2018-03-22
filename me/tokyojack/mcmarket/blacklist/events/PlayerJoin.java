package me.tokyojack.mcmarket.blacklist.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;

import me.tokyojack.mcmarket.blacklist.Core;

public class PlayerJoin implements Listener {

	@EventHandler
	public void playerJoin(AsyncPlayerPreLoginEvent event) {
		if (!Core.getPlugin().getBlacklistManager().containsPlayer(event.getUniqueId()))
			return;

		String reason = Core.getPlugin().getBlacklistManager().getReason(event.getUniqueId());
		event.disallow(Result.KICK_BANNED,
				Core.getPlugin().getKonfig().getString("playerJoinedWhenBlacklisted").replace("%reason%", reason));
	}

}
