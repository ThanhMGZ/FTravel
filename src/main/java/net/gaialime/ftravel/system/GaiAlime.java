package net.gaialime.ftravel.system;

import net.gaialime.ftravel.FTravel;
import net.gaialime.ftravel.Main;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class GaiAlime {

    private UUID player;

    private Scroll[] scrolls;

    private Inventory inventory;

    private boolean paperOrIcon = true;

    public GaiAlime(UUID player) {
        this.player = player;
        Main.instance.players().put(player,this);
    }

    public Scroll hasScroll(Scroll scroll) {
        for (Scroll s : scrolls)
            if (s.equals(scroll))
                return s;
        return null;
    }

    public boolean isPaperOrIcon() {
        return paperOrIcon;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setPaperOrIcon(boolean paperOrIcon) {
        this.paperOrIcon = paperOrIcon;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public UUID getPlayer() {
        return player;
    }

    public Scroll[] getScrolls() {
        return scrolls;
    }

    public void setScrolls(Scroll[] scrolls) {
        this.scrolls = scrolls;
    }
}
