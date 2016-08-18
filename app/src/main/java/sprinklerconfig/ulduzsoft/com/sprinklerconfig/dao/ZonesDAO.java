package sprinklerconfig.ulduzsoft.com.sprinklerconfig.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import sprinklerconfig.ulduzsoft.com.sprinklerconfig.model.Zone;

/**
 * Created by syunaeva on 8/6/16.
 */
public class ZonesDAO{
    public static final String TAG = "ZonesDAO";


    // Database fields
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private String[] allColumns = { DBHelper.COLUMN_ZONE_ID, DBHelper.COLUMN_ZONE_DURATION, DBHelper.COLUMN_ZONE_NAME};

    public ZonesDAO(Context context){
        dbHelper = new DBHelper(context);
        try {
            open();
        } catch (SQLException e) {
            Log.e(TAG, "SQLException on openning database " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public Zone createZone(String zoneName, int duration){
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_ZONE_NAME, zoneName);
        values.put(DBHelper.COLUMN_ZONE_DURATION, duration);
        long insertId = database.insert(DBHelper.TABLE_ZONES, null, values);
        Cursor cursor = database.query(DBHelper.TABLE_ZONES, allColumns, DBHelper.COLUMN_ZONE_ID + " = "
                + insertId, null, null, null, null);
        cursor.moveToFirst();
        Zone newZone = cursorToZone(cursor);
        cursor.close();
        return newZone;
    }

    public void deleteZone(Zone zone){
    }

    public List<Zone> getAllZones(){
        List<Zone> zones = new ArrayList<Zone>();

        Cursor cursor = database.query(DBHelper.TABLE_ZONES, allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Zone zone = cursorToZone(cursor);
            zones.add(zone);
            cursor.moveToNext();
        }

        cursor.close();
        return zones;
    }

    private Zone cursorToZone(Cursor cursor){
        Zone zone = new Zone();
        zone.setId(cursor.getLong(0));
        zone.setZoneTone(cursor.getInt(1));
        zone.setZone(cursor.getString(2));
        return zone;
    }
}
