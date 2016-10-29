package me.sezeh.spammy23.Friendly;

import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import static me.sezeh.spammy23.Friendly.Main.pluginthis;
import static me.sezeh.spammy23.Friendly.Main.settings;


/**
 * Created by sam on 27/10/16.
 */
public class listeners implements Listener {
    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
        Player attacked;
        Player attacker;
        Double damage;
        boolean cancel=false;
        if (event.getEntity() instanceof Player && (event.getDamager() instanceof Player || event.getDamager() instanceof Arrow)) {
            attacked = (Player) event.getEntity();
            //System.out.println(attacked.getName()+" was hurt");
            if (pluginthis.getConfig().getBoolean(attacked.getName())) {
                //System.out.println("cancel");
                damage = event.getDamage();
                //attacked.setHealth(attacked.getHealth() + damage);//Heals
                if (event.getDamager() instanceof Player) {
                    attacker = (Player) event.getDamager();
                    attacker.sendMessage(ChatColor.RED + attacked.getName() + ChatColor.WHITE + " Has Friendly Mode Turned ON");
                    //cancel=true;

                }
                if (event.getDamager() instanceof Arrow){
                    Projectile shot= (Projectile) event.getDamager();
                    if (shot.getShooter() instanceof Player){
                        Player shooter =(Player) shot.getShooter();
                        shooter.sendMessage(ChatColor.RED + attacked.getName() + ChatColor.WHITE + " Has Friendly Mode Turned ON");
                        //cancel=true;
                    }

                }
                cancel=true;
            }
            if (event.getDamager() instanceof Player) {
                attacker=(Player) event.getDamager();
                if (pluginthis.getConfig().getBoolean(attacker.getName()) && !settings.getBoolean("AllowFriendlyToAttack")) {
                    attacker.sendMessage("You Have Friendly Mode ON: You cannot Attack Others");
                    cancel=true;
                }
            }
            if (event.getDamager() instanceof Arrow && ((Arrow) event.getDamager()).getShooter() instanceof Player){
                Projectile shot=(Projectile) event.getDamager();
                attacker=(Player) shot.getShooter();
                if (pluginthis.getConfig().getBoolean(attacker.getName()) && !settings.getBoolean("AllowFriendlyToAttack")) {
                    attacker.sendMessage("You Have Friendly Mode ON: You cannot Attack Others");
                    cancel=true;
                }
            }
            event.setCancelled(cancel);
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

