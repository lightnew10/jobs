package fr.lightnew.tools;

import fr.lightnew.Jobs;
import fr.lightnew.jobs.JobsManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GUIJobs {

    public static ItemStack HEAD;

    public static void send(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 4*9, ChatColor.YELLOW + "Jobs de " + ChatColor.GOLD + player.getName());
        JobsManager jobsManager = Jobs.playersJobs.get(player);
        String[] xpBarAlchemist = xpBar(jobsManager.getAlchemist().getXP(), jobsManager.getAlchemist().getXPMAXLevel(), 10);
        String[] xpBarFisherman = xpBar(jobsManager.getFisherman().getXP(), jobsManager.getFisherman().getXPMAXLevel(), 10);
        String[] xpBarHunter = xpBar(jobsManager.getHunter().getXP(), jobsManager.getHunter().getXPMAXLevel(), 10);
        String[] xpBarLumberjack = xpBar(jobsManager.getLumberjack().getXP(), jobsManager.getLumberjack().getXPMAXLevel(), 10);
        String[] xpBarMinor = xpBar(jobsManager.getMinor().getXP(), jobsManager.getMinor().getXPMAXLevel(), 10);
        String[] xpBarPeasant = xpBar(jobsManager.getPeasant().getXP(), jobsManager.getPeasant().getXPMAXLevel(), 10);
        String[] xpBarSmith = xpBar(jobsManager.getSmithing().getXP(), jobsManager.getSmithing().getXPMAXLevel(), 10);

        String notif;
        if (jobsManager.getNotification())
            notif = ChatColor.GREEN + "Activé";
        else
            notif = ChatColor.RED + "Désactivé";

        HEAD = ItemBuilder.skull(1, player.getName(), player.getName(), ChatColor.YELLOW + "Notification : " + notif);
        ItemStack ALCHEMIST = ItemBuilder.create(Material.POTION, 1, ChatColor.YELLOW + "Alchimiste Nv" + ChatColor.GOLD + "" + jobsManager.getAlchemist().getLevel(), xpBarAlchemist[0], ChatColor.YELLOW + "" + jobsManager.getAlchemist().getXP() + ChatColor.GRAY + "/" + ChatColor.GOLD + jobsManager.getAlchemist().getXPMAXLevel() + ChatColor.GRAY + " (" + xpBarAlchemist[1] + "%)");
        ItemStack FISHERMAN = ItemBuilder.create(Material.COD, 1, ChatColor.YELLOW + "Pêcheur Nv" + ChatColor.GOLD + "" + jobsManager.getFisherman().getLevel(), xpBarFisherman[0], ChatColor.YELLOW + "" + jobsManager.getFisherman().getXP() + ChatColor.GRAY + "/" + ChatColor.GOLD + jobsManager.getFisherman().getXPMAXLevel() + ChatColor.GRAY + " (" + xpBarFisherman[1] + "%)");
        ItemStack HUNTER = ItemBuilder.create(Material.SKELETON_SKULL, 1, ChatColor.YELLOW + "Chasseur Nv" + ChatColor.GOLD + "" + jobsManager.getHunter().getLevel(), xpBarHunter[0], ChatColor.YELLOW + "" + jobsManager.getHunter().getXP() + ChatColor.GRAY + "/" + ChatColor.GOLD + jobsManager.getHunter().getXPMAXLevel() + ChatColor.GRAY + " (" + xpBarHunter[1] + "%)");
        ItemStack LUMBERJACK = ItemBuilder.create(Material.OAK_LOG, 1, ChatColor.YELLOW + "Bucheron Nv" + ChatColor.GOLD + "" + jobsManager.getLumberjack().getLevel(), xpBarLumberjack[0], ChatColor.YELLOW + "" + jobsManager.getLumberjack().getXP() + ChatColor.GRAY + "/" + ChatColor.GOLD + jobsManager.getLumberjack().getXPMAXLevel() + ChatColor.GRAY + " (" + xpBarLumberjack[1] + "%)");
        ItemStack MINOR = ItemBuilder.create(Material.DIAMOND_ORE, 1, ChatColor.YELLOW + "Mineur Nv" + ChatColor.GOLD + "" + jobsManager.getMinor().getLevel(), xpBarMinor[0], ChatColor.YELLOW + "" + jobsManager.getMinor().getXP() + ChatColor.GRAY + "/" + ChatColor.GOLD + jobsManager.getMinor().getXPMAXLevel() + ChatColor.GRAY + " (" + xpBarMinor[1] + "%)");;
        ItemStack PEASANT = ItemBuilder.create(Material.WHEAT, 1, ChatColor.YELLOW + "Paysan Nv" + ChatColor.GOLD + "" + jobsManager.getPeasant().getLevel(), xpBarPeasant[0], ChatColor.YELLOW + "" + jobsManager.getPeasant().getXP() + ChatColor.GRAY + "/" + ChatColor.GOLD + jobsManager.getPeasant().getXPMAXLevel() + ChatColor.GRAY + " (" + xpBarPeasant[1] + "%)");
        ItemStack SMITH = ItemBuilder.create(Material.FURNACE, 1, ChatColor.YELLOW + "Forgeron Nv" + ChatColor.GOLD + "" + jobsManager.getSmithing().getLevel(), xpBarSmith[0], ChatColor.YELLOW + "" + jobsManager.getSmithing().getXP() + ChatColor.GRAY + "/" + ChatColor.GOLD + jobsManager.getSmithing().getXPMAXLevel() + ChatColor.GRAY + " (" + xpBarSmith[1] + "%)");

        inventory.setItem(4, HEAD);
        inventory.setItem(10, ALCHEMIST);
        inventory.setItem(12, FISHERMAN);
        inventory.setItem(14, HUNTER);
        inventory.setItem(16, LUMBERJACK);
        inventory.setItem(20, MINOR);
        inventory.setItem(22, PEASANT);
        inventory.setItem(24, SMITH);

        player.openInventory(inventory);
    }

    private static String[] xpBar(int xp, int xpmax, int tranch) {
        if (xpmax == 0)
            xpmax = (tranch+1);
        if (xp >= xpmax)
            xp = xpmax;

        if ((xpmax / tranch) <= 0) {
            return null;
        }

        int quart = (xpmax/tranch);
        String tr = "█";

        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < tranch + 1; i++) {
            if (xp >= quart*i)
                builder.append(ChatColor.GREEN + tr + ChatColor.RESET);
            else
                builder.append(ChatColor.GRAY + tr);
        }
        return new String[]{builder.toString(), Integer.toString((100*xp/xpmax))};
    }
}
