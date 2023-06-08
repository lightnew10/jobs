package fr.lightnew.jobs;

import fr.lightnew.Jobs;
import fr.lightnew.component.AdvancementAPIFrameType;
import fr.lightnew.tools.ObjectsPreset;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class Hunter {

    private int xp;
    private String name = "Chasseur";
    private double level_1 = ObjectsPreset.xpMaxHunter1;
    private double level_2 = ObjectsPreset.xpMaxHunter2;
    private double level_3 = ObjectsPreset.xpMaxHunter3;
    private double level_4 = ObjectsPreset.xpMaxHunter4;
    private double level_5 = ObjectsPreset.xpMaxHunter5;

    public static List<EntityType> listEntity = Arrays.asList(
            EntityType.BAT,
            EntityType.PIG,
            EntityType.COW,
            EntityType.RABBIT,
            EntityType.TROPICAL_FISH,
            EntityType.PUFFERFISH,
            EntityType.SALMON,
            EntityType.COD,
            EntityType.SQUID,
            EntityType.CAT,
            EntityType.FOX,
            EntityType.MULE,
            EntityType.SKELETON_HORSE,
            EntityType.TURTLE,
            EntityType.PANDA,
            EntityType.CHICKEN,
            EntityType.HORSE,
            EntityType.DONKEY,
            EntityType.LLAMA,
            EntityType.PARROT,
            EntityType.STRIDER,
            EntityType.MUSHROOM_COW
    );

    public Hunter(int xp) {
        this.xp  = xp;
    }

    public int getLevel() {
        return xp <= level_1 ? 1 : xp <= level_2 ? 2 : xp <= level_3 ? 3 : xp <= level_4 ? 4 : xp <= level_5 ? 5 : 6;
    }

    private int getXPLevel(int level, EntityType type) {
        if (type.equals(EntityType.BAT))
            if (level == 1 || level == 2 || level == 3 || level == 4 || level == 5)
                return 50;

        if (type.equals(EntityType.PIG))
            if (level == 1 || level == 2 || level == 3 || level == 4 || level == 5)
                return 5;

        if (type.equals(EntityType.COW))
            if (level == 1 || level == 2 || level == 4 || level == 5)
                return 5;

        if (type.equals(EntityType.RABBIT))
            if (level == 1 || level == 2 || level == 4 || level == 5)
                return 45;

        if (type.equals(EntityType.TROPICAL_FISH) || type.equals(EntityType.PUFFERFISH) || type.equals(EntityType.SALMON) || type.equals(EntityType.COD))
            if (level == 1 || level == 2 || level == 3 || level == 4 || level == 5)
                return 15;

        if (type.equals(EntityType.SQUID))
            if (level == 2 || level == 3 || level == 4 || level == 5)
                return 20;

        if (type.equals(EntityType.CAT))
            if (level == 2 || level == 3 || level == 4 || level == 5)
                return 30;
        if (type.equals(EntityType.FOX))
            if (level == 2 || level == 3 || level == 4 || level == 5)
                return 45;

        if (type.equals(EntityType.MULE))
            if (level == 3 || level == 4 || level == 5)
                return 30;

        if (type.equals(EntityType.SKELETON_HORSE))
            if (level == 3 || level == 4 || level == 5)
                return 70;

        if (type.equals(EntityType.TURTLE))
            if (level == 3 || level == 4 || level == 5)
                return 50;

        if (type.equals(EntityType.PANDA))
            if (level == 3 || level == 4 || level == 5)
                return 25;

        if (type.equals(EntityType.SHEEP))
            if (level == 4 || level == 5)
                return 5;

        if (type.equals(EntityType.CHICKEN))
            if (level == 4 || level == 5)
                return 5;

        if (type.equals(EntityType.HORSE))
            if (level == 4 || level == 5)
                return 10;

        if (type.equals(EntityType.DONKEY))
            if (level == 4 || level == 5)
                return 25;

        if (type.equals(EntityType.LLAMA))
            if (level == 4 || level == 5)
                return 30;

        if (type.equals(EntityType.PARROT))
            if (level == 4 || level == 5)
                return 70;

        if (type.equals(EntityType.STRIDER))
            if (level == 4 || level == 5)
                return 20;
        if (type.equals(EntityType.MUSHROOM_COW))
            if (level == 5)
                return 30;

        return 0;
    }

    public void getGiveXP(Player player, EntityType type) {
        if (type == null)
            return;
        if (getXPLevel(1, type) == 0) {
            return;
        }
        int xp = getXPLevel(1, type);
        if (Jobs.playersJobs.get(player).getNotification())
            ObjectsPreset.sendFakeNotification(player, AdvancementAPIFrameType.TASK, Material.DIAMOND_SWORD, ChatColor.GOLD + "§l" + name + ChatColor.YELLOW + "\n+" + xp + "xp");
        int old = getLevel();
        this.xp = this.xp + xp;
        if ((old + 1) == 6) {
            player.sendMessage(ChatColor.YELLOW + "Vous venez de passer au niveau " + ChatColor.GOLD + "Max" + ChatColor.YELLOW + " dans le métier §6§l" + this.name);
        } else
        if (getLevel() == (old+1))
            player.sendMessage(ChatColor.YELLOW + "Vous venez de passer au niveau " + ChatColor.GOLD + getLevel() + ChatColor.YELLOW + " dans le métier §6§l" + this.name);
    }

    public int getXP() {return xp;}

    public int getXPMAXLevel() {
        return (int) (xp <= level_1 ? level_1 : xp <= level_2 ? level_2 : xp <= level_3 ? level_3 : xp <= level_4 ? level_4 : level_5);
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

}
