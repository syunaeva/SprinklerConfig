package sprinklerconfig.ulduzsoft.com.sprinklerconfig.dao;

/**
 * Created by syunaeva on 8/6/16.
 */
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import sprinklerconfig.ulduzsoft.com.sprinklerconfig.model.Program;


public class ProgramDAO {

    public static final String TAG = "ProgramDAO";

    // Database fields
    private SQLiteDatabase mDatabase;
    private DBHelper mDbHelper;
    private Context mContext;
    private String[] mAllColumns = { DBHelper.COLUMN_PROGRAM_ID,
            DBHelper.COLUMN_PROGRAM_NAME, DBHelper.COLUMN_PROGRAM_DAYS,
            DBHelper.COLUMN_PROGRAM_ZONES,
            DBHelper.COLUMN_PROGRAM_DAY_INTERVAL,
            DBHelper.COLUMN_PROGRAM_START_HOUR,
            DBHelper.COLUMN_PROGRAM_START_MINUTE,
            DBHelper.COLUMN_PROGRAM_ZONE_DELAY ,
            DBHelper.COLUMN_PROGRAM_LAST_RUN};

    public ProgramDAO(Context context) {
        this.mContext = context;
        mDbHelper = new DBHelper(context);
        // open the database
        try {
            open();
        } catch (SQLException e) {
            Log.e(TAG, "SQLException on openning database " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void open() throws SQLException {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    public Program createProgram(Program program){

        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_PROGRAM_NAME, program.name);
        values.put(DBHelper.COLUMN_PROGRAM_DAYS, program.days);
        values.put(DBHelper.COLUMN_PROGRAM_ZONES, program.zones);
        values.put(DBHelper.COLUMN_PROGRAM_DAY_INTERVAL, program.dayInterval);
        values.put(DBHelper.COLUMN_PROGRAM_START_HOUR, program.startHour);
        values.put(DBHelper.COLUMN_PROGRAM_START_MINUTE, program.startMinute);
        values.put(DBHelper.COLUMN_PROGRAM_ZONE_DELAY, program.zoneDelaySec);
        values.put(DBHelper.COLUMN_PROGRAM_LAST_RUN, ""); // the program just created - no run yet

        long insertId = mDatabase
                .insert(DBHelper.TABLE_PROGRAMS, null, values);
        Cursor cursor = mDatabase.query(DBHelper.TABLE_PROGRAMS, mAllColumns,
                DBHelper.COLUMN_PROGRAM_ID + " = " + insertId, null, null,
                null, null);
        cursor.moveToFirst();
        Program newProgram = cursorToProgram(cursor);
        cursor.close();
        return newProgram;
    }

    public void deleteCompany(Program program) {
        long id = program.getId();

        System.out.println("the deleted company has the id: " + id);
        mDatabase.delete(DBHelper.TABLE_PROGRAMS, DBHelper.COLUMN_PROGRAM_ID
                + " = " + id, null);
    }

    public List<Program> getAllPrograms() {
        List<Program> listCompanies = new ArrayList<Program>();

        Cursor cursor = mDatabase.query(DBHelper.TABLE_PROGRAMS, mAllColumns,
                null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Program program = cursorToProgram(cursor);
                listCompanies.add(program);
                cursor.moveToNext();
            }

            // make sure to close the cursor
            cursor.close();
        }
        return listCompanies;
    }

    public Program getProgramById(long id) {
        Cursor cursor = mDatabase.query(DBHelper.TABLE_PROGRAMS, mAllColumns,
                DBHelper.COLUMN_PROGRAM_ID + " = ?",
                new String[] { String.valueOf(id) }, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Program program = cursorToProgram(cursor);
        return program;
    }

    protected Program cursorToProgram(Cursor cursor) {
        Program pr = new Program();
        pr.setId(cursor.getLong(0));
        pr.name = cursor.getString(1);
        pr.days = cursor.getString(2);
        pr.zones = cursor.getString(3);
        pr.dayInterval = cursor.getInt(4);
        pr.startHour = cursor.getInt(5);
        pr.startMinute = cursor.getInt(6);
        pr.zoneDelaySec = cursor.getInt(7);
        // need to do something with last run
        return pr;
    }

}
