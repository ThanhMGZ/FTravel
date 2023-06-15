package net.gaialime.ftravel.system.gui;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemBuilder {

    private ItemStack stack;

    private ItemMeta meta;

    private List<Component> lore = new ArrayList<>();

    public ItemBuilder(Material m) {
        this.stack = new ItemStack(m);
        this.meta = stack.getItemMeta();
    }

    public ItemBuilder(String skullValue) {
        this.stack = skullWithValue(skullValue);
        this.meta = stack.getItemMeta();
    }

    public ItemBuilder displayName(String str) {
        meta.displayName(Component.text(color(str)));
        return this;
    }

    public ItemBuilder lore(String str) {
        lore.add(Component.text(color(str)));
        return this;
    }

    public ItemBuilder enchant(boolean b) {
        if (b) {
            meta.addEnchant(Enchantment.KNOCKBACK,1,false);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        } else {
            for (Enchantment enchantment : meta.getEnchants().keySet()) {
                meta.removeEnchant(enchantment);
            }
        }
        return this;
    }

    public ItemStack build() {
        meta.lore(lore);
        stack.setItemMeta(meta);
        return stack;
    }

    private ItemStack skullWithValue(String value) {
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
        SkullMeta meta = (SkullMeta) itemStack.getItemMeta();

        PlayerProfile profile = Bukkit.createProfile(UUID.randomUUID());
        profile.getProperties().add(new ProfileProperty("textures", value));
        meta.setPlayerProfile(profile);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
    private String color(String s) {
        return ChatColor.translateAlternateColorCodes('&',s);
    }
}
