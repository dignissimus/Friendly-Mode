package me.sezeh.spammy23.Friendly;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;

import static me.sezeh.spammy23.Friendly.Main.plugdir;
import static me.sezeh.spammy23.Friendly.Main.pluginthis;

/**
 * Created by sam on 27/10/16.
 */
public class Tools {

    public void WriteFile(String path, String text) {
        //File folder = plugdir;
        File file = new File(plugdir, path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        try {
            PrintWriter write = new PrintWriter(new FileWriter(file, false));
            write.println(text);
            write.flush();
            write.close();
        } catch (Exception e) {
            System.out.println(e);

        }

    }
    public void AppendFile(String path, String text) {
        //File folder = plugdir;
        File file = new File(plugdir, path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        try {
            PrintWriter write = new PrintWriter(new FileWriter(file, false));
            write.println(text);
            write.flush();
            write.close();
        } catch (Exception e) {
            System.out.println(e);

        }

    }

    public String ReadFile(String path) {
        //File folder = plugdir;
        String string="";
        File file = new File(plugdir+File.separator+path);
        if (!file.exists()) {
            try {
                file.createNewFile();
                WriteFile(file.toString(), "false");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        try {
            FileInputStream read= new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            string= new String(data, "UTF-8");
            read.close();
            return string;
        } catch (Exception e) {
            System.out.println(e);

        }
        return string;
    }
}
