package fr.lightnew.jobs;

import fr.lightnew.Jobs;
import fr.lightnew.component.AdvancementAPIFrameType;
import fr.lightnew.tools.ObjectsPreset;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

// Forgeron
public class Smith {

    private int xp;
    private String name = "Forgeron";
    private double level_1 = ObjectsPreset.xpMaxSmith1;
    private double level_2 = ObjectsPreset.xpMaxSmith2;
    private double level_3 = ObjectsPreset.xpMaxSmith3;
    private double level_4 = ObjectsPreset.xpMaxSmith4;
    private double level_5 = ObjectsPreset.xpMaxSmith5;
    public static List<ItemStack> listItems = Arrays.asList(
            new ItemStack(Material.IRON_INGOT),
            new ItemStack(Material.GOLD_INGOT),
            new ItemStack(Material.STONE),
            new ItemStack(Material.LEATHER_HELMET ),
            new ItemStack(Material.LEATHER_CHESTPLATE),
            new ItemStack(Material.LEATHER_LEGGINGS ),
            new ItemStack(Material.LEATHER_BOOTS),
            new ItemStack(Material.CHAINMAIL_HELMET ),
            new ItemStack(Material.CHAINMAIL_CHESTPLATE),
            new ItemStack(Material.CHAINMAIL_LEGGINGS ),
            new ItemStack(Material.CHAINMAIL_BOOTS),
            new ItemStack(Material.IRON_HELMET ),
            new ItemStack(Material.IRON_CHESTPLATE),
            new ItemStack(Material.IRON_LEGGINGS ),
            new ItemStack(Material.IRON_BOOTS),
            new ItemStack(Material.GOLDEN_HELMET ),
            new ItemStack(Material.GOLDEN_CHESTPLATE),
            new ItemStack(Material.GOLDEN_LEGGINGS ),
            new ItemStack(Material.GOLDEN_BOOTS),
            new ItemStack(Material.DIAMOND_HELMET ),
            new ItemStack(Material.DIAMOND_CHESTPLATE),
            new ItemStack(Material.DIAMOND_LEGGINGS ),
            new ItemStack(Material.DIAMOND_BOOTS),
            new ItemStack(Material.NETHERITE_HELMET ),
            new ItemStack(Material.NETHERITE_CHESTPLATE),
            new ItemStack(Material.NETHERITE_LEGGINGS ),
            new ItemStack(Material.NETHERITE_BOOTS),

            new ItemStack(Material.NETHERITE_PICKAXE),
            new ItemStack(Material.DIAMOND_PICKAXE),
            new ItemStack(Material.GOLDEN_PICKAXE),
            new ItemStack(Material.IRON_PICKAXE),
            new ItemStack(Material.STONE_PICKAXE),
            new ItemStack(Material.WOODEN_PICKAXE),

            new ItemStack(Material.NETHERITE_AXE),
            new ItemStack(Material.DIAMOND_AXE),
            new ItemStack(Material.GOLDEN_AXE),
            new ItemStack(Material.IRON_AXE),
            new ItemStack(Material.STONE_AXE),
            new ItemStack(Material.WOODEN_AXE),

            new ItemStack(Material.NETHERITE_SHOVEL),
            new ItemStack(Material.DIAMOND_SHOVEL),
            new ItemStack(Material.GOLDEN_SHOVEL),
            new ItemStack(Material.IRON_SHOVEL),
            new ItemStack(Material.STONE_SHOVEL),
            new ItemStack(Material.WOODEN_SHOVEL),

            new ItemStack(Material.NETHERITE_SWORD),
            new ItemStack(Material.DIAMOND_SWORD),
            new ItemStack(Material.GOLDEN_SWORD),
            new ItemStack(Material.IRON_SWORD),
            new ItemStack(Material.STONE_SWORD),
            new ItemStack(Material.WOODEN_SWORD),

            new ItemStack(Material.NETHERITE_HOE),
            new ItemStack(Material.DIAMOND_HOE),
            new ItemStack(Material.GOLDEN_HOE),
            new ItemStack(Material.IRON_HOE),
            new ItemStack(Material.STONE_HOE),
            new ItemStack(Material.WOODEN_HOE)
    );

