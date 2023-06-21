package fr.lightnew.event;

import fr.lightnew.Jobs;
import fr.lightnew.component.AdvancementAPIFrameType;
import fr.lightnew.jobs.*;
import fr.lightnew.tools.GUIJobs;
import fr.lightnew.tools.ObjectsPreset;
import org.bukkit.*;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

public class JobsEvents implements Listener {

    public WeakHashMap<Player, Integer> countXP = new WeakHashMap<>();
    public WeakHashMap<Player, Integer> miningProgressTime = new WeakHashMap<>();
    public WeakHashMap<Player, Integer> peasantProgressTime = new WeakHashMap<>();
    public WeakHashMap<Player, Integer> lumberjackProgressTime = new WeakHashMap<>();
    public WeakHashMap<Player, Integer> countXPPeasant = new WeakHashMap<>();
    public WeakHashMap<Player, Integer> countXPLumberjack = new WeakHashMap<>();
    public List<Player> miningProgress = new ArrayList<>();
    public List<Player> peasantProgress = new ArrayList<>();
    public List<Player> lumberjackProgress = new ArrayList<>();

    @EventHandler
    public void alchemistConsume(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        ItemStack potionItem = event.getItem();
        if (potionItem.getItemMeta() instanceof PotionMeta && potionItem.getType().equals(Material.POTION)) {
            PotionMeta potionMeta = (PotionMeta) potionItem.getItemMeta();
            JobsManager jobsManager = Jobs.playersJobs.get(player);
            if (jobsManager.getAlchemist().getLevel() != 6)
                jobsManager.getAlchemist().getGiveXP(player, potionMeta.getBasePotionData().getType());
        }
    }

