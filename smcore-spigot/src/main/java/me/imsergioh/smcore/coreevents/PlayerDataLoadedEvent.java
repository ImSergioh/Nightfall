package me.imsergioh.smcore.coreevents;

import me.imsergioh.smcore.instance.player.CorePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerDataLoadedEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final CorePlayer corePlayer;

    public PlayerDataLoadedEvent(CorePlayer corePlayer) {
        this.corePlayer = corePlayer;
    }

    public Player getPlayer() {
        return corePlayer.getPlayer();
    }

    public CorePlayer getCorePlayer() {
        return corePlayer;
    }

    public static HandlerList getHandler() {
        return HANDLER_LIST;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
