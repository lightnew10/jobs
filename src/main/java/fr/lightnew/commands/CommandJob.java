package fr.lightnew.commands;

import fr.lightnew.Jobs;
import fr.lightnew.jobs.JobsManager;
import fr.lightnew.tools.GUIJobs;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandJob implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            JobsManager manager = Jobs.playersJobs.get(player);
            if (args.length == 0) {
                GUIJobs.send(player);
            }
            if (player.isOp()) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("reset")) {
                        player.sendMessage(ChatColor.YELLOW + "Vous venez de vous réinitialiser vos métiers.");
                        manager.reset();
                    }
                    if (args[0].equalsIgnoreCase("get")) {
                        player.sendMessage(manager.toStringExplain());
                    }
                    if (args[0].equalsIgnoreCase("max")) {
                        player.sendMessage(ChatColor.YELLOW + "Vous avez max vos métiers");
                        Jobs.playersJobs.get(player).max();
                    }
                }
                if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("get")) {
                        if (Bukkit.getPlayer(args[1]) == null) {
                            player.sendMessage(ChatColor.RED + "Joueur pas en ligne.");
                            return false;
                        }
                        Player player1 = Bukkit.getPlayer(args[1]);
                        player.sendMessage(Jobs.playersJobs.get(player1).toStringExplain());
                    }
                    if (args[0].equalsIgnoreCase("max")) {
                        if (Bukkit.getPlayer(args[1]) == null) {
                            player.sendMessage(ChatColor.RED + "Joueur pas en ligne.");
                            return false;
                        }
                        Player player1 = Bukkit.getPlayer(args[1]);
                        player.sendMessage(ChatColor.YELLOW + "Vous avez max les métiers de " + ChatColor.GOLD + player1.getName());
                        player1.sendMessage(ChatColor.RED + "Un staff vous a max vos métiers.");
                        Jobs.playersJobs.get(player1).max();
                    }
                    if (args[0].equalsIgnoreCase("reset")) {
                        if (Bukkit.getPlayer(args[1]) == null) {
                            player.sendMessage(ChatColor.RED + "Joueur pas en ligne.");
                            return false;
                        }
                        Player player1 = Bukkit.getPlayer(args[1]);
                        player1.sendMessage(ChatColor.RED + "Un staff vous a reset vos métiers");
                        player.sendMessage(ChatColor.YELLOW + "Vous avez reset les métiers de " + ChatColor.GOLD + player1.getName());
                        Jobs.playersJobs.get(player1).reset();
                    }
                }
            } else GUIJobs.send(player);
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1)
            return Arrays.asList("reset", "get", "max");

        if (args.length == 2) {
            List<String> players = new ArrayList<>();
            for (Player player : Bukkit.getOnlinePlayers())
                players.add(player.getName());
            return players;
        }
        return null;
    }
}
