package de.danzel34.mutesystem;

import co.aikar.commands.PaperCommandManager;
import de.danzel34.mutesystem.commands.MuteCommand;
import de.danzel34.mutesystem.commands.UnmuteCommand;
import de.danzel34.mutesystem.database.MySQLConnector;
import de.danzel34.mutesystem.manager.MuteManager;
import de.danzel34.mutesystem.utils.TimeOverCheck;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class MuteSystem extends JavaPlugin {
    @Getter
    private static MuteSystem instance;
    @Getter
    private MySQLConnector mySQLConnector;
    @Getter
    private MuteManager muteManager;
    private PaperCommandManager commandManager;
    private int check;

    @Override
    public void onLoad() {
        //first thing create instance
        instance = this;
        //create mysql connector
        mySQLConnector = new MySQLConnector(
                getConfig().getString("database.mysql.host"),
                getConfig().getInt("database.mysql.port"),
                getConfig().getString("database.mysql.user"),
                getConfig().getString("database.mysql.database"),
                getConfig().getString("database.mysql.password")
        );
    }

    @Override
    public void onEnable() {
        //register command manager instance
        commandManager = new PaperCommandManager(this);
        //register mute manager
        muteManager = new MuteManager();
        //register commands
        commandManager.registerCommand(new MuteCommand());
        commandManager.registerCommand(new UnmuteCommand());
        //start time over check
        check = new TimeOverCheck(mySQLConnector).runTaskTimerAsynchronously(this, 0, 20 * 60).getTaskId();
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTask(check);
        //close mysql connection
        mySQLConnector.disconnect();
        //last thing delete instance
        instance = null;
    }
}
