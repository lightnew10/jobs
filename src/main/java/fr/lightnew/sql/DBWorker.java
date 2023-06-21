package fr.lightnew.sql;

import fr.lightnew.Jobs;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class DBWorker extends BukkitRunnable {

    private Queue<Runnable> todo = new ArrayDeque<>();

    @Override
    public void run() {
        if (todo.isEmpty())
            return;
        Runnable current = todo.poll();
        current.run();
    }

    public void get(BlockRequestMethod requestMethod) {
        /*Bukkit.broadcastMessage("Get");
        todo.add(() -> {
            // TODO : REQUEST
            Bukkit.broadcastMessage("Request 1");
            requestMethod.run(RequestsSQL.getLocationBlockPosed());
            Bukkit.broadcastMessage("Request 2");
        });*/
        CompletableFuture.runAsync(() -> {
            // TODO: Requête asynchrone
            // Effectuez ici votre requête SQL ou toute autre opération longue de manière asynchrone
            requestMethod.run(RequestsSQL.getLocationBlockPosed());
        }, Executors.newCachedThreadPool()).thenRun(() -> {
        });
    }

    public void put(Location location) {
        /*todo.add(() -> {
            // TODO : INJECT
            RequestsSQL.setLocationBlockPosed(location);
        });*/
        CompletableFuture.runAsync(() -> {
            // TODO: Requête asynchrone
            // Effectuez ici votre requête SQL ou toute autre opération longue de manière asynchrone
            RequestsSQL.setLocationBlockPosed(location);
        }, Executors.newCachedThreadPool()).thenRun(() -> {
        });
    }

    public void remove(Location location) {
        /*todo.add(() -> {
           // TODO : REMOVE
           RequestsSQL.removeLocationBlockPosed(location);
        });*/
        CompletableFuture.runAsync(() -> {
            // TODO: Requête asynchrone
            // Effectuez ici votre requête SQL ou toute autre opération longue de manière asynchrone
            RequestsSQL.removeLocationBlockPosed(location);
        }, Executors.newCachedThreadPool()).thenRun(() -> {
        });
    }
}
