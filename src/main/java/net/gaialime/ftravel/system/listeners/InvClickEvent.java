package net.gaialime.ftravel.system.listeners;

import net.gaialime.ftravel.FTravel;
import net.gaialime.ftravel.Main;
import net.gaialime.ftravel.system.GaiAlime;
import net.gaialime.ftravel.system.gui.GUIItem;
import net.gaialime.ftravel.system.gui.TravelGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class InvClickEvent implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();
        int slot = event.getSlot();
        GaiAlime player = Main.instance.players().get(p.getUniqueId());
        try {
            if (player.getInventory() != null) {
                TravelGUI inventory = Main.instance.travelGUI();
                if (player.getInventory().equals(inventory.build(player))) {
                    event.setCancelled(true);
                    if (slot == inventory.getPois()) {
                        player.setPaperOrIcon(!player.isPaperOrIcon());
                        p.closeInventory();
                        p.openInventory(inventory.build(player));
                    } else {
                        if (inventory.getGuiItems().containsKey(slot)) {
                            GUIItem guiItem = inventory.getGuiItems().get(slot);
                            if (player.hasScroll(guiItem.getScroll()) != null) {
                                p.teleport(guiItem.getScroll().getWarpTo());
                                for (String str : guiItem.getActions().keySet()) {
                                    Bukkit.dispatchCommand(guiItem.getActions().get(str),str);
                                }
                                p.sendMessage(ChatColor.GREEN + "Teleported Success");
                                p.closeInventory();
                            } else {
                                p.sendMessage("Bạn Chưa Có Scroll! Vui Lòng Mua Tại Địa Chỉ: https://gearmc.vn/napthe");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
