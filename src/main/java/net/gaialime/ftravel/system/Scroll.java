package net.gaialime.ftravel.system;

import net.gaialime.ftravel.FTravel;
import net.gaialime.ftravel.Main;
import net.gaialime.ftravel.system.gui.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public class Scroll {

    private String id;

    private ItemStack material;

    private Location warpTo;

    public Scroll(String id) {
        this.id = id;
        Main.instance.scrolls().put(id,this);
    }

    public Location getWarpTo() {
        return warpTo;
    }

    public Scroll setWarpTo(Location warpTo) {
        this.warpTo = warpTo;
        return this;
    }

    public ItemStack getMaterial() {
        return material;
    }

    public Scroll setMaterial(ItemBuilder material) {
        this.material = material.build();
        return this;
    }

    public String getId() {
        return id;
    }
}
