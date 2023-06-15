package net.gaialime.ftravel.system.listeners;

import net.gaialime.ftravel.FTravel;
import net.gaialime.ftravel.Main;
import net.gaialime.ftravel.system.GaiAlime;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InvCloseEvent implements Listener {

    @EventHandler
    public void seg(InventoryCloseEvent event) {
        GaiAlime player = Main.instance.players().get((Player) event.getPlayer());
        if (player.getInventory() != null)
            player.setInventory(null);
    }
}
