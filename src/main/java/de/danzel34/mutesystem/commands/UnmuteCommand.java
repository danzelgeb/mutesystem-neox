package de.danzel34.mutesystem.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import de.danzel34.mutesystem.MuteSystem;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

@CommandAlias("unmute")
@CommandPermission("mutesystem.unmute")
public class UnmuteCommand extends BaseCommand {

    @Default
    @SneakyThrows
    public void onDefault(CommandSender sender, String target) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(target);
        if (MuteSystem.getInstance().getMuteManager().isMuted(offlinePlayer.getUniqueId())) {
            MuteSystem.getInstance().getMuteManager().unmute(offlinePlayer.getUniqueId());
            sender.sendMessage("§aSuccessfully unmuted " + target);
        } else {
            sender.sendMessage("§c" + target + " is not muted");
        }
    }
}
