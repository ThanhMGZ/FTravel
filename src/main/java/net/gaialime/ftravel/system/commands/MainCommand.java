package net.gaialime.ftravel.system.commands;

import net.gaialime.ftravel.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;
        if (Main.instance.travelGUI().getPermission() != null && !player.hasPermission(Main.instance.travelGUI().getPermission())) {
            player.sendMessage("Non Permission!");
            return true;
        }
        Inventory inventory = Main.instance.travelGUI().build(Main.instance.players().get(player.getUniqueId()));
        player.openInventory(inventory);
        Main.instance.players().get(player.getUniqueId()).setInventory(inventory);

        return true;
    }
}
