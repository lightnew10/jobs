package fr.lightnew;

import fr.lightnew.commands.CommandJob;
import fr.lightnew.event.JobsEvents;
import fr.lightnew.event.PlayerManager;
import fr.lightnew.jobs.JobsManager;
import fr.lightnew.sql.DBWorker;
import fr.lightnew.sql.RequestsSQL;
import fr.lightnew.tools.ObjectsPreset;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.WeakHashMap;

public class Jobs extends JavaPlugin implements Listener {

    public static Jobs instance;
    public static WeakHashMap<Player, JobsManager> playersJobs = new WeakHashMap<>();
    private Connection connection;
    public static DBWorker dbWorker;


    @Override
    public void onEnable() {
        // Load basic
        instance = this;
        dbWorker = new DBWorker();
        dbWorker.runTaskTimerAsynchronously(this, 0, 10);
        ObjectsPreset.init();
        saveDefaultConfig();
        log(ObjectsPreset.prefix + ChatColor.GREEN + "Plugin is Enable");
        // Database
        final String url = "jdbc:mysql://" + ObjectsPreset.host + ":" + ObjectsPreset.port + "/" + /*ObjectsPreset.database +*/ "?user=" + ObjectsPreset.username + "&password=" + ObjectsPreset.password; // Enter URL with db name
        try {
            connection = DriverManager.getConnection(url);
            log(ChatColor.GREEN + "DATABASE Jobs CONNECTED");
            RequestsSQL.createDatabase();
            RequestsSQL.createDefaultsTables();
            log(ObjectsPreset.prefix + ChatColor.GREEN + "Data base jobs et tableau jobs ont bien été chargé.");
        } catch (SQLException e) {e.printStackTrace();}
        // Listener
        PluginManager event = Bukkit.getPluginManager();
        event.registerEvents(new PlayerManager(), this);
        event.registerEvents(new JobsEvents(), this);
        // Command
        getCommand("job").setExecutor(new CommandJob());
    }

    @Override
    public void onDisable() {
        log(ObjectsPreset.prefix + ChatColor.RED + "Plugin is Disable");
    }

    public static void log(String s) {
        Bukkit.getConsoleSender().sendMessage(s);
    }

    public Connection getConnection() {
        return connection;
    }
}