    public Smith(int xp) {
        this.xp  = xp;
    }

    public int getLevel() {
        return xp <= level_1 ? 1 : xp <= level_2 ? 2 : xp <= level_3 ? 3 : xp <= level_4 ? 4 : xp <= level_5 ? 5 : 6;
    }

    private int getXPLevel(int level, ItemStack itemStack, int amount) {
        // Cook
        if (itemStack.getType().equals(Material.IRON_INGOT)) {
            if (level == 1)
                return (5*amount);
        }
        if (itemStack.getType().equals(Material.GOLD_INGOT)) {
            if (level == 1)
                return (5*amount);
        }
        if (itemStack.getType().equals(Material.STONE)) {
            if (level == 1)
                return (3*amount);
        }

        // Leather Stuff
        if (itemStack.getType().equals(Material.LEATHER_HELMET) || itemStack.getType().equals(Material.LEATHER_CHESTPLATE)  || itemStack.getType().equals(Material.LEATHER_LEGGINGS) || itemStack.getType().equals(Material.LEATHER_BOOTS)) {
            switch (level) {
                case 1:
                    return 10;
                case 2:
                case 3:
                    return 25;
                case 4:
                case 5:
                    return 70;
            }
        }
        // Chainmail Stuff
        if (itemStack.getType().equals(Material.CHAINMAIL_HELMET) || itemStack.getType().equals(Material.CHAINMAIL_CHESTPLATE)  || itemStack.getType().equals(Material.CHAINMAIL_LEGGINGS) || itemStack.getType().equals(Material.CHAINMAIL_BOOTS)) {
            switch (level) {
                case 1:
                    return 10;
                case 2:
                case 3:
                    return 20;
                case 4:
                case 5:
                    return 35;
            }
        }
        // Iron Stuff
        if (itemStack.getType().equals(Material.IRON_HELMET) || itemStack.getType().equals(Material.IRON_CHESTPLATE)  || itemStack.getType().equals(Material.IRON_LEGGINGS) || itemStack.getType().equals(Material.IRON_BOOTS)) {
            switch (level) {
                case 1:
                    return 10;
                case 2:
                case 3:
                    return 25;
                case 4:
                case 5:
                    return 70;
            }
        }
        // Golden Stuff
        if (itemStack.getType().equals(Material.GOLDEN_HELMET) || itemStack.getType().equals(Material.GOLDEN_CHESTPLATE)  || itemStack.getType().equals(Material.GOLDEN_LEGGINGS) || itemStack.getType().equals(Material.GOLDEN_BOOTS)) {
            switch (level) {
                case 1:
                    return 10;
                case 2:
                case 3:
                    return 25;
                case 4:
                case 5:
                    return 70;
            }
        }
        // Diamond Stuff
        if (itemStack.getType().equals(Material.DIAMOND_HELMET) || itemStack.getType().equals(Material.DIAMOND_CHESTPLATE)  || itemStack.getType().equals(Material.DIAMOND_LEGGINGS) || itemStack.getType().equals(Material.DIAMOND_BOOTS)) {
            switch (level) {
                case 1:
                    return 10;
                case 2:
                case 3:
                    return 25;
                case 4:
                case 5:
                    return 50;
            }
        }
        // Netherite Stuff
        if (itemStack.getType().equals(Material.NETHERITE_HELMET) || itemStack.getType().equals(Material.NETHERITE_CHESTPLATE)  || itemStack.getType().equals(Material.NETHERITE_LEGGINGS) || itemStack.getType().equals(Material.NETHERITE_BOOTS)) {
            switch (level) {
                case 1:
                    return 10;
                case 2:
                    return 50;
                case 3:
                    return 50;
                case 4:
                    return 100;
                case 5:
                    return 100;
            }
        }

        if (itemStack.getType().equals(Material.NETHERITE_PICKAXE) || itemStack.getType().equals(Material.NETHERITE_AXE) || itemStack.getType().equals(Material.NETHERITE_SHOVEL) || itemStack.getType().equals(Material.NETHERITE_SWORD) || itemStack.getType().equals(Material.NETHERITE_HOE)) {
            switch (level) {
                case 1:
                case 2:
                    return 0;
                case 3:
                    return 50;
                case 4:
                    return 150;
                case 5:
                    return 250;
            }
        }

        if (itemStack.getType().equals(Material.DIAMOND_PICKAXE) || itemStack.getType().equals(Material.DIAMOND_AXE) || itemStack.getType().equals(Material.DIAMOND_SHOVEL) || itemStack.getType().equals(Material.DIAMOND_SWORD) || itemStack.getType().equals(Material.DIAMOND_HOE)) {
            switch (level) {
                case 1:
                    return 0;
                case 2:
                case 3:
                    return 25;
                case 4:
                case 5:
                    return 100;
            }
        }

        if (itemStack.getType().equals(Material.GOLDEN_PICKAXE) || itemStack.getType().equals(Material.GOLDEN_AXE) || itemStack.getType().equals(Material.GOLDEN_SHOVEL) || itemStack.getType().equals(Material.GOLDEN_SWORD) || itemStack.getType().equals(Material.GOLDEN_HOE)) {
            switch (level) {
                case 1:
                    return 15;
                case 2:
                case 3:
                    return 25;
                case 4:
                case 5:
                    return 30;
            }
        }

        if (itemStack.getType().equals(Material.IRON_PICKAXE) || itemStack.getType().equals(Material.IRON_AXE) || itemStack.getType().equals(Material.IRON_SHOVEL) || itemStack.getType().equals(Material.IRON_SWORD) || itemStack.getType().equals(Material.IRON_HOE)) {
            switch (level) {
                case 1:
                    return 10;
                case 2:
                case 3:
                    return 20;
                case 4:
                case 5:
                    return 25;
            }
        }

        if (itemStack.getType().equals(Material.STONE_PICKAXE) || itemStack.getType().equals(Material.STONE_AXE) || itemStack.getType().equals(Material.STONE_SHOVEL) || itemStack.getType().equals(Material.STONE_SWORD) || itemStack.getType().equals(Material.STONE_HOE)) {
            switch (level) {
                case 1:
                    return 3;
                case 2:
                case 3:
                    return 5;
                case 4:
                case 5:
                    return 7;
            }
        }

        if (itemStack.getType().equals(Material.WOODEN_PICKAXE) || itemStack.getType().equals(Material.WOODEN_AXE) || itemStack.getType().equals(Material.WOODEN_SHOVEL) || itemStack.getType().equals(Material.WOODEN_SWORD) || itemStack.getType().equals(Material.WOODEN_HOE)) {
            switch (level) {
                case 1:
                    return 2;
                case 2:
                case 3:
                    return 4;
                case 4:
                case 5:
                    return 7;
            }
        }

        return 0;
    }

