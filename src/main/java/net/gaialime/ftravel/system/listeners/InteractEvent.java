package net.gaialime.ftravel.system.listeners;

import net.gaialime.ftravel.Main;
import net.gaialime.ftravel.system.GaiAlime;
import net.gaialime.ftravel.system.Scroll;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

public class InteractEvent implements Listener {

    ItemStack[] scrolls = new ItemStack[Main.instance.scrolls().size()];

    @EventHandler
    public void onEvent(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        GaiAlime player = Main.instance.players().get(p.getUniqueId());
        if (scrolls[0] == null) {
            int i = 0;
            for (String id : Main.instance.scrolls().keySet()) {
                scrolls[i] = Main.instance.scrolls().get(id).getMaterial();
            }
        }
        if (contain(p.getInventory().getItemInMainHand())) {
            Scroll scroll = getScrollById(p.getInventory().getItemInMainHand().getI18NDisplayName());
            if (player.hasScroll(scroll) != null) {
                //success
                if (p.getInventory().getItemInMainHand().getAmount() > 1) {
                    ItemStack is = p.getInventory().getItemInMainHand();
                    is.setAmount(is.getAmount() - 1);
                    p.getInventory().setItemInMainHand(is);
                } else {
                    p.getInventory().remove(p.getInventory().getItemInMainHand());
                }
                Scroll[] sa = new Scroll[player.getScrolls().length + 1];
                for (int i = 0; i < sa.length; i++) {
                    sa[i] = player.getScrolls()[i];
                }
                sa[player.getScrolls().length + 1] = scroll;
            }
        }
    }

    boolean equals(ItemStack is1, ItemStack is2) {
        ItemStack is3 = is1.clone();
        ItemStack is4 = is2.clone();
        is3.setAmount(1);
        is4.setAmount(1);
        return is3.equals(is4);
    }

    Scroll getScrollById(String s) {
        for (Scroll scroll : Main.instance.scrolls().values()) {
            if (Objects.equals(scroll.getMaterial().getI18NDisplayName(), s)) {
                return scroll;
            }
        }
        return null;
    }

    boolean contain(ItemStack itemStack) {
        for (ItemStack is : scrolls) {
            if (equals(is,itemStack))
                return true;
        }
        return false;
    }
}
