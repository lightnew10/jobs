package fr.lightnew.jobs;

import fr.lightnew.Jobs;
import fr.lightnew.component.AdvancementAPIFrameType;
import fr.lightnew.tools.ObjectsPreset;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionType;

import java.util.Arrays;
import java.util.List;

public class Alchemist {

    private int xp;
    private String name = "Alchimiste";
    private double level_1 = ObjectsPreset.xpMaxAlchemist1;
    private double level_2 = ObjectsPreset.xpMaxAlchemist2;
    private double level_3 = ObjectsPreset.xpMaxAlchemist3;
    private double level_4 = ObjectsPreset.xpMaxAlchemist4;
    private double level_5 = ObjectsPreset.xpMaxAlchemist5;

    public static List<PotionType> listPotion = Arrays.asList(
            PotionType.STRENGTH,
            PotionType.REGEN,
            PotionType.SPEED,
            PotionType.INSTANT_HEAL,
            PotionType.FIRE_RESISTANCE,
            PotionType.POISON,
            PotionType.INSTANT_HEAL
    );

    public Alchemist(int xp) {
        this.xp  = xp;
    }

    public int getLevel() {
        return xp <= level_1 ? 1 : xp <= level_2 ? 2 : xp <= level_3 ? 3 : xp <= level_4 ? 4 : xp <= level_5 ? 5 : 6;
    }

    private int getXPLevel(int level, PotionType potionType) {
        if (potionType.equals(PotionType.STRENGTH)) {
            if (level == 1 || level == 2)
                return 50;
            if (level == 3 || level == 4)
                return 30;
            if (level == 5)
                return 20;
        }
        if (potionType.equals(PotionType.REGEN)) {
            if (level == 1 || level == 2 || level == 3)
                return 40;
            if (level == 4)
                return 30;
            if (level == 5)
                return 45;
        }
        if (potionType.equals(PotionType.SPEED)) {
            if (level == 2)
                return 25;
            if (level == 3)
                return 15;
            if (level == 4)
                return 20;
            if (level == 5)
                return 30;
        }
        if (potionType.equals(PotionType.INSTANT_HEAL)) {
            if (level == 3)
                return 50;
            if (level == 4 || level == 5)
                return 40;
        }
        if (potionType.equals(PotionType.FIRE_RESISTANCE)) {
            if (level == 4 || level == 5)
                return 50;
        }
        if (potionType.equals(PotionType.POISON)) {
            if (level == 4 || level == 5)
                return 30;
        }
        return 0;
    }

    public void getGiveXP(Player player, PotionType potionType) {
        if (potionType == null)
            return;
        if (getXPLevel(1, potionType) == 0) {
            return;
        }
        int xp = getXPLevel(1, potionType);
        if (Jobs.playersJobs.get(player).getNotification())
            ObjectsPreset.sendFakeNotification(player, AdvancementAPIFrameType.TASK, Material.BREWING_STAND, ChatColor.GOLD + "§l" + name + ChatColor.YELLOW + "\n+" + xp + "xp");
        int old = getLevel();
        this.xp = this.xp + xp;
        if ((old + 1) == 6) {
            player.sendMessage(ChatColor.YELLOW + "Vous venez de passer au niveau " + ChatColor.GOLD + "Max" + ChatColor.YELLOW + " dans le métier §6§l" + this.name);
        } else
            if (getLevel() == (old+1)) {
                player.sendMessage(ChatColor.YELLOW + "Vous venez de passer au niveau " + ChatColor.GOLD + getLevel() + ChatColor.YELLOW + " dans le métier §6§l" + this.name);
            }
    }

    public void giveRewards(int level) {
        if (level == 2) {

        }
        if (level == 3) {

        }
        if (level == 4) {

        }
        if (level == 5) {

        }
        if (level == 6) {

        }
    }

    public int getXP() {return xp;}

    public int getXPMAXLevel() {
        return (int) (xp <= level_1 ? level_1 : xp <= level_2 ? level_2 : xp <= level_3 ? level_3 : xp <= level_4 ? level_4 : level_5);
    }

    public void setXp(int xp) {
        this.xp = xp;
    }
}
