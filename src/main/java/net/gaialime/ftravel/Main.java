package net.gaialime.ftravel;

import net.gaialime.ftravel.system.Database;
import net.gaialime.ftravel.system.GaiAlime;
import net.gaialime.ftravel.system.Scroll;
import net.gaialime.ftravel.system.commands.MainCommand;
import net.gaialime.ftravel.system.gui.TravelGUI;
import net.gaialime.ftravel.system.listeners.InteractEvent;
import net.gaialime.ftravel.system.listeners.InvClickEvent;
import net.gaialime.ftravel.system.listeners.InvCloseEvent;
import net.gaialime.ftravel.system.listeners.JoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Main extends FTravel {

    public static Main instance;

    private Database database;

    private Map<UUID, GaiAlime> players = new HashMap<>();

    private Map<String, Scroll> scrolls = new HashMap<>();

    private Listener[] listeners = {new JoinEvent(),new InvClickEvent(),new InvCloseEvent(), new InteractEvent()};

    private Config config;

    @Override
    public void onEnable() {
        super.onEnable();
        // Plugin startup logic
        saveConfig();
//        if (!new File(getDataFolder(),"config.yml").exists())
//            saveResource("config.yml",true);
        getCommand("warp").setExecutor(new MainCommand());
        instance = this;
        this.database = new Database();
        this.database.init();
        for (Listener listener : listeners)
            getServer().getPluginManager().registerEvents(listener,this);
//        if (getConfig().contains("Scrolls")) {
//            for (String s : getConfig().getConfigurationSection("Scrolls").getKeys(false)) {
//                Scroll scroll = new Scroll(s);
//                scroll.setWarpTo(toLocation(getConfig().getString("Scrolls." + s + ".location")));
//                scroll.setMaterial(getItemStackFromString(getConfig().getString("Scrolls." + s + ".itemstack")));
//            }
//        }
        this.config = new Config();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        // Plugin shutdown logic
        this.database.save();
        this.database.close();
    }

//    public ItemStack getItemStackFromString(String data) {
//        ItemStack item = null;
//        try {
//            ByteArrayInputStream is = new ByteArrayInputStream(Base64Coder.decodeLines(data));
//            try {
//                BukkitObjectInputStream dip = new BukkitObjectInputStream(is);
//                item = (ItemStack) dip.readObject();
//                dip.close();
//            } catch (ClassNotFoundException e) {
//                throw new RuntimeException(e);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return item;
//    }
//
//    public String itemStackToString(ItemStack is) {
//        try {
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            BukkitObjectOutputStream bukkitObjectOutputStream = new BukkitObjectOutputStream(outputStream);
//            bukkitObjectOutputStream.writeInt(1);
//            bukkitObjectOutputStream.writeObject(is);
//            bukkitObjectOutputStream.close();
//            return Base64Coder.encodeLines(outputStream.toByteArray());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public Scroll[] toScroll(String[] id) {
        Scroll[] rs = new Scroll[id.length];
        int i = 0;
        for (String s : id) {
            if (!scrolls.containsKey(s)) continue;
            rs[i] = scrolls.get(s);
            i++;
        }
        return rs;
    }

    @Override
    public TravelGUI travelGUI() {
        return config.travelGUI;
    }

    @Override
    public Database database() {
        return database;
    }

    @Override
    public Map<String, Scroll> scrolls() {
        return scrolls;
    }

    @Override
    public Map<UUID, GaiAlime> players() {
        return players;
    }
}
