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

public class Peasant {

    private int xp;
    private String name = "Paysan";
    private double level_1 = ObjectsPreset.xpMaxPeasant1;
    private double level_2 = ObjectsPreset.xpMaxPeasant2;
    private double level_3 = ObjectsPreset.xpMaxPeasant3;
    private double level_4 = ObjectsPreset.xpMaxPeasant4;
    private double level_5 = ObjectsPreset.xpMaxPeasant5;
    public static List<ItemStack> listItems = Arrays.asList(
            new ItemStack(Material.WHEAT_SEEDS),
            new ItemStack(Material.BEETROOT_SEEDS),
            new ItemStack(Material.POTATOES),
            new ItemStack(Material.CARROTS),
            new ItemStack(Material.PUMPKIN),
            new ItemStack(Material.MELON),
            new ItemStack(Material.COCOA_BEANS)
    );

    public Peasant(int xp) {
        this.xp  = xp;
    }

    public int getLevel() {
        return xp <= level_1 ? 1 : xp <= level_2 ? 2 : xp <= level_3 ? 3 : xp <= level_4 ? 4 : xp <= level_5 ? 5 : 6;
    }

    private int getXPLevel(int level, ItemStack itemStack) {
        if (itemStack.getType().equals(Material.WHEAT)) {
            if (level == 1 || level == 2)
                return 10;
            if (level == 3 || level == 4 || level == 5)
                return 20;
        }
        if (itemStack.getType().equals(Material.BEETROOT)) {
            if (level == 1 || level == 2)
                return 20;
            if (level == 3)
                return 30;
            if (level == 4)
                return 35;
            if (level == 5)
                return 40;
        }
        if (itemStack.getType().equals(Material.POTATO)) {
            if (level == 1 || level == 2)
                return 15;
            if (level == 3)
                return 25;
            if (level == 4)
                return 30;
            if (level == 5)
                return 35;
        }
        if (itemStack.getType().equals(Material.CARROT)) {
            if (level == 1)
                return 15;
            if (level == 2)
                return 20;
            if (level == 3 || level == 4)
                return 25;
            if (level == 5)
                return 30;
        }
        if (itemStack.getType().equals(Material.PUMPKIN)) {
            if (level == 1)
                return 30;
            if (level == 2)
                return 35;
            if (level == 3)
                return 40;
            if (level == 4)
                return 45;
            if (level == 5)
                return 50;
        }
        if (itemStack.getType().equals(Material.MELON)) {
            if (level == 1)
                return 30;
            if (level == 2)
                return 35;
            if (level == 3)
                return 40;
            if (level == 4)
                return 45;
            if (level == 5)
                return 50;
        }
        if (itemStack.getType().equals(Material.CACTUS)) {
            if (level == 1)
                return 50;
            if (level == 2)
                return 50;
            if (level == 3)
                return 60;
            if (level == 4)
                return 70;
            if (level == 5)
                return 75;
        }
        if (itemStack.getType().equals(Material.COCOA)) {
            if (level == 1)
                return 35;
            if (level == 2)
                return 40;
            if (level == 3)
                return 45;
            if (level == 4)
                return 55;
            if (level == 5)
                return 35;
        }
        if (itemStack.getType().equals(Material.BROWN_MUSHROOM) || itemStack.getType().equals(Material.RED_MUSHROOM)) {
            if (level == 1)
                return 60;
            if (level == 2)
                return 70;
            if (level == 3)
                return 80;
            if (level == 4)
                return 85;
            if (level == 5)
                return 95;
        }
        if (itemStack.getType().equals(Material.NETHER_WART)) {
            if (level == 1 || level == 2)
                return 20;
            if (level == 3)
                return 30;
            if (level == 4)
                return 45;
            if (level == 5)
                return 55;
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
            ObjectsPreset.sendFakeNotification(player, AdvancementAPIFrameType.TASK, Material.DIAMOND_HOE, ChatColor.GOLD + "§l" + name + ChatColor.YELLOW + "\n+" + xp + "xp");
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
