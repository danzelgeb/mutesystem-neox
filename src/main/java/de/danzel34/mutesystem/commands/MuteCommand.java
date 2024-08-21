package de.danzel34.mutesystem.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import de.danzel34.mutesystem.MuteSystem;
import de.danzel34.mutesystem.utils.TimeUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("mute")
@CommandPermission("mutesystem.mute")
public class MuteCommand extends BaseCommand {

    @Default
    @CommandCompletion("@players")
    public void onDefault(CommandSender sender, @Optional String target, @Optional String reason) {
        if (target == null || reason == null || target.equals("help")) {
            sender.sendMessage("Usage: /mute <player> <reason>");
            sender.sendMessage("Reasons: \n" +
                    " - Beleidigung (3 Tage)\n" +
                    " - Werbung (1 Tag)\n" +
                    " - Spam (2 Tage)");
        } else {
            long time;
            if (reason.equalsIgnoreCase("Beleidigung")) {
                // mute target for 3 days
                time = TimeUtils.getMillis("3d");
            } else if (reason.equalsIgnoreCase("Werbung")) {
                // mute target for 1 day
                time = TimeUtils.getMillis("1d");
            } else if (reason.equalsIgnoreCase("Spam")) {
                // mute target for 2 days
                time = TimeUtils.getMillis("2d");
            } else {
                sender.sendMessage("§cInvalid reason");
                return;
            }
            OfflinePlayer player = Bukkit.getOfflinePlayer(target);
            MuteSystem.getInstance().getMuteManager().mute(player.getUniqueId(), time);
            sender.sendMessage("§aSuccessfully muted " + target + " for " + reason);
        }
    }

}
