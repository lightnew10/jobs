package fr.lightnew.jobs;

import fr.lightnew.Jobs;
import fr.lightnew.component.AdvancementAPIFrameType;
import fr.lightnew.tools.ObjectsPreset;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Fisherman {


    private int xp;
    private String name = "Pêcheur";
    private double level_1 = ObjectsPreset.xpMaxFisherman1;
    private double level_2 = ObjectsPreset.xpMaxFisherman2;
    private double level_3 = ObjectsPreset.xpMaxFisherman3;
    private double level_4 = ObjectsPreset.xpMaxFisherman4;
    private double level_5 = ObjectsPreset.xpMaxFisherman5;

    public Fisherman(int xp) {
        this.xp  = xp;
    }

    public int getLevel() {
        return xp <= level_1 ? 1 : xp <= level_2 ? 2 : xp <= level_3 ? 3 : xp <= level_4 ? 4 : xp <= level_5 ? 5 : 6;
    }

    private int getXPLevel(int level, ItemStack itemStack) {
        // Fish
        if (itemStack.getType().equals(Material.COD)) {
            if (level == 1)
                return 20;
            if (level == 2 || level == 3)
                return 35;
            if (level == 4)
                return 45;
            if (level == 5)
                return 50;
        }
        if (itemStack.getType().equals(Material.SALMON)) {
            if (level == 1)
                return 35;
            if (level == 2)
                return 70;
            if (level == 3)
                return 100;
            if (level == 4)
                return 110;
            if (level == 5)
                return 130;
        }
        if (itemStack.getType().equals(Material.TROPICAL_FISH)) {
            if (level == 1)
                return 120;
            if (level == 2)
                return 150;
            if (level == 3 || level == 4 || level == 5)
                return 175;
        }
        if (itemStack.getType().equals(Material.PUFFERFISH)) {
            if (level == 1)
                return 100;
            if (level == 2)
                return 190;
            if (level == 3)
                return 210;
            if (level == 4 || level == 5)
                return 220;
        }
        // Treasure
        if (itemStack.getType().equals(Material.BOW)) {
            if (level == 1)
                return 45;
            if (level == 2 || level == 3)
                return 85;
            if (level == 4)
                return 95;
            if (level == 5)
                return 120;
        }
        if (itemStack.getType().equals(Material.ENCHANTED_BOOK)) {
            if (level == 1)
                return 45;
            if (level == 2 || level == 3)
                return 90;
            if (level == 4)
                return 110;
            if (level == 5)
                return 140;
        }
        if (itemStack.getType().equals(Material.FISHING_ROD)) {
            if (level == 1)
                return 30;
            if (level == 2 || level == 3)
                return 60;
            if (level == 4)
                return 100;
            if (level == 5)
                return 120;
        }
        if (itemStack.getType().equals(Material.NAME_TAG)) {
            if (level == 1)
                return 90;
            if (level == 2 || level == 3)
                return 135;
            if (level == 4 || level == 5)
                return 150;
        }
        if (itemStack.getType().equals(Material.NAUTILUS_SHELL)) {
            if (level == 1)
                return 80;
            if (level == 2 || level == 3)
                return 140;
            if (level == 4 || level == 5)
                return 170;
        }
        if (itemStack.getType().equals(Material.SADDLE)) {
            if (level == 1)
                return 100;
            if (level == 2)
                return 150;
            if (level == 3)
                return 170;
            if (level == 4 || level == 5)
                return 200;
        }
        // Waste
        if (level == 1)
            return 15;
        if (level == 2 || level == 3)
            return 35;
        if (level == 4)
            return 45;
        if (level == 5)
            return 50;
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
            ObjectsPreset.sendFakeNotification(player, AdvancementAPIFrameType.TASK, Material.FISHING_ROD, ChatColor.GOLD + "§l" + name + ChatColor.YELLOW + "\n+" + xp + "xp");
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
