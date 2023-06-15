package net.gaialime.ftravel.system.gui;

import net.gaialime.ftravel.system.GaiAlime;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TravelGUI {


    private String title,permission;

    private int col;

    private Map<Integer,GUIItem> guiItems = new HashMap<>();

    private ItemStack[] contents;

    private ItemStack i1,i2;

    private int pois;

    public TravelGUI(String title, String permission, int col) {
        this.title = title;
        this.permission = permission;
        this.col = col;
        this.contents = new ItemStack[col * 9];
    }

    public TravelGUI addItem(GUIItem... guiItem) {
        for (int i = 0; i < guiItem.length; i++) {
            guiItems.put(guiItem[i].getSlot(),guiItem[i]);
            contents[guiItem[i].getSlot()] = guiItem[i].getIb().build();
        }
        return this;
    }

    public TravelGUI fill(Material material,boolean ec,boolean replaceAll) {
        ItemStack stack = new ItemStack(material);
        stack.getItemMeta().displayName(Component.text("§4"));
        if (ec) {
            stack.getItemMeta().addEnchant(Enchantment.KNOCKBACK,1,false);
            stack.getItemMeta().addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        for (int i = 0; i < contents.length; i++) {
            if (replaceAll) {
                contents[i] = stack;
                continue;
            }
            if (contents[i] == null)
                continue;
            contents[i] = stack;
        }
        return this;
    }

    public TravelGUI paperOrIconChanger(ItemBuilder off,ItemBuilder on,int slot) {
        if (on == null || off == null )
            throw new NullPointerException("ItemBuilder Không Tồn Tại!");
        if (slot < 0 || slot > (col * 9 - 1))
            throw new RuntimeException("'" + slot + "' Không Nằm Trong Phạm Vi Của Inventory!");
        contents[slot] = on.build();
        this.pois = slot;
        this.i1 = off.build();
        this.i2 = on.build();
        return this;
    }

    public String getPermission() {
        return permission;
    }

    public int getPois() {
        return pois;
    }

    public Map<Integer, GUIItem> getGuiItems() {
        return guiItems;
    }

    private Inventory inventory = null;

    public Inventory build(GaiAlime player) {
        if (inventory != null)
            return inventory;
        Inventory inventory = Bukkit.createInventory(null,col * 9, ChatColor.translateAlternateColorCodes('&',title));
        inventory.setContents(contents);
        for (GUIItem guiItem : getGuiItems().values()) {
            if (player.isPaperOrIcon()) {
                if (player.hasScroll(guiItem.getScroll()) != null) {
                    inventory.setItem(guiItem.getSlot(), guiItem.getIb().build());
                } else {
                    inventory.setItem(guiItem.getSlot(), guiItem.getIb2().build());
                }
            } else {
                ItemStack is;
                if (player.hasScroll(guiItem.getScroll()) != null) {
                    is = guiItem.getIb().build();
                } else {
                    is = guiItem.getIb2().build();
                }
                is.setType(Material.PAPER);
                inventory.setItem(guiItem.getSlot(),is);
            }
        }
        this.inventory = inventory;
        if (!player.isPaperOrIcon()) {
            inventory.setItem(pois,i1);
        } else {
            inventory.setItem(pois,i2);
        }
        return inventory;
    }
}
