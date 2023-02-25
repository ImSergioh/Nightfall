package me.imsergioh.smcore.events;

import me.imsergioh.smcore.instance.player.CorePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerMainEvents implements Listener {

    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);

        Player player = event.getPlayer();

        CorePlayer.register(player);
    }

    @EventHandler
    public void playerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);

        Player player = event.getPlayer();

        CorePlayer.unregister(player);
    }
}
