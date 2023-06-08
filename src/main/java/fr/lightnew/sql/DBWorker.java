package fr.lightnew.sql;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DBWorker extends BukkitRunnable {

    private List<Runnable> todo = new LinkedList<>();

    @Override
    public void run() {
        if (todo.isEmpty())
            return;
        Runnable current = todo.remove(0);
        current.run();
    }

    public void get(Chunk chunk, BlockRequestMethod requestMethod) {
        todo.add(() -> {
            // TODO : REQUÊTE
            requestMethod.run(chunk, RequestsSQL.getLocationBlockPosed(chunk));
        });
    }

    public void put(Chunk chunk, Location location) {
        todo.add(() -> {
            // TODO : INJECT
            RequestsSQL.setLocationBlockPosed(chunk, location);
        });
    }

    public void remove(Chunk chunk, Location location) {
        todo.add(() -> {
           // TODO : REMOVE
           RequestsSQL.removeLocationBlockPosed(chunk, location);
        });
    }
}