    @EventHandler
    public void alchemistPotionSplash(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (event.getItem() == null)
                return;
            if (event.getItem().getType().equals(Material.SPLASH_POTION) || event.getItem().getType().equals(Material.LINGERING_POTION)) {
                ItemStack potionItem = event.getItem();
                if (potionItem.getItemMeta() instanceof PotionMeta) {
                    Player player = event.getPlayer();
                    PotionMeta potionMeta = (PotionMeta) potionItem.getItemMeta();
                    if (Alchemist.listPotion.contains(potionMeta.getBasePotionData().getType())) {
                        JobsManager jobsManager = Jobs.playersJobs.get(player);
                        if (jobsManager.getAlchemist().getLevel() != 6)
                            jobsManager.getAlchemist().getGiveXP(player, potionMeta.getBasePotionData().getType());
                    }
                }
            }
        }
    }

    @EventHandler
    public void hunter(EntityDeathEvent event) {
        if (event.getEntity().getKiller() == null)
            return;
        Player player = event.getEntity().getKiller();
        if (Hunter.listEntity.contains(event.getEntity().getType())) {
            JobsManager jobsManager = Jobs.playersJobs.get(player);
            if (jobsManager.getHunter().getLevel() != 6) {
                jobsManager.getHunter().getGiveXP(player, event.getEntity().getType());
            }
        }
    }

    @EventHandler
    public void interact(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getCurrentItem() == null)
            return;
        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.YELLOW + "Jobs de " + ChatColor.GOLD + player.getName())) {
            if (event.getCurrentItem().getType().equals(GUIJobs.HEAD.getType())) {
                JobsManager jobsManager = Jobs.playersJobs.get(player);
                if (jobsManager.getNotification())
                    jobsManager.setNotification(false);
                else
                    jobsManager.setNotification(true);
                GUIJobs.send(player);
            }
            event.setCancelled(true);
        }

        if (event.getClickedInventory().getType().equals(InventoryType.FURNACE) || event.getClickedInventory().getType().equals(InventoryType.BLAST_FURNACE)) {
            if (event.getClick().isKeyboardClick()) {
                event.setCancelled(true);
                return;
            }
            FurnaceInventory inventory = (FurnaceInventory) event.getInventory();
            if (event.getCurrentItem() != null && inventory.getResult() != null)
                if (event.getCurrentItem().isSimilar(inventory.getResult()) && event.getSlot() == 2) {
                    if (event.isShiftClick() && event.isLeftClick() || event.isLeftClick() || event.isRightClick()) {
                        if ((event.getCurrentItem().getAmount() + player.getItemOnCursor().getAmount()) > 64 && player.getInventory().firstEmpty() == -1 ||player.getInventory().firstEmpty() == -1 && event.isShiftClick()) {
                            event.setCancelled(true);
                            return;
                        }
                        JobsManager jobsManager = Jobs.playersJobs.get(player);
                        int a = event.getCurrentItem().getAmount();
                        if (event.isRightClick())
                            a = a/2;
                        jobsManager.getSmithing().getGiveXP(player, event.getCurrentItem(), a);
                    }
                }
        }
        if (event.getClickedInventory().getType().equals(InventoryType.WORKBENCH)) {
            if (!Smith.listItems.contains(event.getCurrentItem()) || !(player.getItemOnCursor().getType().equals(Material.AIR)))
                return;
            if (event.getSlot() == 0) {
                if (event.isShiftClick() && event.isLeftClick() || event.isLeftClick() || event.isRightClick()) {
                    if (player.getItemOnCursor().getType() != Material.AIR && player.getInventory().firstEmpty() == -1 ||player.getInventory().firstEmpty() == -1 && event.isShiftClick()) {
                        event.setCancelled(true);
                        return;
                    }
                    JobsManager jobsManager = Jobs.playersJobs.get(player);
                    int a = event.getCurrentItem().getAmount();
                    if (event.isRightClick())
                        a = a/2;
                    jobsManager.getSmithing().getGiveXP(player, event.getCurrentItem(), a);
                }
            }
        }
    }


    @EventHandler
    public void fish(PlayerFishEvent event) {
        if (event.getState().equals(PlayerFishEvent.State.CAUGHT_ENTITY) || event.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)) {
            JobsManager manager = Jobs.playersJobs.get(event.getPlayer());
            if (manager.getFisherman().getLevel() != 6) {
                manager.getFisherman().getGiveXP(event.getPlayer(), ((Item) event.getCaught()).getItemStack());
            }
        }
    }

    @EventHandler
    public void placeBlock(BlockPlaceEvent event) {
        List<ItemStack> listWitheList = new ArrayList<>();
        listWitheList.addAll(Lumberjack.listItems);
        listWitheList.addAll(Miner.listItems);
        listWitheList.addAll(Peasant.listItems);
        if (listWitheList.contains(new ItemStack(event.getBlockPlaced().getType())))
            Jobs.dbWorker.put(event.getBlockPlaced().getLocation());
    }

    @EventHandler
    public void blockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack item = new ItemStack(event.getBlock().getType());
        JobsManager jobsManager = Jobs.playersJobs.get(player);
        List<ItemStack> listWitheList = new ArrayList<>();
        listWitheList.addAll(Lumberjack.listItems);
        listWitheList.addAll(Miner.listItems);
        listWitheList.addAll(Peasant.listItems);

        if (listWitheList.contains(item)) {
            Jobs.dbWorker.get(locations -> {
                if (locations.contains(event.getBlock().getLocation())) {
                    Jobs.dbWorker.remove(event.getBlock().getLocation());
                } else {
                    // LumberJack
                    if (Lumberjack.listItems.contains(item)) {
                        Lumberjack lumberjack = jobsManager.getLumberjack();
                        playerLumberjack(player);
                        if (countXPLumberjack.containsKey(player))
                            countXPLumberjack.replace(player, countXPLumberjack.get(player) + lumberjack.getGiveXP(player, item, false));
                    }

                    // Peasant
                    if (Peasant.listItems.contains(item)) {
                        Peasant peasant = jobsManager.getPeasant();
                        if (peasant.getLevel() != 6) {
                            playerPeasant(player);
                            if (countXPPeasant.containsKey(player))
                                countXPPeasant.replace(player, countXPPeasant.get(player) + peasant.getGiveXP(player, item, false));
                        }
                    }

                    // Minor
                    if (Miner.listItems.contains(item)) {
                        Miner miner = jobsManager.getMiner();
                        if (miner.getLevel() != 6) {
                            PlayerMining(player);
                            if (countXP.containsKey(player))
                                countXP.replace(player, countXP.get(player) + miner.getGiveXP(player, item, false));
                        }
                    }
                }

            });
        }
    }


    private boolean PlayerMining(Player player) {
        if (miningProgress.contains(player)) {
            miningProgressTime.replace(player, miningProgressTime.get(player), 1);
            return false;
        } else {
            miningProgressTime.put(player, 1);
            countXP.put(player, 1);
            miningProgress.add(player);
        }
        BukkitTask task = new BukkitRunnable() {
            @Override
            public void run() {
                int i = miningProgressTime.get(player);
                if (i >= 5) {
                    ObjectsPreset.sendFakeNotification(player, AdvancementAPIFrameType.TASK, Material.DIAMOND_PICKAXE, ChatColor.GOLD + "§l" + "Mineur" + ChatColor.YELLOW + "\n+" + countXP.get(player) + "xp");
                    countXP.remove(player);
                    miningProgress.remove(player);
                    miningProgressTime.remove(player);
                    cancel();
                }
                i++;
                miningProgressTime.replace(player, miningProgressTime.get(player), i);
            }
        }.runTaskTimer(Jobs.instance, 0, 10);
        return true;
    }

    private boolean playerPeasant(Player player) {
        if (peasantProgress.contains(player)) {
            peasantProgressTime.replace(player, peasantProgressTime.get(player), 1);
            return false;
        } else {
            peasantProgressTime.put(player, 1);
            countXPPeasant.put(player, 1);
            peasantProgress.add(player);
        }
        BukkitTask task = new BukkitRunnable() {
            @Override
            public void run() {
                int i = peasantProgressTime.get(player);
                if (i >= 3) {
                    ObjectsPreset.sendFakeNotification(player, AdvancementAPIFrameType.TASK, Material.DIAMOND_HOE, ChatColor.GOLD + "§l" + "Paysan" + ChatColor.YELLOW + "\n+" + countXPPeasant.get(player) + "xp");
                    countXPPeasant.remove(player);
                    peasantProgress.remove(player);
                    peasantProgressTime.remove(player);
                    cancel();
                }
                i++;
                peasantProgressTime.replace(player, peasantProgressTime.get(player), i);
            }
        }.runTaskTimer(Jobs.instance, 0, 10);
        return true;
    }

    private boolean playerLumberjack(Player player) {
        if (lumberjackProgress.contains(player)) {
            lumberjackProgressTime.replace(player, lumberjackProgressTime.get(player), 1);
            return false;
        } else {
            countXPLumberjack.put(player, 1);
            lumberjackProgress.add(player);
            lumberjackProgressTime.put(player, 1);
        }
        BukkitTask task = new BukkitRunnable() {
            @Override
            public void run() {
                int i = lumberjackProgressTime.get(player);
                if (i >= 3) {
                    ObjectsPreset.sendFakeNotification(player, AdvancementAPIFrameType.TASK, Material.DIAMOND_AXE, ChatColor.GOLD + "§l" + "Bucheron" + ChatColor.YELLOW + "\n+" + countXPLumberjack.get(player) + "xp");
                    countXPLumberjack.remove(player);
                    lumberjackProgress.remove(player);
                    lumberjackProgressTime.remove(player);
                    cancel();
                }
                i++;
                lumberjackProgressTime.replace(player, lumberjackProgressTime.get(player), i);
            }
        }.runTaskTimer(Jobs.instance, 0, 10);
        return true;
    }

    private boolean isBlockBreaByPlayer(HashMap<Chunk, Location> list, Chunk chunk, Location location) {
        if (list.containsKey(chunk)){
            for (Location loc : list.values()) {
                if (loc.getWorld().getName().equalsIgnoreCase(location.getWorld().getName()) &&
                        loc.getBlockX() == location.getBlockX() && loc.getBlockY() == location.getBlockY() && loc.getBlockZ() == location.getBlockZ()) {
                    return true;
                }
            }
        }
        return false;
    }
}
