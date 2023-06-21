package fr.lightnew.jobs;

import fr.lightnew.Jobs;
import fr.lightnew.component.AdvancementAPIFrameType;
import fr.lightnew.tools.ObjectsPreset;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

// Mineur
public class Miner {

    private int xp;
    private String name = "Mineur";
    private double level_1 = ObjectsPreset.xpMaxMinor1;
    private double level_2 = ObjectsPreset.xpMaxMinor2;
    private double level_3 = ObjectsPreset.xpMaxMinor3;
    private double level_4 = ObjectsPreset.xpMaxMinor4;
    private double level_5 = ObjectsPreset.xpMaxMinor5;
    public static List<ItemStack> listItems = Arrays.asList(
            new ItemStack(Material.STONE),
            new ItemStack(Material.GRANITE),
            new ItemStack(Material.COAL_ORE),
            new ItemStack(Material.GOLD_ORE),
            new ItemStack(Material.IRON_ORE),
            new ItemStack(Material.NETHER_QUARTZ_ORE),
            new ItemStack(Material.REDSTONE_ORE),
            new ItemStack(Material.LAPIS_ORE),
            new ItemStack(Material.NETHER_GOLD_ORE),
            new ItemStack(Material.DIAMOND_ORE),
            new ItemStack(Material.EMERALD_ORE)
    );

    public Miner(int xp) {
        this.xp  = xp;
    }

    public int getLevel() {
        return xp <= level_1 ? 1 : xp <= level_2 ? 2 : xp <= level_3 ? 3 : xp <= level_4 ? 4 : xp <= level_5 ? 5 : 6;
    }

    private int getXPLevel(int level, ItemStack itemStack) {
        if (itemStack.getType().equals(Material.STONE)) {
            switch (level) {
                case 1:
                case 2:
                    return 5;
                case 3:
                case 4:
                case 5:
                    return 0;
            }
        }
        if (itemStack.getType().equals(Material.GRANITE)) {
            switch (level) {
                case 1:
                case 2:
                    return 5;
                case 3:
                case 4:
                case 5:
                    return 0;
            }
        }
        if (itemStack.getType().equals(Material.COAL_ORE)) {
            switch (level) {
                case 1:
                case 2:
                    return 5;
                case 3:
                case 4:
                case 5:
                    return 0;
            }
        }
        if (itemStack.getType().equals(Material.GOLD_ORE)) {
            switch (level) {
                case 1:
                    return 0;
                case 2:
                    return 30;
                case 3:
                    return 35;
                case 4:
                    return 40;
                case 5:
                    return 50;
            }
        }
        if (itemStack.getType().equals(Material.IRON_ORE)) {
            switch (level) {
                case 1:
                    return 10;
                case 2:
                    return 10;
                case 3:
                    return 20;
                case 4:
                    return 25;
                case 5:
                    return 30;
            }
        }
        if (itemStack.getType().equals(Material.NETHER_QUARTZ_ORE)) {
            switch (level) {
                case 1:
                    return 15;
                case 2:
                    return 25;
                case 3:
                    return 35;
                case 4:
                    return 45;
                case 5:
                    return 55;
            }
        }
        if (itemStack.getType().equals(Material.REDSTONE_ORE)) {
            switch (level) {
                case 1:
                case 2:
                    return 20;
                case 3:
                case 4:
                    return 25;
                case 5:
                    return 30;
            }
        }
        if (itemStack.getType().equals(Material.LAPIS_ORE)) {
            switch (level) {
                case 1:
                    return 25;
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
        if (itemStack.getType().equals(Material.NETHER_GOLD_ORE)) {
            switch (level) {
                case 1:
                case 2:
                    return 15;
                case 3:
                    return 30;
                case 4:
                    return 35;
                case 5:
                    return 40;
            }
        }
        if (itemStack.getType().equals(Material.DIAMOND_ORE)) {
            switch (level) {
                case 1:
                    return 35;
                case 2:
                    return 40;
                case 3:
                    return 45;
                case 4:
                    return 50;
                case 5:
                    return 60;
            }
        }
        if (itemStack.getType().equals(Material.EMERALD_ORE)) {
            switch (level) {
                case 1:
                    return 70;
                case 2:
                    return 75;
                case 3:
                    return 80;
                case 4:
                    return 90;
                case 5:
                    return 95;
            }
        }
        return 0;
    }

    public int getGiveXP(Player player, ItemStack itemStack) {
        if (itemStack == null)
            return 0;
        if (getXPLevel(1, itemStack) == 0) {
            return 0;
        }
        int xp = getXPLevel(1, itemStack);
        if (Jobs.playersJobs.get(player).getNotification())
            ObjectsPreset.sendFakeNotification(player, AdvancementAPIFrameType.TASK, Material.DIAMOND_PICKAXE, ChatColor.GOLD + "§l" + name + ChatColor.YELLOW + "\n+" + xp + "xp");
        int old = getLevel();
        this.xp = this.xp + xp;
        if ((old + 1) == 6) {
            player.sendMessage(ChatColor.YELLOW + "Vous venez de passer au niveau " + ChatColor.GOLD + "Max" + ChatColor.YELLOW + " dans le métier §6§l" + this.name);
        } else
        if (getLevel() == (old+1))
            player.sendMessage(ChatColor.YELLOW + "Vous venez de passer au niveau " + ChatColor.GOLD + getLevel() + ChatColor.YELLOW + " dans le métier §6§l" + this.name);
        return xp;
    }

    public int getAmountGiveXP(ItemStack itemStack) {
        if (itemStack == null)
            return 0;
        if (getXPLevel(1, itemStack) == 0) {
            return 0;
        }
        int xp = getXPLevel(1, itemStack);
        this.xp = this.xp + xp;
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
