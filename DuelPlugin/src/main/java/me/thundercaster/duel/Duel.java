package me.thundercaster.duel;

import me.thundercaster.duel.commands.DuelCommand;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;

public final class Duel extends JavaPlugin {
    public Map<String, String> playerduel = new HashMap<>();
    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("Duel").setExecutor(new DuelCommand(this));

    }
    public boolean inventorycheck(Player p){
        if (p.getInventory().isEmpty()){
            p.sendMessage(ChatColor.AQUA + "Your Inventory is Empty");
            return true;
        }
        p.sendMessage(ChatColor.RED + "You must Empty Your Inventory");
        return false;
    }
    public boolean healthcheck(Player p){
        if (p.getHealth() == p.getMaxHealth()){
            p.sendMessage(ChatColor.AQUA + "You are at full health");
            return true;
        }
        return false;
    }
    public boolean saturationcheck(Player p){
        if (p.getSaturation() == 14){
            p.sendMessage(ChatColor.AQUA + "Your Saturation is full");
            return true;
        }
        p.sendMessage(ChatColor.RED + "Your Should Saturation Be Full");
        return false;
    }
    public boolean overallcheck(Player p){
        if (saturationcheck(p) & healthcheck(p) & inventorycheck(p)){
            return true;
        }
        return false;
    }
    public void ironarmor(Player p){
        p.getInventory().addItem(new ItemStack(Material.PORKCHOP,32));
        p.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
        p.getInventory().addItem(new ItemStack(new ItemStack(Material.IRON_AXE)));
        p.getInventory().addItem(new ItemStack(Material.BOW));
        p.getInventory().addItem(new ItemStack(Material.ARROW,64));
        p.getInventory().setArmorContents(null);
        ItemStack[] armorContents = new ItemStack[]{
                new ItemStack(Material.IRON_HELMET, 1),
                new ItemStack(Material.IRON_CHESTPLATE, 1),
                new ItemStack(Material.IRON_LEGGINGS, 1),
                new ItemStack(Material.IRON_BOOTS, 1)};
        p.getInventory().setArmorContents(armorContents);

    }
    public void diamondarmor(Player p){
        p.getInventory().addItem(new ItemStack(Material.GOLDEN_CARROT,32));
        p.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD));
        p.getInventory().addItem(new ItemStack(new ItemStack(Material.DIAMOND_AXE)));
        p.getInventory().addItem(new ItemStack(Material.BOW));
        p.getInventory().addItem(new ItemStack(Material.ARROW,64));
        ItemStack strengh = new ItemStack(Material.POTION);
        ItemStack instanthealth = new ItemStack(Material.POTION,2);
        ItemStack resistance = new ItemStack(Material.POTION);

        PotionMeta strengh_meta = (PotionMeta) strengh.getItemMeta();
        strengh_meta.setMainEffect(PotionEffectType.INCREASE_DAMAGE);
        strengh.setItemMeta(strengh_meta);

        PotionMeta instanthealth_meta = (PotionMeta) instanthealth.getItemMeta();
        instanthealth_meta.setMainEffect(PotionEffectType.HEAL);
        instanthealth.setItemMeta(instanthealth_meta);

        PotionMeta resistance_meta = (PotionMeta) resistance.getItemMeta();
        resistance_meta.setMainEffect(PotionEffectType.DAMAGE_RESISTANCE);
        resistance.setItemMeta(resistance_meta);

        p.getInventory().addItem(strengh);
        p.getInventory().addItem(instanthealth);
        p.getInventory().addItem(resistance);

        p.getInventory().setArmorContents(null);
        ItemStack[] armorContents = new ItemStack[]{
                new ItemStack(Material.DIAMOND_HELMET, 1),
                new ItemStack(Material.DIAMOND_CHESTPLATE, 1),
                new ItemStack(Material.DIAMOND_LEGGINGS, 1),
                new ItemStack(Material.DIAMOND_BOOTS, 1)};
        p.getInventory().setArmorContents(armorContents);

    }

}
