package de.danzel34.mutesystem.listener;

import de.danzel34.mutesystem.MuteSystem;
import io.papermc.paper.event.player.AsyncChatEvent;
import lombok.SneakyThrows;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;

public class PlayerChatListener implements Listener {

    @EventHandler
    @SneakyThrows
    public void onChat(AsyncChatEvent event) {
        UUID uniqueId = event.getPlayer().getUniqueId(); //Todo use UUIDFetcher
        if (MuteSystem.getInstance().getMuteManager().isMuted(uniqueId)) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("§cYou are muted");
        }
    }
}
