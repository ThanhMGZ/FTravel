package net.gaialime.ftravel.system.listeners;

import net.gaialime.ftravel.FTravel;
import net.gaialime.ftravel.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Main.instance.database().createPlayer(event.getPlayer());
    }
}
