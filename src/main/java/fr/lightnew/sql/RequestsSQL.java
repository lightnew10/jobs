package fr.lightnew.sql;

import fr.lightnew.Jobs;
import fr.lightnew.jobs.*;
import fr.lightnew.tools.ObjectsPreset;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequestsSQL {
    private static final Connection connection = Jobs.instance.getConnection();


    public static void createDatabase() {
        try {
            String data =
                    "CREATE DATABASE IF NOT EXISTS jobs";
            PreparedStatement statement1 = connection.prepareStatement(data);
            statement1.executeUpdate();
            Jobs.log(ObjectsPreset.prefix + ChatColor.GREEN + "Database 'Jobs' is created");
        } catch (SQLException e) {
            ObjectsPreset.Exception(ChatColor.RED + "Default table -> " + ChatColor.RESET + "furnace and spawner" + ChatColor.RED + ", has not created\n\n" + e);
        }
    }
    public static void createDefaultsTables() {
        try {
            String use = "USE jobs;";
            String player_jobs =
                    "CREATE TABLE IF NOT EXISTS playerJobs(id int NOT NULL AUTO_INCREMENT," +
                            "player_uuid VARCHAR(255) NOT NULL," +
                            "notification BOOLEAN NOT NULL," +
                            "alchemist_xp DOUBLE NOT NULL," +
                            "fisherman_xp DOUBLE NOT NULL," +
                            "hunter_xp DOUBLE NOT NULL," +
                            "lumberjack_xp DOUBLE NOT NULL," +
                            "minor_xp DOUBLE NOT NULL," +
                            "peasant_xp DOUBLE NOT NULL," +
                            "smithing_xp DOUBLE NOT NULL," +
                            "PRIMARY KEY (id));";
            String BreakBlock =
                    "CREATE TABLE IF NOT EXISTS breakblock(id int NOT NULL AUTO_INCREMENT," +
                            "location_world VARCHAR(255) NOT NULL," +
                            "location_x INT NOT NULL," +
                            "location_y INT NOT NULL," +
                            "location_z INT NOT NULL," +
                            "PRIMARY KEY (id));";
            PreparedStatement statement = connection.prepareStatement(use);
            PreparedStatement statement2 = connection.prepareStatement(player_jobs);
            PreparedStatement statement3 = connection.prepareStatement(BreakBlock);
            statement.executeUpdate();
            statement2.executeUpdate();
            statement3.executeUpdate();
        } catch (SQLException e) {
            ObjectsPreset.Exception(ChatColor.RED + "Default table -> " + ChatColor.RESET + "jobs & player jobs" + ChatColor.RED + ", has not created\n\n" + e);
            e.printStackTrace();
        }
    }

    public static Boolean verifyNewPlayer(Player player) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM playerJobs WHERE player_uuid=?");
            statement.setString(1, player.getUniqueId().toString());
            ResultSet result = statement.executeQuery();
            if (result.next())
                return false;
        } catch (SQLException e) {e.printStackTrace();}
        return true;
    }

    public static JobsManager getPlayerJob(Player player) {
        JobsManager manager = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM playerjobs WHERE player_uuid=?");
            statement.setString(1, player.getUniqueId().toString());
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                manager = new JobsManager(player,
                        result.getBoolean("notification"),
                        new Alchemist(result.getInt("alchemist_xp")),
                        new Fisherman(result.getInt("fisherman_xp")),
                        new Hunter(result.getInt("hunter_xp")),
                        new Lumberjack(result.getInt("lumberjack_xp")),
                        new Miner(result.getInt("minor_xp")),
                        new Peasant(result.getInt("peasant_xp")),
                        new Smith(result.getInt("smithing_xp"))
                );
                Jobs.playersJobs.put(player, manager);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return manager;
    }

    public static void createPlayerData(Player player) {
        try {
            if (verifyNewPlayer(player)) {
            PreparedStatement insert = connection.prepareStatement("INSERT INTO playerjobs(" +
                        "player_uuid, notification, alchemist_xp, fisherman_xp, hunter_xp, lumberjack_xp, minor_xp, " +
                        "peasant_xp, smithing_xp)" +
                        "values (?,?,?,?,?,?,?,?,?)");

                insert.setString(1, player.getUniqueId().toString());
                insert.setBoolean(2, true);
                insert.setInt(3, 0);
                insert.setInt(4, 0);
                insert.setInt(5, 0);
                insert.setInt(6, 0);
                insert.setInt(7, 0);
                insert.setInt(8, 0);
                insert.setInt(9, 0);

                insert.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updatePlayerData(Player player) {
        try {
            PreparedStatement insert = connection.prepareStatement("UPDATE playerjobs SET " +
                    "player_uuid=?, notification=?, alchemist_xp=?, fisherman_xp=?, hunter_xp=?, lumberjack_xp=?, minor_xp=?, peasant_xp=?, smithing_xp=? WHERE player_uuid=?");
            JobsManager manager = Jobs.playersJobs.get(player);
            insert.setString(1, player.getUniqueId().toString());
            insert.setBoolean(2, manager.getNotification());
            insert.setInt(3, manager.getAlchemist().getXP());
            insert.setInt(4, manager.getFisherman().getXP());
            insert.setInt(5, manager.getHunter().getXP());
            insert.setInt(6, manager.getLumberjack().getXP());
            insert.setInt(7, manager.getMinor().getXP());
            insert.setInt(8, manager.getPeasant().getXP());
            insert.setInt(9, manager.getSmithing().getXP());

            insert.setString(10, player.getUniqueId().toString());
            insert.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Location> getLocationBlockPosed() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM breakblock");
            ResultSet result = statement.executeQuery();
            List<Location> locs = new ArrayList<>();
            while (result.next())
                locs.add(new Location(Bukkit.getWorld(result.getString("location_world")), result.getDouble("location_x"), result.getDouble("location_y"), result.getDouble("location_z"), 0, 0));
            return locs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setLocationBlockPosed(Location location) {
        try {
            PreparedStatement insert = connection.prepareStatement("INSERT INTO breakblock(location_world, location_x, location_y, location_z)" + "values (?,?,?,?)");

            insert.setString(1, location.getWorld().getName());
            insert.setInt(2, location.getBlockX());
            insert.setInt(3, location.getBlockY());
            insert.setInt(4, location.getBlockZ());

            insert.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeLocationBlockPosed(Location location) {
        try {
            String query = "DELETE FROM breakblock WHERE location_world = ? AND location_x = ? AND location_y = ? AND location_z = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, location.getWorld().getName());
            statement.setInt(2, location.getBlockX());
            statement.setInt(3, location.getBlockY());
            statement.setInt(4, location.getBlockZ());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
