package me.imsergioh.smcore.handler;

import me.imsergioh.smcore.SMCore;
import me.imsergioh.smcore.events.PlayerMainEvents;
import me.imsergioh.smcore.util.PluginUtil;

public class EventsHandler {

    private static final SMCore plugin = SMCore.getPlugin();

    public static void registerEvents() {
        PluginUtil.registerListeners(plugin,
                new PlayerMainEvents());
    }

}
