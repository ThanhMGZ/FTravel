package net.gaialime.ftravel.system.gui;

import net.gaialime.ftravel.system.Scroll;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GUIItem {

    private int slot;

    private Scroll scroll;

    private ItemBuilder ib,ib2;
    private List<String> lore = new ArrayList<>();

    private Map<String,CommandSender> actions = new HashMap<>();

    public GUIItem(int slot, Scroll scroll, ItemBuilder ib,ItemBuilder ib2) {
        this.slot = slot;
        this.scroll = scroll;
        this.ib = ib;
        this.ib2 = ib2;
    }

    public ItemBuilder getIb2() {
        return ib2;
    }

    public ItemBuilder getIb() {
        return ib;
    }

    public int getSlot() {
        return slot;
    }

    public List<String> getLore() {
        return lore;
    }

    public Map<String, CommandSender> getActions() {
        return actions;
    }

    public Scroll getScroll() {
        return scroll;
    }

    public GUIItem addLore(String lore) {
        this.lore.add(ChatColor.translateAlternateColorCodes('&',lore));
        return this;
    }

    public GUIItem addOnClickAction(String command, CommandSender commandSender) {
        this.actions.put(ChatColor.translateAlternateColorCodes('&',command),commandSender);
        return this;
    }
}
