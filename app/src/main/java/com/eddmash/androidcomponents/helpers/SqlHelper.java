/*
 * This file is part of the Fivmszm package.
 * <p>
 * (c) Eddilbert Macharia (http://eddmash.com)<edd.cowan@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.eddmash.androidcomponents.helpers;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by edd on 6/13/17.
 */

public class SqlHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "ziamis.db";
    public static final String INITDBFILE = "initdb.sql";
    public static final String UPDATEDBFILE = "updatedb.txt";

    private static SqlHelper instance;

    public final SQLiteDatabase db;
    private Context context;


    private SqlHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        db = this.getReadableDatabase();
    }

    public static SqlHelper getInstance(Context context){
        if (instance==null){
            instance = new SqlHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e(getClass().getName(), " CREATING DATABASE ");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte buf[] = new byte[1024];
        int len;
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = null;

        try {
            inputStream = assetManager.open(INITDBFILE);
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();

            String[] createScript = outputStream.toString().split(";");
            for (int i = 0; i < createScript.length; i++) {
                String sqlStatement = createScript[i].trim();
                // TODO You may want to parse out comments here
                if (sqlStatement.length() > 0) {
                    db.execSQL(sqlStatement + ";");
                }
            }
        } catch (Exception e) {
            Log.e(getClass().getName()+" FAILING TO CREATE", e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

}
