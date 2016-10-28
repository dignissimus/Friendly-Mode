package me.sezeh.spammy23.Friendly;


import java.io.*;

import static me.sezeh.spammy23.Friendly.Main.plugdir;

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
    //The Below Was Made By DomovoiButler
    public void copy(InputStream in, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while((len=in.read(buf))>0){
                out.write(buf,0,len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
