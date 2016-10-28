package me.sezeh.spammy23.Friendly;

import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;

import static me.sezeh.spammy23.Friendly.Main.pluginthis;


/**
 * Created by sam on 27/10/16.
 */
public class listeners implements Listener {
    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
        Player attacked;
        Player attacker;
        Double damage;
        if (event.getEntity() instanceof Player && (event.getDamager() instanceof Player || event.getDamager() instanceof Arrow)) {

            attacked = (Player) event.getEntity();
            //System.out.println(attacked.getName()+" was hurt");
            if (pluginthis.getConfig().getBoolean(attacked.getName())) {
                //System.out.println("cancel");
                damage = event.getDamage();
                //attacked.setHealth(attacked.getHealth() + damage);//Heals
                if (event.getDamager() instanceof Player) {
                    attacker = (Player) event.getDamager();
                    attacker.sendMessage(ChatColor.RED + attacked.getName() + ChatColor.WHITE + " Has Friendly Mode Turned OFF");
                }
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = (Player) event.getPlayer();
     //   System.out.println(player.getName()+" joined");
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
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        if (event.getEntity() instanceof Player) {
            Player killed=(Player) event.getEntity();
      //      System.out.println(killed.getName()+" Died");
            //System.out.println(pluginthis.getConfig().getBoolean(killed.getName()));
            if (pluginthis.getConfig().getBoolean(killed.getName())){
                event.setKeepInventory(true);

                }
            else {
                event.setKeepInventory(false);

            }

            }
        }
    }

