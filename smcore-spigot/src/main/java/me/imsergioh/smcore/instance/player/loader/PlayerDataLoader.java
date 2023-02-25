package me.imsergioh.smcore.instance.player.loader;

import me.imsergioh.sbackendapi.instance.DataBucket;
import me.imsergioh.sbackendclient.instance.DataBucketRequest;
import me.imsergioh.smcore.SMCore;
import org.bukkit.entity.Player;

import java.util.concurrent.CompletableFuture;

public class PlayerDataLoader implements PlayerDataLoaderTask {

    private static final String handler = "SCore/playerdata";

    private final Player player;

    public PlayerDataLoader(Player player) {
        this.player = player;
    }

    @Override
    public CompletableFuture<DataBucket> loadData() {
        CompletableFuture<DataBucket> future = new CompletableFuture<>();

        DataBucketRequest request = new DataBucketRequest(handler, player.getUniqueId().toString());

        request.setReceiveRunnable(() -> {
            future.complete(request.getDataBucket());
        });
        SMCore.getBackendConnection().requestDataBucket(request);
        return future;
    }
}

