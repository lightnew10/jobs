package fr.lightnew.tools;

import fr.lightnew.Jobs;
import fr.lightnew.component.AdvancementAPI;
import fr.lightnew.component.AdvancementAPIFrameType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ObjectsPreset {

    public static String prefix = ChatColor.GRAY + "[" + ChatColor.YELLOW + "Jobs" + ChatColor.GRAY + "] ";
    public static String host, database, username, password;
    public static Integer port;
    public static void Exception(String exception) {
        throw new RuntimeException(exception);
    }

    public static Double xpMaxAlchemist1, xpMaxAlchemist2, xpMaxAlchemist3, xpMaxAlchemist4, xpMaxAlchemist5,
            xpMaxFisherman1, xpMaxFisherman2, xpMaxFisherman3, xpMaxFisherman4, xpMaxFisherman5,
            xpMaxHunter1, xpMaxHunter2, xpMaxHunter3, xpMaxHunter4, xpMaxHunter5,
            xpMaxLumberjack1, xpMaxLumberjack2, xpMaxLumberjack3, xpMaxLumberjack4, xpMaxLumberjack5,
            xpMaxMinor1, xpMaxMinor2, xpMaxMinor3, xpMaxMinor4, xpMaxMinor5,
            xpMaxPeasant1, xpMaxPeasant2, xpMaxPeasant3, xpMaxPeasant4, xpMaxPeasant5,
            xpMaxSmith1, xpMaxSmith2, xpMaxSmith3, xpMaxSmith4, xpMaxSmith5;

    public static void init() {
        host = Jobs.instance.getConfig().getString("Database.host");
        database = Jobs.instance.getConfig().getString("Database.database");
        username = Jobs.instance.getConfig().getString("Database.username");
        password = Jobs.instance.getConfig().getString("Database.password");
        port = Jobs.instance.getConfig().getInt("Database.port");

        xpMaxAlchemist1 = Jobs.instance.getConfig().getDouble("jobs-entity.alchemist.level-1-xp");
        xpMaxAlchemist2 = Jobs.instance.getConfig().getDouble("jobs-entity.alchemist.level-2-xp");
        xpMaxAlchemist3 = Jobs.instance.getConfig().getDouble("jobs-entity.alchemist.level-3-xp");
        xpMaxAlchemist4 = Jobs.instance.getConfig().getDouble("jobs-entity.alchemist.level-4-xp");
        xpMaxAlchemist5 = Jobs.instance.getConfig().getDouble("jobs-entity.alchemist.level-5-xp");

        xpMaxFisherman1 = Jobs.instance.getConfig().getDouble("jobs-entity.fisherman.level-1-xp");
        xpMaxFisherman2 = Jobs.instance.getConfig().getDouble("jobs-entity.fisherman.level-2-xp");
        xpMaxFisherman3 = Jobs.instance.getConfig().getDouble("jobs-entity.fisherman.level-3-xp");
        xpMaxFisherman4 = Jobs.instance.getConfig().getDouble("jobs-entity.fisherman.level-4-xp");
        xpMaxFisherman5 = Jobs.instance.getConfig().getDouble("jobs-entity.fisherman.level-5-xp");

        xpMaxHunter1 = Jobs.instance.getConfig().getDouble("jobs-entity.hunter.level-1-xp");
        xpMaxHunter2 = Jobs.instance.getConfig().getDouble("jobs-entity.hunter.level-2-xp");
        xpMaxHunter3 = Jobs.instance.getConfig().getDouble("jobs-entity.hunter.level-3-xp");
        xpMaxHunter4 = Jobs.instance.getConfig().getDouble("jobs-entity.hunter.level-4-xp");
        xpMaxHunter5 = Jobs.instance.getConfig().getDouble("jobs-entity.hunter.level-5-xp");

        xpMaxLumberjack1 = Jobs.instance.getConfig().getDouble("jobs-entity.lumberjack.level-1-xp");
        xpMaxLumberjack2 = Jobs.instance.getConfig().getDouble("jobs-entity.lumberjack.level-2-xp");
        xpMaxLumberjack3 = Jobs.instance.getConfig().getDouble("jobs-entity.lumberjack.level-3-xp");
        xpMaxLumberjack4 = Jobs.instance.getConfig().getDouble("jobs-entity.lumberjack.level-4-xp");
        xpMaxLumberjack5 = Jobs.instance.getConfig().getDouble("jobs-entity.lumberjack.level-5-xp");

        xpMaxMinor1 = Jobs.instance.getConfig().getDouble("jobs-entity.minor.level-1-xp");
        xpMaxMinor2 = Jobs.instance.getConfig().getDouble("jobs-entity.minor.level-2-xp");
        xpMaxMinor3 = Jobs.instance.getConfig().getDouble("jobs-entity.minor.level-3-xp");
        xpMaxMinor4 = Jobs.instance.getConfig().getDouble("jobs-entity.minor.level-4-xp");
        xpMaxMinor5 = Jobs.instance.getConfig().getDouble("jobs-entity.minor.level-5-xp");

        xpMaxPeasant1 = Jobs.instance.getConfig().getDouble("jobs-entity.peasant.level-1-xp");
        xpMaxPeasant2 = Jobs.instance.getConfig().getDouble("jobs-entity.peasant.level-2-xp");
        xpMaxPeasant3 = Jobs.instance.getConfig().getDouble("jobs-entity.peasant.level-3-xp");
        xpMaxPeasant4 = Jobs.instance.getConfig().getDouble("jobs-entity.peasant.level-4-xp");
        xpMaxPeasant5 = Jobs.instance.getConfig().getDouble("jobs-entity.peasant.level-5-xp");

        xpMaxSmith1 = Jobs.instance.getConfig().getDouble("jobs-entity.smith.level-1-xp");
        xpMaxSmith2 = Jobs.instance.getConfig().getDouble("jobs-entity.smith.level-2-xp");
        xpMaxSmith3 = Jobs.instance.getConfig().getDouble("jobs-entity.smith.level-3-xp");
        xpMaxSmith4 = Jobs.instance.getConfig().getDouble("jobs-entity.smith.level-4-xp");
        xpMaxSmith5 = Jobs.instance.getConfig().getDouble("jobs-entity.smith.level-5-xp");
    }

    public static void sendFakeNotification(Player player, AdvancementAPIFrameType frame, Material material, String title) {
        AdvancementAPI.sendFakeNotification(player, frame, material, title);
    }
}
