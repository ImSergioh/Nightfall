package me.imsergioh.smcore.instance.player.loader;

import me.imsergioh.sbackendapi.instance.DataBucket;

import java.util.concurrent.CompletableFuture;

@FunctionalInterface
public interface PlayerDataLoaderTask {

    CompletableFuture<DataBucket> loadData();

}
