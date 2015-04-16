package com.deyu.stupidgameone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DB {
    private static final String KEY_COUNT = "_CC";
    private static final String KEY_TIME = "_Time";
    private static final String KEY_ROWID = "_id";
    private static final String DATABASE_NAME = "game.db";
    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_TABLE = "score";
    private static final String DATABASE_CREATE = "CREATE TABLE "
            + DATABASE_TABLE +
            "(" + KEY_ROWID + " INTEGER PRIMARY KEY,"
            + KEY_TIME + " INTEGER,"
            + KEY_COUNT + " INTEGER" + ");";
    private Context mContext = null;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public DB(Context context) {
        this.mContext = context;
    }

    public DB open() throws SQLiteException {
        dbHelper = new DatabaseHelper(mContext);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public long create(int record) {
        // SimpleDateFormat df =new SimpleDateFormat("yyyy/MM/dd HH:mm",
        // Locale.ENGLISH);
        ContentValues args = new ContentValues();
        args.put(KEY_ROWID, record);
        args.put(KEY_COUNT, 0);
        args.put(KEY_TIME, 0);
        return db.insert(DATABASE_TABLE, null, args);

    }

    public Cursor get(long rowId) throws SQLiteException {
        Cursor mCursor = db.query(true, DATABASE_TABLE, new String[]{
                        KEY_ROWID, KEY_COUNT, KEY_TIME}, KEY_ROWID + "=" + rowId, null, null,
                null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public boolean updateGameCount(int rowId, int record) {
        ContentValues args = new ContentValues();
        args.put(KEY_COUNT, record);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }

    public boolean updateGameTime(int rowId, long recordTime) {
        ContentValues args = new ContentValues();
        args.put(KEY_TIME, recordTime);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }

    public boolean delete(long rowId) {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        private int count = 0;
        private int Time = (int) GameInfo.GameDefaultTime;

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
            create(db);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
            saveLastData(db);
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }

        private void saveLastData(SQLiteDatabase db) {
            Cursor mCursor = db.query(true, DATABASE_TABLE, new String[]{
                            KEY_ROWID, KEY_COUNT, KEY_TIME}, KEY_ROWID + "=" + 1, null, null,
                    null, null, null);
            if (mCursor != null) {
                mCursor.moveToFirst();
            }
            count = mCursor.getInt(1);
            Time = mCursor.getInt(2);
        }

        public void create(SQLiteDatabase db) {
            // SimpleDateFormat df =new SimpleDateFormat("yyyy/MM/dd HH:mm",
            // Locale.ENGLISH);
            ContentValues args = new ContentValues();
            args.put(KEY_ROWID, 1);
            args.put(KEY_COUNT, count);
            args.put(KEY_TIME, Time);
            db.insert(DATABASE_TABLE, null, args);
        }
    }

}
