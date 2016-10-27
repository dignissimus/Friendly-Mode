package me.sezeh.spammy23.Friendly;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin{
    public static Main pluginthis;
    public static File plugdir;
    @Override
    public void onEnable(){
        pluginthis=this;
        plugdir=getDataFolder();
        getServer().getPluginManager().registerEvents(new listeners(), this);
        this.getCommand("friendly").setExecutor(new Friendly());
    }
    @Override
    public void onDisable(){
        System.out.println("Stopping Friendly Mode Plugin");

    }

}
