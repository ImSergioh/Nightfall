package me.imsergioh.smcore.instance.player;

import me.imsergioh.sbackendapi.instance.DataBucket;
import me.imsergioh.smcore.SMCore;
import me.imsergioh.smcore.coreevents.PlayerDataLoadedEvent;
import me.imsergioh.smcore.instance.player.loader.PlayerDataLoader;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class CorePlayer {

    private static final HashMap<UUID, CorePlayer> players = new HashMap<>();

    private final Player player;
    private DataBucket dataBucket;

    public CorePlayer(Player player) {
        this.player = player;
        loadData();
    }

    private void loadData() {
        PlayerDataLoader dataLoader = new PlayerDataLoader(player);

        dataLoader.loadData().thenAccept(dataBucket -> {
            this.dataBucket = dataBucket;
            Bukkit.getPluginManager().callEvent(new PlayerDataLoadedEvent(this));
        });
    }

    private void unload() {
        pushData();
    }

    public void pushData() {
        if(dataBucket == null) return;
        SMCore.getBackendConnection().pushDataBucket(dataBucket);
    }

    public Player getPlayer() {
        return player;
    }

    public static void unregister(Player player) {
        get(player).unload();
        players.remove(player.getUniqueId());
    }

    public static void register(Player player) {
        players.put(player.getUniqueId(), new CorePlayer(player));
    }

    public static CorePlayer get(Player player) {
        return players.get(player.getUniqueId());
    }

    public static void unloadAndUnregisterPlayersSafely() {
        loadedPlayers().forEach(CorePlayer::unload);
    }

    private static Collection<CorePlayer> loadedPlayers() {
        return players.values();
    }

}
