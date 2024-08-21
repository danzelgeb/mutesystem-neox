package de.danzel34.mutesystem.utils;

import de.danzel34.mutesystem.database.MySQLConnector;
import org.bukkit.scheduler.BukkitRunnable;

public class TimeOverCheck extends BukkitRunnable {
    private final MySQLConnector mySQLConnector;

    public TimeOverCheck(MySQLConnector mySQLConnector) {
        this.mySQLConnector = mySQLConnector;
    }

    @Override
    public void run() {
        mySQLConnector.update("DELETE FROM mutes WHERE ending < " + System.currentTimeMillis());
    }
}
