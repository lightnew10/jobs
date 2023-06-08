package fr.lightnew.event;

import fr.lightnew.Jobs;
import fr.lightnew.sql.RequestsSQL;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class PlayerManager implements Listener {

    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        RequestsSQL.createPlayerData(player);
        RequestsSQL.getPlayerJob(player);
    }

    @EventHandler
    public void playerquit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        RequestsSQL.updatePlayerData(player);
    }
}
