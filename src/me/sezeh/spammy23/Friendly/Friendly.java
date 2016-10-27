package me.sezeh.spammy23.Friendly;

import javafx.print.PageLayout;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import static me.sezeh.spammy23.Friendly.Main.pluginthis;

/**
 * Created by sam on 27/10/16.
 */
public class Friendly implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        boolean returnval=false;
        if (alias.equalsIgnoreCase("friendly")) {
            try{
            if (sender instanceof Player) {
                Player player = (Player) sender;
                switch (args[0]) {
                    case "toggle":
                        boolean value=!pluginthis.getConfig().getBoolean(player.getName());
                        pluginthis.getConfig().set(player.getName(), value);
                        pluginthis.saveConfig();
                        player.sendMessage("Friendly Mode: " +value);
                        break;
                    case "on":
                        if (player.hasPermission("friendly.on.other")) {
                            pluginthis.getConfig().set(args[1], true);
                            pluginthis.saveConfig();
                            player.sendMessage("Turned Friendly Mode ON for player: "+args[1]);
                        } else {
                            player.sendMessage("You do not have Permission friendly.on.other");
                        }
                        break;
                    case "off":
                        if (player.hasPermission("friendly.off.other")) {
                            pluginthis.getConfig().set(args[1], false);
                            pluginthis.saveConfig();
                            player.sendMessage("Turned Friendly Mode OFF for player: " +args[1]);
                        } else {
                            player.sendMessage("You do not have Permission friendly.off.other");
                        }
                        break;
                        case "reload":
                            if (player.hasPermission("friendly.reload")){
                                pluginthis.reloadConfig();
                                player.sendMessage("Reloaded Config");
                            }
                            else{
                                player.sendMessage("You do not have permission friendly.reload");
                            }
                            break;

                }
                returnval=true;
            } else {
                switch (args[0]) {
                    case "on":
                        if (sender.hasPermission("friendly.on.other")) {
                            pluginthis.getConfig().set(args[1], true);
                            pluginthis.saveConfig();
                            sender.sendMessage("Turned Friendly Mode ON for player: " +args[1]);
                        }
                        else {
                            sender.sendMessage("You do not have Permission friendly.on.other");
                        }
                        break;
                    case "off":
                        if (sender.hasPermission("friendly.off.other")) {
                            pluginthis.getConfig().set(args[1], false);
                            pluginthis.saveConfig();
                            sender.sendMessage("Turned Friendly Mode OFF for player: " +args[1]);
                        } else {
                            sender.sendMessage("You do not have Permission friendly.off.other");
                        }
                        break;


                    case "toggle":
                        sender.sendMessage("You MUST be a player to run this command");
                        break;


                    case "reload":
                        if (sender.hasPermission("friendly.reload")){
                            pluginthis.reloadConfig();
                            sender.sendMessage("Reloaded Config");
                        }
                        else{
                            sender.sendMessage("You do not have permission friendly.reload");
                        }
                        break;

                }
                returnval=true;
            }
        }
        catch(Exception e){
            returnval=false;
        }
        }
        else{
            returnval= false;
        }
        return returnval;
    }
}
