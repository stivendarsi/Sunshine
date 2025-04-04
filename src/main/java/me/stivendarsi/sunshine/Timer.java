package me.stivendarsi.sunshine;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Timer {
    private ScheduledExecutorService service;
    private double delay = 3.0;

    private Runnable task;

    public void start(Runnable task) {
        if (service != null && !service.isShutdown()) {
            return;
        }

        service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(() -> {
            try {
                task.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 100, (long) (1000L * delay), TimeUnit.MILLISECONDS);
    }

    public void stop() {
        if (service == null || service.isShutdown()) return;
        service.shutdown();
    }

    public void reload() {
        stop();
        start(task);
    }

    public void setDelay(double delay) {
        this.delay = delay;
    }
}
