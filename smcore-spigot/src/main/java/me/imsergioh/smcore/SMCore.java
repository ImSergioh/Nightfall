package me.imsergioh.smcore;

import me.imsergioh.sbackendclient.SBackendClientConnection;
import me.imsergioh.smcore.handler.EventsHandler;
import me.imsergioh.smcore.instance.ServerStopTasks;
import me.imsergioh.smcore.instance.player.CorePlayer;
import org.bukkit.plugin.java.JavaPlugin;

public final class SMCore extends JavaPlugin {

    private static SMCore plugin;

    private static SBackendClientConnection backendConnection;

    @Override
    public void onEnable() {
        plugin = this;
        registerOutgoingPluginChannelBungee();
        connectToBackend();
        registerEventsFromEventsHandler();
    }

    @Override
    public void onDisable() {
        ServerStopTasks.registerTask(CorePlayer::unloadAndUnregisterPlayersSafely);
        ServerStopTasks.startExecutingTasksUntilAllAreFinished();
    }

    private void registerEventsFromEventsHandler() {
        EventsHandler.registerEvents();
    }

    private void registerOutgoingPluginChannelBungee() {
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

    private void connectToBackend() {
        // CONNECT TO BACKEND
        backendConnection = new SBackendClientConnection("localhost", 723,
                "spigot" + getServer().getPort(), "4iPvLti8y6ZMwRX3");
        backendConnection.connect();
    }

    public static SMCore getPlugin() {
        return plugin;
    }

    public static SBackendClientConnection getBackendConnection() {
        return backendConnection;
    }
}

