package com.steve.navigationdrawer.services;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ConnectionBd {

    private static SQLiteDatabase bd;
    private static String database = "sqliteBd";
    private static int version = 1;


    public static SQLiteDatabase getBd(Context ctx) {
        GestionTable gt = new GestionTable(ctx, database, null, version);
        return bd = gt.getWritableDatabase();

    }

    public static void close() {
        bd.close();
    }

    public static void deleteBd(Context ctx) {
        ctx.deleteDatabase(database);
    }


    public static void addBd(Context ctx) {
        AssetManager assetManager = ctx.getAssets();
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            inputStream = assetManager.open(database);
            outputStream = new FileOutputStream(new File("" + ctx.getDatabasePath(database).getAbsolutePath()));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            Log.d("bd", "bd import" + ctx.getDatabasePath(database).getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    // outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
