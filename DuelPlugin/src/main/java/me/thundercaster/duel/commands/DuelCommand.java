package me.thundercaster.duel.commands;

import me.thundercaster.duel.Duel;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;


public class DuelCommand implements CommandExecutor, Listener {

    Duel plugin;

    public DuelCommand(Duel plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender == null){
            return false;
        }
        if (sender instanceof Player p){

            if (args.length == 2) {

                Player p2 = plugin.getServer().getPlayer(args[0]);
                if (p2 == null){
                    return false;
                }
                p.sendMessage(ChatColor.GREEN + "you have dueled" + args[0]);
                p2.sendMessage(ChatColor.RED + "you have been dueled by" + p.getName());

                if (plugin.overallcheck(p) &  plugin.overallcheck(p2)){
                    plugin.playerduel.put(p.getName(), p2.getName());
                    if (args[1].equalsIgnoreCase("iron")){
                        plugin.ironarmor(p);
                        plugin.ironarmor(p2);
                    }
                    if (args[1].equalsIgnoreCase("diamond")){
                        plugin.diamondarmor(p);
                        plugin.diamondarmor(p2);

                    }
                }

            }
        }


        return true;
    }
    @EventHandler
    public void killevent(EntityDamageByEntityEvent e){
        if (e.getDamager() instanceof Player p){
            if (e.getEntity() instanceof Player p2){
                if (plugin.playerduel.containsKey(p) & plugin.playerduel.containsValue(p2)){
                    if (p.getHealth() < e.getFinalDamage()){
                        p.sendMessage(ChatColor.RED + "You Lost the duel against" + p2.getName() + "by: " + p2.getHealth());
                        p2.sendMessage(ChatColor.GREEN + "You won the duel against" + p.getName() + "with:" + p2.getHealth());
                        e.setCancelled(true);
                        p.setHealth(20);
                        p.setSaturation(14);
                        p.getInventory().clear();
                        p2.setHealth(20);
                        p2.setSaturation(14);
                        p2.getInventory().clear();

                    }
                    if (p2.getHealth() < e.getFinalDamage()){
                        p2.sendMessage(ChatColor.RED + "You Lost the duel against" + p.getName() + "by: " + p.getHealth());
                        p.sendMessage(ChatColor.GREEN + "You won the duel against" + p2.getName() + "with:" + p.getHealth());
                        e.setCancelled(true);
                        p.setHealth(20);
                        p.setSaturation(14);
                        p.getInventory().clear();
                        p2.setHealth(20);
                        p2.setSaturation(14);
                        p2.getInventory().clear();
                    }

                }


            }
        }

    }

}
