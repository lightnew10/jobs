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
        if (type.equals(EntityType.BAT)) {
            switch (level) {
                case 1:
                    return 0;
                case 2:
                    return 0;
                case 3:
                    return 50;
                case 4:
                    return 70;
                case 5:
                    return 80;
            }
        }

        if (type.equals(EntityType.PIG)) {
            switch (level) {
                case 1:
                case 2:
                    return 5;
                case 3:
                case 4:
                    return 7;
                case 5:
                    return 10;
            }
        }

        if (type.equals(EntityType.COW)) {
            switch (level) {
                case 1:
                case 2:
                case 3:
                    return 5;
                case 4:
                    return 7;
                case 5:
                    return 10;
            }
        }

        if (type.equals(EntityType.RABBIT)) {
            switch (level) {
                case 1:
                case 2:
                    return 30;
                case 3:
                    return 50;
                case 4:
                    return 60;
                case 5:
                    return 70;
            }
        }

        if (type.equals(EntityType.TROPICAL_FISH) || type.equals(EntityType.PUFFERFISH) || type.equals(EntityType.SALMON) || type.equals(EntityType.COD)) {
            switch (level) {
                case 1:
                    return 20;
                case 2:
                    return 30;
                case 3:
                    return 40;
                case 4:
                    return 50;
                case 5:
                    return 60;
            }
        }

        if (type.equals(EntityType.SQUID)) {
            switch (level) {
                case 1:
                    return 20;
                case 2:
                    return 25;
                case 3:
                    return 35;
                case 4:
                    return 35;
                case 5:
                    return 40;
            }
        }

        if (type.equals(EntityType.CAT)) {
            switch (level) {
                case 1:
                    return 20;
                case 2:
                case 3:
                    return 30;
                case 4:
                    return 35;
                case 5:
                    return 40;
            }
        }

        if (type.equals(EntityType.FOX)) {
            switch (level) {
                case 1:
                    return 0;
                case 2:
                    return 25;
                case 3:
                    return 35;
                case 4:
                    return 40;
                case 5:
                    return 45;
            }
        }

        if (type.equals(EntityType.SKELETON_HORSE)) {
            switch (level) {
                case 1:
                    return 70;
                case 2:
                    return 75;
                case 3:
                    return 60;
                case 4:
                    return 55;
                case 5:
                    return 50;
            }
        }

        if (type.equals(EntityType.TURTLE)) {
            switch (level) {
                case 1:
                case 2:
                    return 50;
                case 3:
                    return 40;
                case 4:
                    return 35;
                case 5:
                    return 30;
            }
        }

        if (type.equals(EntityType.PANDA)) {
            switch (level) {
                case 1:
                    return 15;
                case 2:
                    return 20;
                case 3:
                    return 30;
                case 4:
                    return 35;
                case 5:
                    return 40;
            }
        }

        if (type.equals(EntityType.SHEEP)) {
            switch (level) {
                case 1:
                    return 0;
                case 2:
                    return 0;
                case 3:
                    return 50;
                case 4:
                    return 70;
                case 5:
                    return 80;
            }
        }

        if (type.equals(EntityType.CHICKEN)) {
            switch (level) {
                case 1:
                case 2:
                    return 5;
                case 3:
                    return 7;
                case 4:
                    return 10;
                case 5:
                    return 15;
            }
        }

        if (type.equals(EntityType.STRIDER)) {
            switch (level) {
                case 1:
                case 2:
                    return 20;
                case 3:
                    return 25;
                case 4:
                    return 30;
                case 5:
                    return 35;
            }
        }

        if (type.equals(EntityType.MUSHROOM_COW)) {
            switch (level) {
                case 1:
                case 2:
                case 3:
                    return 5;
                case 4:
                    return 7;
                case 5:
                    return 10;
            }
        }

        return 0;
    }

    public int getGiveXP(Player player, EntityType type) {
        if (type == null)
            return 0;
        if (getXPLevel(1, type) == 0) {
            return 0;
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
        return xp;
    }

    public int getXP() {return xp;}

    public int getXPMAXLevel() {
        return (int) (xp <= level_1 ? level_1 : xp <= level_2 ? level_2 : xp <= level_3 ? level_3 : xp <= level_4 ? level_4 : level_5);
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

}
