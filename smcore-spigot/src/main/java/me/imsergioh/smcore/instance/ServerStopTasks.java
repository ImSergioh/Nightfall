package me.imsergioh.smcore.instance;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ServerStopTasks {

    private static final Set<ServerStopTask> tasks = new HashSet<>();

    public static void registerTask(ServerStopTask task) {
        tasks.add(task);
    }

    public static void startExecutingTasksUntilAllAreFinished() {
        while (!tasks.isEmpty()) {
            for (ServerStopTask task : tasks) {
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                Future<?> future = executorService.submit(task);

                if (future.isDone()) {
                    tasks.remove(task);
                }
            }
        }
    }

}
