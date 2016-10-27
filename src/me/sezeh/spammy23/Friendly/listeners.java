package me.sezeh.spammy23.Friendly;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;

import static me.sezeh.spammy23.Friendly.Main.pluginthis;


/**
 * Created by sam on 27/10/16.
 */
public class listeners implements Listener {
    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event){
        Player attacked;
        Player attacker;
        Double damage;
        if(event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            /**
            attacked=(Player)event.getEntity();
            if (pluginthis.getConfig().getBoolean(attacked.getName())) {
                damage = event.getDamage();
                attacker = (Player) event.getDamager();
                attacked.setHealth(attacked.getHealth() + damage);//Heals

                attacker.sendMessage(ChatColor.RED + attacked.getName() + ChatColor.WHITE + " Has Friendly Mode Turned OFF");
            }
             **/
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = (Player) event.getPlayer();
        if (!pluginthis.getConfig().contains(player.getName())){
            pluginthis.getConfig().set(player.getName(), false);
            try {
                pluginthis.saveConfig();
            }
            catch (Exception e){
                System.out.println(e);
            }
        }
    }
}

