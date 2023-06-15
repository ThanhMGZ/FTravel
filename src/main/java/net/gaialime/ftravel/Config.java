package net.gaialime.ftravel;

import net.gaialime.ftravel.system.Scroll;
import net.gaialime.ftravel.system.gui.GUIItem;
import net.gaialime.ftravel.system.gui.ItemBuilder;
import net.gaialime.ftravel.system.gui.TravelGUI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class Config {

    public TravelGUI travelGUI;

    public Config() {
        Scroll s1 = new Scroll("scroll1")
                .setWarpTo(new Location(Bukkit.getWorld("world"),100,60,101))
                .setMaterial(new ItemBuilder(Material.ACACIA_DOOR).displayName("&aSIU").lore("a").lore("b").lore("c").enchant(true));
        Scroll s2 = new Scroll("scroll2")
                .setWarpTo(new Location(Bukkit.getWorld("end"),1,2,3,90,0))
                .setMaterial(new ItemBuilder(Material.IRON_AXE));

        this.travelGUI = new TravelGUI("&aFast Travel",null,6)
                .fill(Material.GRAY_STAINED_GLASS_PANE,false,true)
                .paperOrIconChanger(new ItemBuilder(Material.PAPER).displayName("&cPaper Icon").lore("&7 Trạng Thái: &cTẮT").lore("&e ấn vào để bật!"),
                        new ItemBuilder(Material.PAPER).displayName("&aPaper Icon").lore("&7 Trạng Thái: &aBật").lore("&e ấn để tắt!"),53)
                .addItem(new GUIItem(10,s1,new ItemBuilder("{skull_value}").displayName("Warp 1").lore("&eClick Để Warp").enchant(true),
                        new ItemBuilder("{skull_value}").displayName("Warp 1").lore("&c Chưa Có Scroll!")))// có là ib còn không là ib2
                .addItem(new GUIItem(11, s2,new ItemBuilder("{skull_value}").displayName("Warp 2").lore("&eClick Để Warp").enchant(true),
                        new ItemBuilder("{skull_value}").displayName("Warp 2").lore("&c Chưa Có Scroll!")));
    }


}
