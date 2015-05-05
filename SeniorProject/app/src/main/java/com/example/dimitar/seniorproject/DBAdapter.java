// ------------------------------------ DBADapter.java ---------------------------------------------

package com.example.dimitar.seniorproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBAdapter {

    /////////////////////////////////////////////////////////////////////
    //	Constants & Data
    /////////////////////////////////////////////////////////////////////
    // For logging:
    private static final String TAG = "DBAdapter";

    // DB Fields
    public static final String KEY_ROWID = "_id";
    public static final int COL_ROWID = 0;
    /*
     * CHANGE 1:
     */
    // TODO: Setup fields here:
    public static final String KEY_NAME = "name";
    public static final String KEY_REALNAME = "realname";
    public static final String KEY_UNIVERSE = "universe";
    public static final String KEY_HISTORY = "history";
    public static final String KEY_NEMESIS = "nemesis";
    public static final String KEY_RELATIVES = "relatives";
    public static final String KEY_ACCOMPLICES = "accomplices";
    public static final String KEY_ICONID = "icondid";
    public static final String KEY_VIDEOURL = "videourl";
    public static final String KEY_PICTUREID = "pictureid";


    // TODO: Setup field numbers here (0 = KEY_ROWID, 1=...)
    public static final int COL_NAME = 1;
    public static final int COL_REALNAME= 2;
    public static final int COL_UNIVERSE = 3;
    public static final int COL_HISTORY = 4;
    public static final int COL_NEMESIS = 5;
    public static final int COL_RELATIVES = 6;
    public static final int COL_ACCOMPLICES = 7;
    public static final int COL_ICONID = 8;
    public static final int COL_VIDEOURL = 9;
    public static final int COL_PICTUREID = 10;


    public static final String[] ALL_KEYS = new String[] {KEY_ROWID, KEY_NAME, KEY_REALNAME, KEY_UNIVERSE, KEY_HISTORY, KEY_NEMESIS,
            KEY_RELATIVES, KEY_ACCOMPLICES, KEY_ICONID, KEY_VIDEOURL, KEY_PICTUREID};

    // DB info: it's name, and the table we are using (just one).
    public static final String DATABASE_NAME = "MyDb";
    public static final String DATABASE_TABLE = "mainTable";
    // Track DB version if a new version of your app changes the format.
    public static final int DATABASE_VERSION = 3;

    private static final String DATABASE_CREATE_SQL =
            "create table " + DATABASE_TABLE
                    + " (" + KEY_ROWID + " integer primary key autoincrement, "
			
			/*
			 * CHANGE 2:
			 */
                    // TODO: Place your fields here!
                    // + KEY_{...} + " {type} not null"
                    //	- Key is the column name you created above.
                    //	- {type} is one of: text, integer, real, blob
                    //		(http://www.sqlite.org/datatype3.html)
                    //  - "not null" means it is a required field (must be given a value).
                    // NOTE: All must be comma separated (end of line!) Last one must have NO comma!!
                    + KEY_NAME + " text not null, "
                    + KEY_REALNAME + " text not null, "
                    + KEY_UNIVERSE + " text not null, "
                    + KEY_HISTORY + " text not null, "
                    + KEY_NEMESIS + " text not null, "
                    + KEY_RELATIVES + " text not null, "
                    + KEY_ACCOMPLICES + " text not null, "
                    + KEY_ICONID + " integer not null, "
                    + KEY_VIDEOURL + " text not null, "
                    + KEY_PICTUREID + " integer not null "

                    // Rest  of creation:
                    + ");";

    // Context of application who uses us.
    private final Context context;

    private DatabaseHelper myDBHelper;
    private SQLiteDatabase db;

    /////////////////////////////////////////////////////////////////////
    //	Public methods:
    /////////////////////////////////////////////////////////////////////

    public DBAdapter(Context ctx) {
        this.context = ctx;
        myDBHelper = new DatabaseHelper(context);
    }

    // Open the database connection.
    public DBAdapter open() {
        db = myDBHelper.getWritableDatabase();
        return this;
    }

    // Close the database connection.
    public void close() {
        myDBHelper.close();
    }

    // Add a new set of values to the database.
    public long insertRow(String name, String realname, String universe, String history,
                          String nemesis, String relatives, String accomplices, int iconid, String videourl, int pictureid) {
		/*
		 * CHANGE 3:
		 */
        // TODO: Update data in the row with new fields.
        // TODO: Also change the function's arguments to be what you need!
        // Create row's data:
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_REALNAME, realname);
        initialValues.put(KEY_UNIVERSE, universe);
        initialValues.put(KEY_HISTORY, history);
        initialValues.put(KEY_NEMESIS, nemesis);
        initialValues.put(KEY_RELATIVES, relatives);
        initialValues.put(KEY_ACCOMPLICES, accomplices);
        initialValues.put(KEY_ICONID, iconid);
        initialValues.put(KEY_VIDEOURL, videourl);
        initialValues.put(KEY_PICTUREID, pictureid);


        // Insert it into the database.
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    // Delete a row from the database, by rowId (primary key)
    public boolean deleteRow(long rowId) {
        String where = KEY_ROWID + "=" + rowId;
        return db.delete(DATABASE_TABLE, where, null) != 0;
    }

    public void deleteAll() {
        Cursor c = getAllRows();
        long rowId = c.getColumnIndexOrThrow(KEY_ROWID);
        if (c.moveToFirst()) {
            do {
                deleteRow(c.getLong((int) rowId));
            } while (c.moveToNext());
        }
        c.close();
    }

    // Return all data in the database.
    public Cursor getAllRows() {
        String where = null;
        Cursor c = 	db.query(true, DATABASE_TABLE, ALL_KEYS,
                where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    // Get a specific row (by rowId)
    public Cursor getRow(long rowId) {
        String where = KEY_ROWID + "=" + rowId;
        Cursor c = 	db.query(true, DATABASE_TABLE, ALL_KEYS,
                where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    // Change an existing row to be equal to new data.
    public boolean updateRow(long rowId, String name, String realname, String universe, String history,
                             String nemesis, String relatives, String accomplices, int iconid, String videourl, int pictureid) {
        String where = KEY_ROWID + "=" + rowId;

		/*
		 * CHANGE 4:
		 */
        // TODO: Update data in the row with new fields.
        // TODO: Also change the function's arguments to be what you need!
        // Create row's data:
        ContentValues newValues = new ContentValues();
        newValues.put(KEY_NAME, name);
        newValues.put(KEY_REALNAME, realname);
        newValues.put(KEY_UNIVERSE, universe);
        newValues.put(KEY_HISTORY, history);
        newValues.put(KEY_NEMESIS, nemesis);
        newValues.put(KEY_RELATIVES, relatives);
        newValues.put(KEY_ACCOMPLICES, accomplices);
        newValues.put(KEY_ICONID, iconid);
        newValues.put(KEY_VIDEOURL, videourl);
        newValues.put(KEY_PICTUREID, pictureid);

        // Insert it into the database.
        return db.update(DATABASE_TABLE, newValues, where, null) != 0;
    }



    /////////////////////////////////////////////////////////////////////
    //	Private Helper Classes:
    /////////////////////////////////////////////////////////////////////

    /**
     * Private class which handles database creation and upgrading.
     * Used to handle low-level database access.
     */
    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase _db) {
            _db.execSQL(DATABASE_CREATE_SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading application's database from version " + oldVersion
                    + " to " + newVersion + ", which will destroy all old data!");

            // Destroy old database:
            _db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);

            // Recreate new database:
            onCreate(_db);
        }
    }
}
