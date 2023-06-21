package fr.lightnew.sql;

import org.bukkit.Chunk;
import org.bukkit.Location;

import java.util.List;

@FunctionalInterface
public interface BlockRequestMethod {

    public void run(List<Location> locations);

}
