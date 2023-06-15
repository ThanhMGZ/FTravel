package net.gaialime.ftravel;

import net.gaialime.ftravel.system.Database;
import net.gaialime.ftravel.system.GaiAlime;
import net.gaialime.ftravel.system.Scroll;
import net.gaialime.ftravel.system.gui.TravelGUI;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Map;
import java.util.UUID;

public abstract class FTravel extends JavaPlugin {

    public abstract TravelGUI travelGUI();

    public abstract Database database();

    public abstract Map<String,Scroll> scrolls();

    public abstract Map<UUID,GaiAlime> players();

}
