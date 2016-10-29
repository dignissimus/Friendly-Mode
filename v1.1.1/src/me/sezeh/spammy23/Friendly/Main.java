package me.sezeh.spammy23.Friendly;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import me.sezeh.spammy23.Friendly.Tools;

public class Main extends JavaPlugin{
    public static Main pluginthis;
    public static File plugdir;
    public static File settingsfile;
    public static YamlConfiguration settings;
    @Override
    public void onEnable(){


        pluginthis=this;
        plugdir=getDataFolder();
        settingsfile= new File(this.getDataFolder(), "settings.yml");

        try {
            InputStream defaultsettingsfile = this.getResource("settings.yml");
            if (!settingsfile.exists()) {
                settingsfile.getParentFile().mkdirs();
                Tools tool=new Tools();
                tool.copy(defaultsettingsfile, settingsfile);
            }
        }
        catch (Exception e){
            System.out.println(e);

        }
        settings = YamlConfiguration.loadConfiguration(settingsfile);
        getServer().getPluginManager().registerEvents(new listeners(), this);
        this.getCommand("friendly").setExecutor(new Friendly());
    }
    @Override
    public void onDisable(){
        System.out.println("Stopping Friendly Mode Plugin");

    }

}
