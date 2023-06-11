package fr.lightnew.jobs;

import fr.lightnew.Jobs;
import fr.lightnew.component.AdvancementAPIFrameType;
import fr.lightnew.tools.ObjectsPreset;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class Lumberjack {


    private int xp;
    private String name = "Bucheron";
    private double level_1 = ObjectsPreset.xpMaxLumberjack1;
    private double level_2 = ObjectsPreset.xpMaxLumberjack2;
    private double level_3 = ObjectsPreset.xpMaxLumberjack3;
    private double level_4 = ObjectsPreset.xpMaxLumberjack4;
    private double level_5 = ObjectsPreset.xpMaxLumberjack5;

    public static List<ItemStack> listItems = Arrays.asList(
            new ItemStack(Material.OAK_LOG),
            new ItemStack(Material.SPRUCE_LOG),
            new ItemStack(Material.BIRCH_LOG),
            new ItemStack(Material.JUNGLE_LOG),
            new ItemStack(Material.ACACIA_LOG)
    );

    public Lumberjack(int xp) {
        this.xp  = xp;
    }

    public int getLevel() {
        return xp <= level_1 ? 1 : xp <= level_2 ? 2 : xp <= level_3 ? 3 : xp <= level_4 ? 4 : xp <= level_5 ? 5 : 6;
    }

    private int getXPLevel(int level, ItemStack itemStack) {
        if (itemStack.getType().equals(Material.OAK_LOG)) {
            if (level == 1)
                return 10;
            if (level == 2)
                return 20;
            if (level == 3)
                return 25;
            if (level == 4)
                return 40;
            if (level == 5)
                return 50;
        }
        if (itemStack.getType().equals(Material.SPRUCE_LOG)) {
            if (level == 1)
                return 15;
            if (level == 2)
                return 20;
            if (level == 3)
                return 30;
            if (level == 4)
                return 55;
            if (level == 5)
                return 65;
        }
        if (itemStack.getType().equals(Material.BIRCH_LOG)) {
            if (level == 1)
                return 15;
            if (level == 2)
                return 20;
            if (level == 3)
                return 30;
            if (level == 4)
                return 55;
            if (level == 5)
                return 65;
        }
        if (itemStack.getType().equals(Material.JUNGLE_LOG)) {
            if (level == 1 || level == 2)
                return 20;
            if (level == 3)
                return 35;
            if (level == 4)
                return 65;
            if (level == 5)
                return 70;
        }
        if (itemStack.getType().equals(Material.ACACIA_LOG)) {
            if (level == 1 || level == 2)
                return 20;
            if (level == 3)
                return 35;
            if (level == 4)
                return 65;
            if (level == 5)
                return 70;
        }
        return 0;
    }

    public void getGiveXP(Player player, ItemStack itemStack) {
        if (itemStack == null)
            return;
        if (getXPLevel(1, itemStack) == 0) {
            return;
        }
        int xp = getXPLevel(1, itemStack);
        if (Jobs.playersJobs.get(player).getNotification())
            ObjectsPreset.sendFakeNotification(player, AdvancementAPIFrameType.TASK, Material.DIAMOND_AXE, ChatColor.GOLD + "§l" + name + ChatColor.YELLOW + "\n+" + xp + "xp");
        int old = getLevel();
        this.xp = this.xp + xp;
        if ((old + 1) == 6) {
            player.sendMessage(ChatColor.YELLOW + "Vous venez de passer au niveau " + ChatColor.GOLD + "Max" + ChatColor.YELLOW + " dans le métier §6§l" + this.name);
        } else
        if (getLevel() == (old+1))
            player.sendMessage(ChatColor.YELLOW + "Vous venez de passer au niveau " + ChatColor.GOLD + getLevel() + ChatColor.YELLOW + " dans le métier §6§l" + this.name);
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
