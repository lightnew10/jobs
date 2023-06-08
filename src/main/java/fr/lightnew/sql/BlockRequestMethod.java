package fr.lightnew.sql;

import org.bukkit.Chunk;
import org.bukkit.Location;

import java.util.List;

@FunctionalInterface
public interface BlockRequestMethod {

    public void run(final Chunk parameter, List<Location> locations);

}
