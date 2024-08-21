package de.danzel34.mutesystem.manager;

import de.danzel34.mutesystem.MuteSystem;
import de.danzel34.mutesystem.database.MySQLConnector;
import lombok.SneakyThrows;

import java.sql.SQLException;
import java.util.UUID;

public class MuteManager {
    private MySQLConnector connector;

    public MuteManager() {
        connector = MuteSystem.getInstance().getMySQLConnector();
    }

    @SneakyThrows
    public boolean isMuted(UUID uniqueId) throws SQLException {
        String query = "SELECT * FROM mutes WHERE uuid = '" + uniqueId.toString() + "'";
        return connector.query(query).next();
    }

    @SneakyThrows
    public void mute(UUID uniqueId, long time) {
        String query = "INSERT INTO mutes (uuid, starting, ending) VALUES ('" + uniqueId.toString() + "', '" + System.currentTimeMillis() + "', '" + (System.currentTimeMillis() + time) + "')";
        connector.update(query);
    }

    @SneakyThrows
    public void unmute(UUID uniqueId) {
        String query = "DELETE FROM mutes WHERE uuid = '" + uniqueId.toString() + "'";
        connector.update(query);
    }
}
