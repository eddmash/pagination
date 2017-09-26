/*
 * This file is part of the com.eddmash.querer package.
 * <p>
 * (c) Eddilbert Macharia (http://eddmash.com)<edd.cowan@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.eddmash.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple set of helper methods to query for data on android sqlite database.
 * <p>
 * To get the instance of theis Active record use the getInstance method,
 * this method takes just one parameter. an instance of SQLiteDatabase.
 * <p>
 * this class is implemented as a singleton meaning only one instance of ActiveRecord ever exists
 * in your application life time.
 * <p>
 * NB:: the instance of SQLiteDatabase passed in getInstance method is destroyed once the garbage
 * collector destroys the instance of the ActiveRecord.
 */

public class ActiveRecord {

    private static ActiveRecord instance;

    /**
     * REturn instance of SQLiteDatabase that the activerecord instance is using.
     *
     * @return
     */
    public SQLiteDatabase getDb() {
        return db;
    }

    private SQLiteDatabase db;


    private ActiveRecord(SQLiteDatabase database) {
        db = database;
    }


    /**
     * Returns an instance of the the activerecord class
     *
     * @param database
     * @return
     */
    public static ActiveRecord getInstance(SQLiteDatabase database) {
        if (instance == null) {
            instance = new ActiveRecord(database);
        }
        return instance;
    }

    /**
     * Returns an list of maps, where the map represents each record in the database.
     * <p>
     * with keys of the map as the column name and values of the map as the values of the
     * respective columns.
     * <p>
     * something like this:
     * <p>
     * [{id:1, username:ken, age:50}, {id:2, username:matt, age:70}]
     *
     * @param sql
     * @param args
     * @return
     */
    public List<Map> find(String sql, String[] args) {

        Cursor mycursor = db.rawQuery(sql, args);
        HashMap map;

        List<Map> list = new ArrayList<>();
        while (mycursor.moveToNext()) {
            map = new HashMap();
            for (String colName : mycursor.getColumnNames()) {
                map.put(colName, mycursor.getString(mycursor.getColumnIndexOrThrow(colName)));
            }
            list.add(map);
        }

        mycursor.close();
        return list;
    }

    /**
     * Returns an list of maps, where the map represents each record in the database.
     * <p>
     * with keys of the map as the column name and values of the map as the values of the
     * respective columns.
     * <p>
     * something like this:
     * <p>
     * [{id:1, username:ken, age:50}, {id:2, username:matt, age:70}]
     *
     * @param tableName
     * @param queryCols
     * @return
     */
    public List<Map> all(String tableName, String[] queryCols) {
        Cursor mycursor = db.query(tableName, queryCols, null, null, null, null, null, null);
        HashMap map;

        if (queryCols == null) {
            queryCols = mycursor.getColumnNames();
        }
        Log.e(getClass().getName(), "COLS :: " + Arrays.toString(queryCols));
        List<Map> list = new ArrayList<>();
        while (mycursor.moveToNext()) {
            map = new HashMap();
            for (String colName : queryCols) {

                map.put(colName, mycursor.getString(mycursor.getColumnIndexOrThrow(colName)));
            }
            list.add(map);
        }

        mycursor.close();
        return list;
    }

    /**
     * Returns an list of maps, where the map represents each record in the database.
     * <p>
     * with keys of the map as the column name and values of the map as the values of the
     * respective columns.
     * <p>
     * something like this:
     * <p>
     * [{id:1, username:ken, age:50}, {id:2, username:matt, age:70}]
     *
     * @param tableName
     * @return
     */
    public List<Map> all(String tableName) {
        Cursor mycursor = db.query(tableName, null, null, null, null, null, null, null);
        HashMap map;

        String queryCols[] = mycursor.getColumnNames();
        List<Map> list = new ArrayList<>();
        while (mycursor.moveToNext()) {
            map = new HashMap();
            for (String colName : queryCols) {

                map.put(colName, mycursor.getString(mycursor.getColumnIndexOrThrow(colName)));
            }
            list.add(map);
        }

        mycursor.close();
        return list;
    }

    /**
     * Returns value of the single column requested.
     */
    public boolean exists(String tableName, String field, String value) {

        Cursor cursor = db.query(
                tableName,
                null,
                field + " = ? ",
                new String[]{value},
                null,
                null,
                null
        );

        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    /**
     * Returns value of the single column requested.
     */
    public boolean exists(String sql, String[] params) {

        Cursor cursor = db.rawQuery(sql, params);

        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    /**
     * Returns a Map representing a single record based on the query.
     *
     * @param sql
     * @param params parameters to bind to the query
     * @return
     */
    public HashMap get(String sql, String[] params) {
        Cursor c = db.rawQuery(sql, params);
        String selection = "";
        HashMap record = new HashMap();
        if (c.moveToFirst()) {
            // we use the first column in the sql.
            for (String col : c.getColumnNames()) {
                record.put(col, c.getString(c.getColumnIndexOrThrow(col)));
            }
        }
        c.close();
        return record;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        db.close();
    }

    public int getScalarInt(String sql, String[] params) {
        Cursor c = db.rawQuery(sql, params);
        int count = -1;
        if (c.moveToFirst()) {
            count = c.getInt(0);
        }
        c.close();

        return count;
    }
}
