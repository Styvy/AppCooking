package com.steve.navigationdrawer.services;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ConnectionBd {

    private static SQLiteDatabase bd;
    private static String nomBd = "sqlite_bd";
    private static int versionBd = 1;


    public static SQLiteDatabase getBd(Context ctx) {
        GestionTable gt = new GestionTable(ctx, nomBd, null, versionBd);
        bd = gt.getWritableDatabase();
        return bd;
    }

    public static void closeBd() {
        bd.close();
    }

    public static boolean doesDatabaseExist(Context context, String bdName) {
        File bdFile = context.getDatabasePath(bdName);
        return bdFile.exists();

    }

    public static void addBd(Context ctx) {
        String cheminBd = null;

        boolean test = doesDatabaseExist(ctx, nomBd);

        if (!doesDatabaseExist(ctx, nomBd)) {
            ConnectionBd.getBd(ctx);
        }

        try {
            cheminBd = ctx.getDatabasePath(nomBd).getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        }

        AssetManager aManager = ctx.getAssets();
        InputStream in = null;
        OutputStream out = null;

        try {
            in = aManager.open(nomBd);
            out = new FileOutputStream(new File(cheminBd));

            int read = 0;
            byte[] buffer = new byte[1024];

            while ((read = in.read(buffer)) != -1) {
                out.write(buffer);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
