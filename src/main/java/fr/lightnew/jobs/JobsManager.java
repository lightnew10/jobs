package fr.lightnew.jobs;

import fr.lightnew.tools.ObjectsPreset;
import org.bukkit.entity.Player;

public class JobsManager {

    private final Player player;
    private Boolean notification;
    private final Alchemist alchemist;
    private final Fisherman fisherman;
    private final Hunter hunter;
    private final Lumberjack lumberjack;
    private final Miner miner;
    private final Peasant peasant;
    private final Smith smithing;

    public JobsManager(Player player, Boolean notification, Alchemist alchemist, Fisherman fisherman, Hunter hunter, Lumberjack lumberjack, Miner miner, Peasant peasant, Smith smithing) {
        this.player = player;
        this.notification = notification;
        this.alchemist = alchemist;
        this.fisherman = fisherman;
        this.hunter = hunter;
        this.lumberjack = lumberjack;
        this.miner = miner;
        this.peasant = peasant;
        this.smithing = smithing;
    }

    public Player getPlayer() {
        return player;
    }

    public Boolean getNotification() {
        return notification;
    }

    public void setNotification(Boolean notification) {
        this.notification = notification;
    }

    public Alchemist getAlchemist() {
        return alchemist;
    }

    public Fisherman getFisherman() {
        return fisherman;
    }

    public Hunter getHunter() {
        return hunter;
    }

    public Lumberjack getLumberjack() {
        return lumberjack;
    }

    public Miner getMinor() {
        return miner;
    }

    public Peasant getPeasant() {
        return peasant;
    }

    public Smith getSmithing() {
        return smithing;
    }

    public void reset() {
        alchemist.setXp(0);
        fisherman.setXp(0);
        hunter.setXp(0);
        lumberjack.setXp(0);
        miner.setXp(0);
        peasant.setXp(0);
        smithing.setXp(0);
    }

    public void max() {
        alchemist.setXp(ObjectsPreset.xpMaxAlchemist5.intValue());
        fisherman.setXp(ObjectsPreset.xpMaxFisherman5.intValue());
        hunter.setXp(ObjectsPreset.xpMaxHunter5.intValue());
        lumberjack.setXp(ObjectsPreset.xpMaxLumberjack5.intValue());
        miner.setXp(ObjectsPreset.xpMaxMinor5.intValue());
        peasant.setXp(ObjectsPreset.xpMaxPeasant5.intValue());
        smithing.setXp(ObjectsPreset.xpMaxSmith5.intValue());
    }

    public String toStringExplain() {
        return "§6§lJobsManager : " + "\n" +
                "§ePlayer §7-> §6" + player.getName() + "\n" +
                "§eAlchemist §7-> §e" + alchemist.getXP() + "§7/§6" + alchemist.getXPMAXLevel() + "\n" +
                "§eFisherman §7-> §e" + fisherman.getXP() + "§7/§6" + fisherman.getXPMAXLevel() + "\n" +
                "§eHunter §7-> §e" + hunter.getXP() + "§7/§6" + hunter.getXPMAXLevel() + "\n" +
                "§eLumberjack §7-> §e" + lumberjack.getXP() + "§7/§6" + lumberjack.getXPMAXLevel() + "\n" +
                "§eMinor §7-> §e" + miner.getXP() + "§7/§6" + miner.getXPMAXLevel() + "\n" +
                "§ePeasant §7-> §e" + peasant.getXP() + "§7/§6" + peasant.getXPMAXLevel() + "\n" +
                "§eSmithing §7-> §e" + smithing.getXP() + "§7/§6" + smithing.getXPMAXLevel();
    }

    @Override
    public String toString() {
        return "JobsManager{" +
                "player=" + player +
                ", alchemist=" + alchemist +
                ", fisherman=" + fisherman +
                ", hunter=" + hunter +
                ", lumberjack=" + lumberjack +
                ", minor=" + miner +
                ", peasant=" + peasant +
                ", smithing=" + smithing +
                '}';
    }
}
