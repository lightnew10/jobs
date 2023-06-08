package fr.lightnew.event;

import org.bukkit.entity.Player;

public class CounterMiningEntity {

    private final Player player;
    private Integer xp;

    public CounterMiningEntity(Player player, Integer xp) {
        this.player = player;
        this.xp = xp;
    }

    public Integer getXp() {
        return xp;
    }

    public Player getPlayer() {
        return player;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }
}