    public void getGiveXP(Player player, ItemStack itemStack, int amount) {
        if (itemStack == null)
            return;
        if (getXPLevel(1, itemStack, amount) == 0)
            return;
        int xp = getXPLevel(1, itemStack, amount);
        if (Jobs.playersJobs.get(player).getNotification())
            ObjectsPreset.sendFakeNotification(player, AdvancementAPIFrameType.TASK, Material.ANVIL, ChatColor.GOLD + "§l" + name + ChatColor.YELLOW + "\n+" + xp + "xp");
        int old = getLevel();
        this.xp = this.xp + xp;
        if ((old + 1) == 6) {
            player.sendMessage(ChatColor.YELLOW + "Vous venez de passer au niveau " + ChatColor.GOLD + "Max" + ChatColor.YELLOW + " dans le métier §6§l" + this.name);
        } else
        if (getLevel() == (old+1)) {
            player.sendMessage(ChatColor.YELLOW + "Vous venez de passer au niveau " + ChatColor.GOLD + getLevel() + ChatColor.YELLOW + " dans le métier §6§l" + this.name);
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_TRADE, 5, 5);
        }
    }

    public int getXP() {
        return xp;
    }

    public int getXPMAXLevel() {
        return (int) (xp <= level_1 ? level_1 : xp <= level_2 ? level_2 : xp <= level_3 ? level_3 : xp <= level_4 ? level_4 : level_5);
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

}
