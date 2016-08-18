package sprinklerconfig.ulduzsoft.com.sprinklerconfig.dao;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by syunaeva on 8/6/16.
 */
public class DBHelper extends SQLiteOpenHelper{
    public static final String TAG = "DBHelper";

    // columns of the PROGRAMS table
    public static final String TABLE_PROGRAMS = "programs";
    public static final String COLUMN_PROGRAM_ID = "_id";
    public static final String COLUMN_PROGRAM_NAME = "program_name";
    public static final String COLUMN_PROGRAM_DAYS = "days";
    public static final String COLUMN_PROGRAM_ZONES = "zones";
    public static final String COLUMN_PROGRAM_DAY_INTERVAL = "day_interval";
    public static final String COLUMN_PROGRAM_START_HOUR = "start_hour"; // 24 hours
    public static final String COLUMN_PROGRAM_START_MINUTE = "start_minute";
    public static final String COLUMN_PROGRAM_ZONE_DELAY = "zone_delay";
    public static final String COLUMN_PROGRAM_LAST_RUN = "last_run";

    // columns of the zones table
    public static final String TABLE_ZONES = "zones";
    public static final String COLUMN_ZONE_ID = COLUMN_PROGRAM_ID;
    public static final String COLUMN_ZONE_DURATION = "duration";
    public static final String COLUMN_ZONE_NAME = "zone_name";


    private static final String DATABASE_NAME = "sprinklerconfig.db";
    private static final int DATABASE_VERSION = 1;

    // SQL statement of the zones table creation
    private static final String SQL_CREATE_TABLE_ZONES = "CREATE TABLE " + TABLE_ZONES + "("
            + COLUMN_ZONE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_ZONE_DURATION + " INTEGER NOT NULL, "
            + COLUMN_ZONE_NAME + " TEXT NOT NULL "
            +");";

    // SQL statement of the PROGRAMS table creation
    // TEXT as ISO8601 strings ("YYYY-MM-DD HH:MM:SS.SSS").
    private static final String SQL_CREATE_TABLE_PROGRAMS = "CREATE TABLE " + TABLE_PROGRAMS + "("
            + COLUMN_PROGRAM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_PROGRAM_NAME + " TEXT NOT NULL, "
            + COLUMN_PROGRAM_DAYS + " TEXT , "
            + COLUMN_PROGRAM_ZONES + " TEXT NOT NULL, "
            + COLUMN_PROGRAM_DAY_INTERVAL + " INTEGER NOT NULL, "
            + COLUMN_PROGRAM_START_HOUR + " INTEGER NOT NULL, "
            + COLUMN_PROGRAM_START_MINUTE + " INTEGER NOT NULL, "
            + COLUMN_PROGRAM_ZONE_DELAY  + " INTEGER NOT NULL, "
            + COLUMN_PROGRAM_LAST_RUN + " TEXT  "
            +");";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(SQL_CREATE_TABLE_PROGRAMS);
        database.execSQL(SQL_CREATE_TABLE_ZONES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG,
                "Upgrading the database from version " + oldVersion + " to "+ newVersion);
        // clear all data
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ZONES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROGRAMS);

        // recreate the tables
        onCreate(db);
    }

    public DBHelper(Context context, String name, CursorFactory factory,int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

}
