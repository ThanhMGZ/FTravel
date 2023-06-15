package net.gaialime.ftravel.system.commands;

import net.gaialime.ftravel.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AdminCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;
        if (player.hasPermission("op")) {
            if (args.length == 1) {
                String id = args[0];
                if (Main.instance.scrolls().containsKey(id)) {
                    player.getInventory().addItem(Main.instance.scrolls().get(id).getMaterial());
                } else {
                    player.sendMessage("Scroll Not Found!");
                }
            } else {
                player.sendMessage("/getscroll {name}");
            }
        }
        return true;
    }
}
