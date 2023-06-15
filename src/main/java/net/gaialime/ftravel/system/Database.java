package net.gaialime.ftravel.system;

import net.gaialime.ftravel.FTravel;
import net.gaialime.ftravel.Main;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.UUID;

public class Database {

    private Connection connection;

    private File file;

    public Database() {
        this.file = new File(Main.instance.getDataFolder(),"database.db");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection("JDBC:SQLITE:" + file);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            Statement s = connection.createStatement();
            s.executeUpdate("CREATE TABLE IF NOT EXISTS storage (uid varchar(69) NOT NULL, scrolls varchar(6969) NOT NULL,paperOrIcon INT(2) NOT NULL,PRIMARY KEY (uid));");
            s.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createPlayer(Player player) {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM storage WHERE player = ?")) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    if (rs.getString("uid").equals(player.getUniqueId().toString())) {
                        try (PreparedStatement ps2 = connection.prepareStatement("INSERT OR IGNORE INTO storage (uid,scrolls,paperOrIcon) VALUES (?,?,?)")) {
                            ps2.setString(1,player.getUniqueId().toString());
                            ps2.setString(2,"");
                            ps2.setInt(3,1);
                            ps2.executeUpdate();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void init() {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM storage;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                GaiAlime gaiAlime = new GaiAlime(UUID.fromString(rs.getString("uid")));
                gaiAlime.setPaperOrIcon(rs.getInt("paperOrIcon") != 0);
                if (rs.getString("scrolls").length() == 0)
                    continue;
                gaiAlime.setScrolls(Main.instance.toScroll(rs.getString("scrolls").split("#")));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save() {
        for (GaiAlime player : Main.instance.players().values()) {
            try {
                PreparedStatement ps = connection.prepareStatement("REPLACE INTO storage (uid,scrolls,paperOrIcon) VALUES (?,?,?)");
                ps.setString(1, player.getPlayer().toString());
                if (player.getScrolls().length == 0) {
                    ps.setString(2, "");
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (Scroll scroll : player.getScrolls()) {
                        sb.append(scroll.getId()).append("#");
                    }
                    String scrolls = sb.toString();
                    if (scrolls.endsWith("#")) {
                        scrolls = scrolls.substring(0, scrolls.length() - 1);
                    }
                    ps.setString(2, scrolls);
                }
                ps.setInt(3,player.isPaperOrIcon() ? 1 : 0);
                ps.executeUpdate();
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
