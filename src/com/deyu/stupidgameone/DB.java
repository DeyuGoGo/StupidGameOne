package com.deyu.stupidgameone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DB {
	private Context mContext = null;
	private DatabaseHelper dbHelper;
	private SQLiteDatabase db;
	private static final String KEY_COUNT = "_CC";
	private static final String KEY_ROWID = "_id";
	String[] strCols = new String[] { KEY_ROWID, KEY_COUNT };
	private static final String DATABASE_NAME = "game.db";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_TABLE = "score";
	private static final String DATABASE_CREATE = "CREATE TABLE "
			+ DATABASE_TABLE + "(" + KEY_ROWID + " INTEGER PRIMARY KEY,"
			+ KEY_COUNT + " INTEGER" + ");";

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

	public Cursor getALL() {
		// return db.rawQuery("select * from" +DATABASE_TABLE+
		// "order by created DESC",null);
		return db.query(DATABASE_TABLE, strCols, null, null, null, null,
				KEY_COUNT + " DESC");
	}

	public long create(int record) {
		// SimpleDateFormat df =new SimpleDateFormat("yyyy/MM/dd HH:mm",
		// Locale.ENGLISH);
		ContentValues args = new ContentValues();
		args.put(KEY_ROWID, record);
		args.put(KEY_COUNT, 0);
		return db.insert(DATABASE_TABLE, null, args);

	}

	public Cursor get(long rowId) throws SQLiteException {
		Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] {
				KEY_ROWID, KEY_COUNT }, KEY_ROWID + "=" + rowId, null, null,
				null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public boolean update(int rowId, int record) {
		ContentValues args = new ContentValues();
		args.put(KEY_COUNT, record);
		return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;

	}

	public boolean delete(long rowId) {
		return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {

		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {

			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);

		}
	}

}
